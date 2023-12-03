/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 26/11/2023
 * Time: 12:31
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.model.DTO
 * Class: JobStatus
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.model.DTO.Job;

import com.team08.csci205_final_project.model.Job.JobStatus;

public class JobTrackingStatus {
    private String providerEmail;
    private String message;
    private JobStatus status;

    public JobTrackingStatus(String providerEmail, String message, JobStatus status) {
        this.providerEmail = providerEmail;
        this.message = message;
        this.status = status;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}