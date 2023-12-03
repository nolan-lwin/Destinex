package com.team08.csci205_final_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team08.csci205_final_project.model.Auth.LoginRequest;
import com.team08.csci205_final_project.security.JwtUtil;
import com.team08.csci205_final_project.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authController;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    private JSONObject jsonObject;

    private String loginRequest;

    private String invalidLoginRequest;
    private String fakeToken;

    @BeforeEach
    public void setUp() throws JSONException {
        loginRequest = new JSONObject()
                .put("username", "hqp@bucknell.edu")
                .put("password", "1234")
                .toString();

        invalidLoginRequest = new JSONObject()
                .put("email", "hqp@bucknell.edu")
                .put("password", "1234")
                .toString();

        fakeToken = "fake.jwt.token";
        Mockito.when(jwtUtil.generateToken(any())).thenReturn(fakeToken);
        Mockito.when(userService.getFullNameByUsername(any())).thenReturn("Hung");
    }

    /**
     * Test if the auth controller return OK status
     * with right schema or not
     * @throws Exception
     */
    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value(fakeToken))
                .andExpect(jsonPath("$.email").value("hqp@bucknell.edu"))
                .andExpect(jsonPath("$.fullName").value("Hung"));
    }

    /**
     * Test if the controller accept wrong schema or not
     * @throws Exception
     */
    @Test
    public void testLoginWithInvalidSchema() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidLoginRequest))
                .andExpect(status().isBadRequest());
    }
}