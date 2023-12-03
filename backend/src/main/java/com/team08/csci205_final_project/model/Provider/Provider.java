/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 11/11/2023
 * Time: 13:40
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.model
 * Class: Provider
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.model.Provider;

import com.team08.csci205_final_project.model.Job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/** Represents the Provider object in the database */
@Document(collection = "providers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    /** Store Provider ID **/
    @Id
    private String providerId;

    /** Store userID information of the provider */
    private String userId;

    /** Store email information of the provider */
    private String email;

    /** Store the jobs that the provider have taken */
    private ArrayList<Job> jobHistory;

    /** Store the provider's current location */
    private GeoJsonPoint currentLocation;

    /** Store the wait-for-response job */
    private String activeJob;

    /** Store status of the provider */
    private boolean providerAvail;

    /** Vehicle details **/
    private String vehicleDetails;

    /** National identification number of the user. */
    private String nationalIdNumber;

    /** Picture or image of the user's national ID. */
    private String nationalIdPicture;

    /** Driver's license number of the user. */
    private String driverLicense;

    /** Rating or score associated with the user based on reviews or feedback. */
    private Double rating;
}
