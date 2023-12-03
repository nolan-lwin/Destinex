package com.team08.csci205_final_project.model.DTO.Job;

import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Job.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobInfo {
    /** Unique identifier for the job. */
    @Id
    private String id;

    /** Identifier of the service provider. */
    private String providerId;

    /** Email of the service provider. */
    private String providerEmail;

    /** Identifier of the transaction associated with this job. */
    private String transactionId;

    /** The date when the job was created. */
    private LocalDate createdDate;

    /** The date when the job is set to end. */
    private LocalDate endDate;

    /** The category of the job. */
    private String category;

    /** A description of the job. */
    private String description;

    /** The price of the item or service offered. */
    private Double itemPrice;

    /** The total price of the job, including additional costs. */
    private Double totalPrice;

    /** The name of the receiver of the job's output. */
    private String receiverName;

    /** The address of the receiver of the job's output. */
    private String receiverAddress;

    /** The phone number of the receiver of the job's output. */
    private String receiverPhone;

    /** The email of the receiver of the job's output. */
    private String receiverEmail;

    /** The overall status of the job. */
    private JobStatus status;

    public static List<JobInfo> convertToUserDtoList(List<Job> jobs) {
        List<JobInfo> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            JobInfo dto = new JobInfo();
            BeanUtils.copyProperties(job, dto);
            jobDTOs.add(dto);
        }
        return jobDTOs;
    }

}
