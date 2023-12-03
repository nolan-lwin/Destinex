package com.team08.csci205_final_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team08.csci205_final_project.config.SecurityConfig;
import com.team08.csci205_final_project.model.Transaction.Transaction;
import com.team08.csci205_final_project.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@WebMvcTest(value = TransactionController.class)
class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp(WebApplicationContext wac) {
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void createTransaction() throws Exception {
        Transaction transaction = new Transaction();
        given(transactionService.addTransaction(transaction)).willReturn(transaction);

        mockMvc.perform(post("/api/transactions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(transaction)));
    }

    @Test
    void getAllTransactions() throws Exception {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        given(transactionService.findAllTransactions()).willReturn(Arrays.asList(transaction1, transaction2));

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
    @Test
    void getTransactionByJobId() throws Exception {
        String jobId = "job123";
        Transaction transaction = new Transaction();
        given(transactionService.findTransactionByJobId(jobId)).willReturn(Optional.of(transaction));

        mockMvc.perform(get("/api/transactions/job_id/" + jobId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(transaction)));
    }

    @Test
    void deleteTransaction() throws Exception {
        String transactionId = "123";

        mockMvc.perform(delete("/api/transactions/" + transactionId))
                .andExpect(status().isNoContent());
    }

}
