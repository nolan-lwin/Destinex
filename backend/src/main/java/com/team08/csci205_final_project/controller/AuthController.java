/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 02/11/2023
 * Time: 15:11
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.controller
 * Class: AuthController
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.controller;

import com.team08.csci205_final_project.model.Auth.Role;
import com.team08.csci205_final_project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.team08.csci205_final_project.model.Auth.LoginRequest;
import com.team08.csci205_final_project.model.Auth.LoginResponse;
import com.team08.csci205_final_project.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Operation(summary = "Login to an account",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"username\":\"hqp001@bucknell.edu\",\"password\":1234}"
                    ))
            )
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        final String jwt = jwtUtil.generateToken(authenticate);
        final String fullName = userService.getFullNameByUsername(loginRequest.getUsername());
        final Role role = userService.getRoleByUsername(loginRequest.getUsername());
        final String email = loginRequest.getUsername();

        return ResponseEntity.ok(new LoginResponse(jwt, fullName, email, role));
    }
}
