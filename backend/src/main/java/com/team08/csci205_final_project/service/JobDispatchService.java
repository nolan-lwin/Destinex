/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 13/11/2023
 * Time: 14:33
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.service
 * Class: DispatchService
 *
 * Description:
 *
 * ****************************************
 */

package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.event.JobPostedEvent;
import com.team08.csci205_final_project.model.DTO.Job.JobOffer;
import com.team08.csci205_final_project.model.DTO.Job.JobTrackingStatus;
import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Job.JobStatus;
import com.team08.csci205_final_project.model.Provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service for dispatching jobs to providers. This service listens for job posting events
 * and attempts to assign these jobs to suitable providers based on proximity and availability.
 */
@Service
public class JobDispatchService implements ApplicationListener<JobPostedEvent> {

    /** Define a logger for debugging reasons */
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(JobDispatchService.class);

    /** Define a constant for searching radius */
    private static final double DEFAULT_RADIUS_KM = 30;

    /** Define a maximum response time for the provider */
    private static final long RESPONSE_TIME_MS = 100 * 1000; // 100 seconds

    /** Map to store a pair of jobId - providers */
    private Map<String, String> jobProviderMap = new ConcurrentHashMap<>();

    /** Map to store a pair of jobId - providers that reject that job */
    private Map<String, List<String>> jobRejectionsMap = new ConcurrentHashMap<>();

    /** Autowired fields */
    @Autowired
    private JobService jobService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private TaskScheduler taskScheduler;

    /**
     * Handles the JobPostedEvent by initiating the job dispatch process.
     * Will be triggered when a new job is posted.
     *
     * @param event The event containing the job details.
     */
    @Override
    public void onApplicationEvent(JobPostedEvent event) {
        Job job = event.getJob();
        LOGGER.info("Received JobPostedEvent for job: " + job.getId());
        dispatchJob(job.getId(), DEFAULT_RADIUS_KM);
    }

    /**
     * Asynchronously dispatches a job to the nearest available provider.
     * It searches for providers within a specified radius and sends job offers to them.
     *
     * @param jobId The ID of the job to be dispatched.
     * @param radiusInKm The radius within which to search for providers, in kilometers.
     */
    @Async("jobDispatchExecutor")
    public void dispatchJob(String jobId, double radiusInKm) {
        LOGGER.info("Starting dispatchJob for jobId: " + jobId);
        jobService.findJobById(jobId).ifPresent(job -> processJobDispatch(job, radiusInKm));
    }

    /**
     * Processes the job dispatching by finding nearby providers and dispatching the job
     * to an available provider who hasn't previously rejected the job.
     *
     * @param job The job to be dispatched.
     * @param radiusInKm The radius within which to find nearby providers.
     */
    private void processJobDispatch(Job job, double radiusInKm) {

        // Store nearby providers from providerService in a list
        List<Provider> nearbyProviders = providerService.findNearbyProviders(
                job.getReceiverAddressPoint().getX(),
                job.getReceiverAddressPoint().getY(),
                radiusInKm
        );

        // Retrieve a list of provider IDs that have rejected this job.
        List<String> rejectedProviders = jobRejectionsMap.getOrDefault(job.getId(), new ArrayList<>());
        LOGGER.info("Providers who have rejected job {}: {}", job.getId(), rejectedProviders);

        // Dispatching jobs to the nearby providers
        Collections.shuffle(nearbyProviders);
        for (Provider provider : nearbyProviders) {
            if (provider.getActiveJob() == null && !rejectedProviders.contains(provider.getEmail())) {
                String message = "Job " + job.getId() + " " + job.getDescription() + " assigned to provider with mail: " + provider.getEmail();
                LOGGER.info(message);
                sendJobOffer(provider, job);

                // Schedule to check the response after a fixed delay
                taskScheduler.schedule(
                        () -> checkJobAcceptance(job.getId(), provider.getEmail(), radiusInKm),
                        new Date(System.currentTimeMillis() + (long)(100 * 1000))
                );

                // Store the mapping of job to provider
                if (provider.getProviderId() != null) {
                    jobProviderMap.put(job.getId(), provider.getEmail());
                }
                else {
                    System.out.println("Provider ID is null for provider: " + provider);
                }

                // Break after sending the job offer
                break;
            }
            else {
                LOGGER.info("Skipping provider {} for job {} as they have rejected it or have an active job.", provider.getEmail(), job.getId());
            }
        }

        LOGGER.info("Finished dispatchJob for jobId: " + job.getId());
    }

