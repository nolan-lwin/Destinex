/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 13/11/2023
 * Time: 15:09
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.model.DTO
 * Class: JobResponse
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.model.DTO.Job;

import com.team08.csci205_final_project.model.Job.JobStatus;

public class JobResponse {
    private String jobId;
    private String providerId;
    private JobStatus status;

    public JobResponse(String jobId, String providerId, JobStatus status) {
        this.jobId = jobId;
        this.providerId = providerId;
        this.status = status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}
