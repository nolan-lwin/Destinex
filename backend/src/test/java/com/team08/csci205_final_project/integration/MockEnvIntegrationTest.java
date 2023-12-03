/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Pham
 * Section: 10AM
 * Date: 11/16/2023
 * Time: 2:45 AM
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.integration
 * Class: MockEnvIntegrationTest
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team08.csci205_final_project.model.DTO.Job.NewJobRequest;
import com.team08.csci205_final_project.model.Job.Job;
import org.hamcrest.core.StringContains;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@ExtendWith({SpringExtension.class})
public class MockEnvIntegrationTest {

    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;

    private Job job;

    private ObjectMapper objectMapper;
    private NewJobRequest newJobRequest;

    @BeforeEach
    public void setUp(WebApplicationContext wac) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();

        newJobRequest = new NewJobRequest("testUser1", "For fun", "Deliver this to my friend",
                new GeoJsonPoint(1, 2), "123 Main Str", "123-456");

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateJob() throws Exception {
//        System.out.println(a);
        MvcResult result = mockMvc.perform(post("/api/jobs")
                .contentType(MediaType.APPLICATION_JSON)

                .content(new JSONObject().put("userId", "testUser1")
                        .put("category", "Test")
                        .put("description", "For test only")
                        .put("receiverName", "John Doe")
                        .put("receiverAddressPoint",
                                new JSONObject(objectMapper.writeValueAsString(
                                        new GeoJsonPoint(1.1, 2.2))
                                )
                        )
                        .put("receiverPhone", "123-456")
                        .toString())
                )

                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        header().string("Location",
                                new StringContains("/api/jobs/"))
//                        content().json(objectMapper.writeValueAsString(job), false)
                )
                .andReturn();

//        String location = result.getResponse().getHeader("Location");
//
//        mockMvc.perform(get(location))
//                .andExpectAll(
//                        status().isOk(),
//                        content().json(objectMapper.writeValueAsString(job))
//                );
//    }

//    @Test
//    public void testAcceptJobSuccess() throws Exception {
//        String providerId = "providerTest1"; // Assumed existing provider ID
//
//        mockMvc.perform(post("/api/providers/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content())
//                        .andExpect(status().isOk());
//
//        mockMvc.perform(post("/api/providers/" + providerId + "/accept")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//         Additional assertions can be made here
    }
//
//    @Test
//    public void testAcceptJobFailure() throws Exception {
//        String nonExistentProviderId = "nonExistentProviderId";
//        mockMvc.perform(post("/api/providers/" + nonExistentProviderId + "/accept")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
}