    /**
     * Checks if a job has been accepted by the assigned provider after a certain time has elapsed.
     * If the job is not accepted within this period, it is re-dispatched to another provider.
     *
     * @param jobId The ID of the job to check.
     * @param providerEmail The email of the provider to whom the job was dispatched.
     * @param radiusInKm The radius within which to re-dispatch the job, if necessary.
     */
    private void checkJobAcceptance(String jobId, String providerEmail, double radiusInKm) {
        System.out.println("Checking job acceptance for jobId: " + jobId + ", providerId: " + providerEmail);
        Optional<Job> jobOpt = jobService.findJobById(jobId);
        if (!jobOpt.isPresent()) {
            jobProviderMap.remove(jobId);
            return;
        }

        Job job = jobOpt.get();
        boolean isAcceptedAndEmailMatches = job.getStatus() == JobStatus.ACCEPTED
                && providerEmail != null
                && providerEmail.equals(job.getProviderEmail());

        if (!isAcceptedAndEmailMatches) {
            // Remove if job not accepted
            jobProviderMap.remove(jobId);
            dispatchJob(jobId, radiusInKm);
        }
    }

    /**
     * Sends a job offer to a provider.
     *
     * @param provider The provider to whom the job offer is sent.
     * @param job The job for which the offer is being sent.
     */
    private void sendJobOffer(Provider provider, Job job) {
        JobOffer jobOffer = new JobOffer(job.getId(), job.getDescription(), job.getItemPrice());
        LOGGER.info("Sending job offer for job: " + job.getId() + " to provider: " + provider.getEmail());
        messagingTemplate.convertAndSendToUser(provider.getEmail(), "/queue/job-offers", jobOffer);
    }

    /**
     * Notifies a user about the status of their job via a messaging template.
     *
     * @param providerEmail The email address of the provider to notify.
     * @param message The message to be sent to the provider.
     */
    private void notifyUser(String jobId, String providerEmail, String message, JobStatus status) {
        JobTrackingStatus jobTrackingStatus = new JobTrackingStatus(providerEmail, message, status);
        messagingTemplate.convertAndSendToUser(jobId, "/user/job-status", jobTrackingStatus);
    }

    /**
     * Handles the response of providers to job offers.
     * This method updates the job status based on the provider's response and may re-dispatch the job.
     *
     * @param jobId The ID of the job in question.
     * @param providerEmail The email of the provider responding to the job offer.
     * @param status The status of the job (e.g., accepted, rejected).
     */
    public void handleProviderResponse(String jobId, String providerEmail, JobStatus status) {
        Job job = null;
        Provider provider = null;

        LOGGER.info("Handling provider response for job: {}, provider: {}, status: {}", jobId, providerEmail, status);

        Optional<Job> jobOpt = jobService.findJobById(jobId);
        Optional<Provider> providerOpt = providerService.findProviderByEmail(providerEmail);

        if (jobOpt.isPresent()) {
            job = jobOpt.get();
        }

        if (providerOpt.isPresent()) {
            provider = providerOpt.get();
        }

        if (status == JobStatus.ACCEPTED) {
            LOGGER.info("================== Job " + jobId + " is accepted by provider " + providerEmail);
            job.setProviderId(providerEmail);
            job.setStatus(JobStatus.ACCEPTED);
            provider.setActiveJob(job.getId());
            provider.setProviderAvail(false);
            jobService.updateJob(job);
            providerService.updateProvider(provider);
            notifyUser(jobId, job.getUserId(), providerEmail, status);
        } else if (status == JobStatus.REJECTED) {
            LOGGER.info("Job {} is rejected by provider {}. Adding to rejection list.", jobId, providerEmail);
            jobRejectionsMap.computeIfAbsent(jobId, k -> new ArrayList<>()).add(providerEmail);
            LOGGER.info("Updated rejection list for job {}: {}", jobId, jobRejectionsMap.get(jobId));
            jobProviderMap.remove(jobId);
            dispatchJob(jobId, DEFAULT_RADIUS_KM);
            notifyUser(jobId, job.getUserId(), "", status);
        } else {
            LOGGER.info("Providers didn't respond in the allowance time for job: " + jobId + ". Dispatching to new provider.");
            jobProviderMap.remove(jobId);
            dispatchJob(jobId, DEFAULT_RADIUS_KM);
        }
    }
}
