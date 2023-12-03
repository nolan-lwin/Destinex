/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 02/11/2023
 * Time: 15:05
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.controller
 * Class: UserController
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.controller;

import com.team08.csci205_final_project.model.DTO.Job.JobInfo;
import com.team08.csci205_final_project.model.DTO.Job.NewJobRequest;
import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Job.JobStatus;
import com.team08.csci205_final_project.service.JobService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/jobs")
@SecurityRequirement(name = "bearerAuth")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * The interface for requesting a job
     * @param newJobRequest the information of the job including
     *            "userId", "category", "description", "receiverName"
     *            "receiverAddress", "receiverPhone"
     * @return The response from the request
     */
    @Operation(summary = "Create a job and publish an event for job dispatcher")
    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody NewJobRequest newJobRequest) {
        Job savedJob = jobService.createJob(newJobRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedJob.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedJob);
    }

    /**
     * Get the job with its ID
     * @param id ID of the job
     * @return the job
     */
    @Operation(summary = "Get job information from Job ID")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id) {
        return jobService.findJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all the job from one user
     * @param status job status
     * @return All the job of that user
     */
    @Operation(summary = "Get jobs information from this account")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<JobInfo>> getJobByUser(@RequestParam(required = false) JobStatus status) {
        return ResponseEntity.ok(JobInfo.convertToUserDtoList(jobService.findJobByUser(status)));
    }

//    /**
//     * API endpoint to update the job or creating new one using PUT method
//     * @param id id of the job to update or create
//     * @param job the job content
//     * @return response with code 201 or 200
//     */
//    @PutMapping("/{id}")
//    public ResponseEntity<Job> updateJob(@PathVariable String id, @RequestBody NewJobDTO job) {
//        job.setId(id);
//        if (jobService.findJobById(id).isEmpty()) {
//            return createJob(job);
//        }
//        else {
//            Job updatedJob = jobService.updateJob(job);
//            return ResponseEntity.ok(updatedJob);
//        }
//    }

    /**
     * Delete the job from database
     * @param id id of the job
     * @return response with no content
     */
    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        if (jobService.deleteJob(id)) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
