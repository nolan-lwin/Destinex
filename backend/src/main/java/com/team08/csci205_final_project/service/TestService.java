/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 24/11/2023
 * Time: 14:13
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.service
 * Class: TestService
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.model.DTO.Job.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendTestJobOffer(String email) {
        // Create a hard-coded job offer
        JobOffer jobOffer = new JobOffer("testJobId", "Test Job Description", "100.0");

        // Send this offer to the specific user
        messagingTemplate.convertAndSendToUser(email, "/queue/job-offers", jobOffer);
    }
}
