package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.model.DTO.User.UserRegister;
import com.team08.csci205_final_project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testUserRegister() {
        UserRegister userRegister = new UserRegister();
        userRegister.setPassword("password");

        User user = new User();

        Mockito.when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.userRegister(new UserRegister());

        assertEquals("encodedPassword", result.getPassword());
        assertEquals(LocalDate.now(), result.getRegisterDate());
        verify(userRepository).save(any(User.class));
    }

}