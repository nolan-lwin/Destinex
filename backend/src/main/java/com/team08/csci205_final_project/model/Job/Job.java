/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Pham
 * Section: 10AM
 * Date: 11/7/2023
 * Time: 10:55 AM
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.model
 * Class: Job
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.model.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * The {@code Job} class represents a job entity in the system.
 * It contains all the necessary information about a job, including details
 * about the service provider, transaction, and the user.
 * This class is annotated with Lombok annotations to generate boilerplate code
 * such as getters, setters, and constructors.
 * It is also mapped to the "jobs" collection in MongoDB using Spring Data's {@code @Document} annotation.
 */
@Document(collection = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    /** Unique identifier for the job. */
    @Id
    private String id;

    /** Identifier of the service provider. */
    private String providerId;

    /** Email of the service provider. */
    private String providerEmail;

    /** Identifier of the transaction associated with this job. */
    private String transactionId;

    /** Identifier of the user who posted the job. */
    private String userId;

    /** Email of the service provider. */
    private String userEmail;

    /** Identifier of the chat associated with this job. */
    private String chatId;

    /** The date when the job was created. */
    private LocalDate createdDate;

    /** The date when the job is set to end. */
    private String endDate;

    /** The name of the product/service. */
    private String productName;

    /** The category of the job. */
    private String category;

    /** A description of the job. */
    private String description;

    /** The price of the item or service offered. */
    private String itemPrice;

    /** The total price of the job, including additional costs. */
    private Double totalPrice;

    /** The name of the receiver of the job's output. */
    private String receiverName;

    /** The address of the receiver of the job's output. */
    private String receiverAddress;

    /** Convert the receiver address to longitude and latitude */
    private GeoJsonPoint receiverAddressPoint;

    /** The phone number of the receiver of the job's output. */
    private String receiverPhone;

    /** The email of the receiver of the job's output. */
    private String receiverEmail;

    /** The status of the job from the user's perspective. */
    private int userStatus;

    /** The status of the job from the provider's perspective. */
    private int providerStatus;

    /** The overall status of the job. */
    private JobStatus status;

    /** Flag to indicate if the job has been deleted. */
    private boolean IS_DELETED;

    // MIGHT NEED TO ADD MORE FIELDS ACCORDING TO MAKE-A-WISH PAGE
}