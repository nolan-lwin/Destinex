/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 02/11/2023
 * Time: 15:05
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.controller
 * Class: UserController
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.controller;

import com.team08.csci205_final_project.exception.DuplicateAccountException;
import com.team08.csci205_final_project.exception.ResourceNotFoundException;
import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.model.DTO.User.UserRegister;
import com.team08.csci205_final_project.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /** API endpoint to create a new user */
    @Operation(summary = "Register an user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Register Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})
    })
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRegister userRegister) {
        if (userService.findUserByEmail(userRegister.getEmail()).isEmpty())
            return ResponseEntity.ok(userService.userRegister(userRegister));
        else {
            throw new DuplicateAccountException("You already signed up. Please log in");
        }
    }

    /** API endpoint to get all users' information */
    @Hidden
    @GetMapping("/admin/all")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    /** API endpoint to get user's information based on userId */
    @Operation(summary = "Get current user info. User info from this API contains personal info")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<User> getCurrentUserInfo() {
        String id = userService.getCurrentUserId();
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
    }

    /** API endpoint to get user's information based on userId */
    @Operation(summary = "Get user from User ID. Info from this API is public info")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<User> getUserPublicInfo(
            @Parameter(description = "User ID needed to see info") @PathVariable String id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
    }


    /** API endpoint to get user's information based on email */
    @Hidden
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** API endpoint to edit user's information */
    @Operation(summary = "Update this user register information")
    @PutMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody UserRegister userRegister) {
        return ResponseEntity.ok(userService.updateUser(userRegister));
    }

    /** API endpoint to delete a user based on userId */
    @Operation(summary = "Delete this user information")
    @DeleteMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }

}
