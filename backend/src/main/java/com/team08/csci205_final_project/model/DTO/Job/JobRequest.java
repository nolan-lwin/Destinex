/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 13/11/2023
 * Time: 15:08
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.model.DTO
 * Class: JobRequest
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.model.DTO.Job;

public class JobRequest {
    private String jobId;

    public JobRequest(String jobId) {
        this.jobId = jobId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
