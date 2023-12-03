package com.team08.csci205_final_project.repository;

import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Job.JobStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {
    @Query("{'userId': ?0, 'status': ?1}")
    public List<Job> findByUserId(String userId, JobStatus jobStatus);
}
