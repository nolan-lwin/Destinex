package com.team08.csci205_final_project.model.User;

import com.team08.csci205_final_project.model.Auth.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

/**
 * Represents a User entity in the system.
 */
@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Schema(description = "Unique identifier for the user", example = "12345abcde")
    private String id;

    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Password of the user", example = "SecureP@ssw0rd!")
    private String password;

    @Schema(description = "Date of birth of the user", example = "1990-01-01")
    private LocalDate dateOfBirth;

    @Schema(description = "Register date of the user", example = "2023-01-01")
    private LocalDate registerDate;

    @Schema(description = "Geospatial location of the user", example = "{\"type\": \"Point\", \"coordinates\": [-74.005974, 40.712776]}")
    private GeoJsonPoint location;

    @Schema(description = "Role of the user in the system", example = "ROLE_USER")
    private Role role;
}
