package com.team08.csci205_final_project.model.DTO.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Information will be used for new account register")
public class UserRegister {
    /** First name of the user */
    @NotBlank
    @Schema(description = "First name of user", example = "Hung")
    private String firstName;

    /** Last name of the user */
    @NotBlank
    @Schema(description = "Last name of the user", example = "Pham")
    private String lastName;

    /** Email address of the user */
    @Email
    @NotBlank
    @Schema(description = "Email address of the user", example = "hqp001@bucknell.edu")
    private String email;

    /** Password of the user */
    @NotBlank
    @Schema(description = "Password of the user", example = "lol123")
    private String password;
}
