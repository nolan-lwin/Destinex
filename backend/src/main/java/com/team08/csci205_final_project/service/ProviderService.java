/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 11/11/2023
 * Time: 19:14
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.service
 * Class: ProviderService
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.exception.DuplicateAccountException;
import com.team08.csci205_final_project.exception.ResourceNotFoundException;
import com.team08.csci205_final_project.model.Auth.Role;
import com.team08.csci205_final_project.model.DTO.Provider.ProviderRegister;
import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Provider.Provider;
import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.repository.ProviderRepository;
import com.team08.csci205_final_project.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    public ProviderRepository providerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public JobService jobService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    /** Add a new provider */
    public Provider providerRegister(ProviderRegister providerRegister) throws AccessDeniedException {
        String id = userService.getCurrentUserId();

        // If there is user from authentication then return empty
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }

        // Account has to be user account
        User user = userRepository.findById(id).get();
        if (user.getRole() != Role.ROLE_USER) {
            throw new DuplicateAccountException("You already signed up for provider");
        }

        // Create a provider from user account
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerRegister, provider);

        // Set initial state
        provider.setProviderAvail(true);
        provider.setActiveJob(null);
        provider.setUserId(user.getId());
        provider.setEmail(user.getEmail());
        user.setRole(Role.ROLE_PROVIDER);
        userRepository.save(user);

        return providerRepository.save(provider);
    }

    /** Find a provider based on their userId */
    public Optional<Provider> findProviderById (String id) {
        return providerRepository.findById(id);
    }

    /** Find a provider based on their email */
    public Optional<Provider> findProviderByEmail (String email) {
        return providerRepository.findByEmail(email);
    }

    /** Update a provider's information */
    public Provider updateProvider(Provider providerRegister) {
        String id = providerRegister.getProviderId();
        Provider provider = findProviderById(id).orElseThrow(() -> new ResourceNotFoundException("Provider not found"));
        BeanUtils.copyProperties(providerRegister, provider);
        return providerRepository.save(provider);
    }

    /** Update a provider's location */
    public void updateProviderLocation(String providerId, double latitude, double longtitude) {
        Optional<Provider> providerOpt = providerRepository.findById(providerId);
        if (providerOpt.isPresent()) {
            Provider provider = providerOpt.get();
            provider.setCurrentLocation(new GeoJsonPoint(longtitude, latitude));
            providerRepository.save(provider);
        }
    }

    /** Find nearby providers in radius of distanceInKm km */
    public List<Provider> findNearbyProviders(double longitude, double latitude, double distanceInKm) {
        Point point = new Point(longitude, latitude);
        Distance distance = new Distance(distanceInKm, Metrics.KILOMETERS);

        Query query = new Query();
        query.addCriteria(Criteria.where("currentLocation").nearSphere(point).maxDistance(distance.getNormalizedValue()));

        return mongoTemplate.find(query, Provider.class, "providers");
    }

    /**
     * Find assigned job for provider
     */
    public Optional<Job> findAssignedJob(String providerEmail) {
        Optional<Provider> providerOpt = findProviderByEmail(providerEmail);

        if (providerOpt.isEmpty()) {
            throw new ResourceNotFoundException("Provider not found with email: " + providerEmail);
        }

        Provider provider = providerOpt.get();
        String assignedJobId = provider.getActiveJob();

        if (assignedJobId == null || assignedJobId.isEmpty()) {
            return Optional.empty(); // No assigned job
        }

        return jobService.findJobById(assignedJobId);
    }

    /** Delete provider */
    public void deleteProvider(String userId) {
        if (providerRepository.existsById(userId)) {
            providerRepository.deleteById(userId);
        }
        else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    /** Update the status after accepting job
     * @param id id of the provider
     * @return true/false if accepting the job is successful or not
     */
    public boolean acceptJob(String id) {
        Optional <Provider> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            Provider updatedProvider = provider.get();
            String updateJob = updatedProvider.getActiveJob();
            if (updateJob != null) {
                jobService.acceptJob(updateJob, provider.get().getUserId());
                updatedProvider.setActiveJob(null);
                updatedProvider.setProviderAvail(false);
                providerRepository.save(updatedProvider);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean completeJob(String providerEmail) {
        Optional <Provider> provider = providerRepository.findByEmail(providerEmail);
        if (provider.isPresent()) {
            Provider providerInformation = provider.get();
            String currentJobId = providerInformation.getActiveJob();
            jobService.completeJob(currentJobId);
            providerInformation.setActiveJob(null);
            providerInformation.setProviderAvail(true);
            providerRepository.save(providerInformation);
            return true;
        }
        return false;
    }
}
