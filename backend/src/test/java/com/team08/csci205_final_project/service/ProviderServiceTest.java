package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.service.ProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Provider.Provider;
import com.team08.csci205_final_project.repository.ProviderRepository;
import com.team08.csci205_final_project.service.JobService;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProviderServiceTest {

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private JobService jobService;

    @InjectMocks
    private ProviderService providerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void acceptJobWhenProviderExistsAndHasActiveJob() {
        String providerId = "providerId";
        String jobId = "jobId";
        Job activeJob = new Job();
        activeJob.setId(jobId);

        Provider provider = new Provider();
        provider.setActiveJob(activeJob);
        provider.setUserId(providerId);

        when(providerRepository.findById(providerId)).thenReturn(Optional.of(provider));
        when(jobService.acceptJob(jobId, providerId)).thenReturn(new Job());

        boolean result = providerService.acceptJob(providerId);

        assertTrue(result);
        verify(providerRepository).findById(providerId);
        verify(jobService).acceptJob(jobId, providerId);
    }

    @Test
    void acceptJobWhenProviderExistsButNoActiveJob() {
        String providerId = "providerId";

        Provider provider = new Provider();
        provider.setActiveJob(null);

        when(providerRepository.findById(providerId)).thenReturn(Optional.of(provider));

        boolean result = providerService.acceptJob(providerId);

        assertFalse(result);
        verify(providerRepository).findById(providerId);
        verify(jobService, never()).acceptJob(any(), any());
    }

    @Test
    void acceptJobWhenProviderDoesNotExist() {
        String providerId = "providerId";
        when(providerRepository.findById(providerId)).thenReturn(Optional.empty());

        boolean result = providerService.acceptJob(providerId);

        assertFalse(result);
        verify(providerRepository).findById(providerId);
        verify(jobService, never()).acceptJob(any(), any());
    }
}
