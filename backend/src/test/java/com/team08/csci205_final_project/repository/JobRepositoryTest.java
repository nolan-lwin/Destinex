package com.team08.csci205_final_project.repository;

import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Job.JobStatus;
import com.team08.csci205_final_project.model.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class JobRepositoryTest {

    private Job randomJob;
    private Job randomJob1;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

//    @BeforeEach
//    public void setup() {
//        randomJob = new Job();
//        randomJob.setUserId("user1");
//        randomJob.setCategory("Food");
//        randomJob.setDescription("Deliver food to my cat");
//        randomJob.setReceiverName("John Doe");
//        randomJob.setReceiverAddress("123 Main St");
//        randomJob.setReceiverPhone("555-1234");
//
//        randomJob1 = new Job();
//        randomJob1.setUserId("user1");
//        randomJob1.setCategory("Pet");
//        randomJob1.setDescription("Deliver cat to me");
//        randomJob1.setReceiverName("John Doe");
//        randomJob1.setReceiverAddress("123 Main St");
//        randomJob1.setReceiverPhone("555-1234");
//
//    }
//
//    @Test
//    void findByUserId() {
//        // given
//        Job givenJob = randomJob;
//        Job givenJob1 = randomJob1;
//
//        // when
//        jobRepository.save(givenJob);
//        jobRepository.save(givenJob1);
//        List<Job> foundJobs = jobRepository.findByUserId(givenJob.getUserId(), null);
//
//        // then
//        assertNotNull(foundJobs);
//        assertFalse(foundJobs.isEmpty());
//        assertTrue(foundJobs.contains(givenJob));
//        assertTrue(foundJobs.contains(givenJob1));
//    }

//    @Test
//    void findUserByEmail() throws Exception {
//        Optional<User> user = userRepository.findByEmail("{}")
//    }
}