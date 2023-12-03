package com.team08.csci205_final_project.model.DTO.Provider;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details required for registering a service provider")
public class ProviderRegister {
    /** Store the provider's current location */
    @NotNull
    @Schema(description = "Current geographical location of the provider")
    private GeoJsonPoint currentLocation;

    /** Vehicle details **/
    @NotBlank
    @Schema(description = "Details of the provider's vehicle", example = "Toyota Corolla 2019, White")
    private String vehicleDetails;

    /** National identification number of the user. */
    @NotBlank
    @Schema(description = "National identification number of the provider", example = "1234567890")
    private String nationalIdNumber;

    /** Driver's license number of the user. */
    @NotBlank
    @Schema(description = "Driver's license number of the provider", example = "DL1234567")
    private String driverLicense;

}