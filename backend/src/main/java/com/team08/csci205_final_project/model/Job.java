/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 11/2/2023
 * Time: 9:24 PM
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.model
 * Class: Job
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

/**
 * Represents a User entity in the system.
 */
@Document(collection = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    /** Unique identifier for the user */
    @Id
    private String id;

    /** Full name of the user */
    private String category;

    /** National ID of the provider user */
    private String providerID;

    /** Location of the job */
    private GeoJsonPoint jobLocation;

    /** Date of creation of the wish/request */
    private LocalDate createdDate;

    /** Date of the job/wish/request being fulfilled */
    private LocalDate endDate;

    /** Name of the receiver */
    private String receiverName;

    /** Address of the receiver */
    private String receiverAddress;

    /** Phone number of the receiver */
    private String receiverPhoneNo;

    /** Description */
    private String jobDesc;

    /** Price the provider user has to pay */
    private double itemPrice;

    /** Price the requesting user has to pay */
    private double totalPrice;

}
