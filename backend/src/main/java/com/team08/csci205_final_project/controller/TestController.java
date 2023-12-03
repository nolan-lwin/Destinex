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
 * Package: com.team08.csci205_final_project.controller
 * Class: TestController
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.controller;

import com.team08.csci205_final_project.service.TestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test/send-job-offer")
    public ResponseEntity<String> sendTestJobOffer(@RequestParam String email) {
        testService.sendTestJobOffer(email);
        return ResponseEntity.ok("Test job offer sent to " + email);
    }
}
