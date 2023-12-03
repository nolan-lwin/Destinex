/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 13/11/2023
 * Time: 15:00
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.model
 * Class: JobOffer
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.model.DTO.Job;

public class JobOffer {
    private String jobId;
    private String description;
    private String itemPrice;

    public JobOffer(String jobId, String description, String itemPrice) {
        this.jobId = jobId;
        this.description = description;
        this.itemPrice = itemPrice;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}