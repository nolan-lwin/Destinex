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
 * Package: com.team08.csci205_final_project.service
 * Class: UserService
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.exception.ResourceNotFoundException;
import com.team08.csci205_final_project.model.Auth.Role;
import com.team08.csci205_final_project.model.User.CustomUserDetails;
import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.model.DTO.User.UserRegister;
import com.team08.csci205_final_project.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    /** A BcryptPassword Encoder object to encode the password */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    /** Add a new user to the database */
    public User userRegister(UserRegister userRegister) {
        User user = new User();
        BeanUtils.copyProperties(userRegister, user);

        // Set up initial state
        user.setRegisterDate(LocalDate.now());
        user.setRole(Role.ROLE_USER);
        // Encode the user password to store in the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    /** Find a user based on userId */
    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    /** Find a user based on their email */
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /** Return all users in the database */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /** Update a user's information */
    public User updateUser(UserRegister userRegister) {
        String id = getCurrentUserId();
        User user = findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BeanUtils.copyProperties(userRegister, user);

        user.setRegisterDate(LocalDate.now());
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    /** Delete a user based on their userId */
    public void deleteUser() {
        String userId = getCurrentUserId();
        userRepository.deleteById(userId);
    }

    /** Find a user's full name based on their username */
    public String getFullNameByUsername(String username) {
        Optional<User> userOpt = userRepository.findByEmail(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getFirstName() + " " + user.getLastName();
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }

    /**
     * Get user name from request
     * @return userId
     * @throws AccessDeniedException if there is no authentication from user
     */
    public String getCurrentUserId() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User is not authenticated");
        }
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) principal;
            return customUserDetails.getUserId();
        } else {
            throw new AccessDeniedException(principal.toString());
        }
    }

    /**
     * Get username from current authentication
     * @return username
     * @throws AccessDeniedException if there is no authentication
     */
    public String getCurrentUserName() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new org.springframework.security.access.AccessDeniedException("User is not authenticated");
        }
        return authentication.getName();
    }

    /**
     * Get role from current username
     * @return role
     * @throws RuntimeException if there is no username
     */
    public Role getRoleByUsername(String username) {
        Optional<User> userOpt = userRepository.findByEmail(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getRole();
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }
}
