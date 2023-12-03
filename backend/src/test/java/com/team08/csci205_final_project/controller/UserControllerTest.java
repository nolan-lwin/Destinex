package com.team08.csci205_final_project.controller;

import org.json.JSONObject;
import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;
    private User mockUser;

    @BeforeEach
    public void setUp(WebApplicationContext wac) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        mockUser = new User();
        mockUser.setFirstName("Hung");
        mockUser.setLastName("Pham");
        mockUser.setEmail("test@t.com");
        mockUser.setPassword("fuc");
        mockUser.setId("user123");

    }

    /**
     * Test if the user register receive the correct schema
     * to return OK or not
     * @throws Exception
     */
    @Test
    public void createUserTest() throws Exception {

        Mockito.when(userService.findUserByEmail(any())).thenReturn(Optional.empty());

        JSONObject userJson = new JSONObject()
                .put("firstName", "Hung")
                .put("lastName", "Pham")
                .put("email", "test@t.com")
                .put("password", "fuc");

        when(userService.userRegister(any())).thenReturn(mockUser);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@t.com"));
    }

    /**
     * Test this endpoint return correct status and content or not
     * @throws Exception
     */
    @Test
    public void getCurrentUserInfoTest() throws Exception {
        Mockito.when(userService.getCurrentUserId()).thenReturn(mockUser.getId());
        Mockito.when(userService.findUserById(mockUser.getId())).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/api/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockUser.getId()));
    }

    /**
     * Test this endpoint return correct status and content or not
     * @throws Exception
     */
    @Test
    public void getUserPublicInfoTest() throws Exception {
        Mockito.when(userService.findUserById(mockUser.getId())).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/api/users/" + mockUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockUser.getId()));
    }

    /**
     * Test this endpoint return correct status and content or not
     * @throws Exception
     */
    @Test
    public void updateUserTest() throws Exception {
        JSONObject userJson = new JSONObject()
                .put("firstName","lol")
                .put("lastName", "lol123")
                .put("email", "updated@example.com")
                .put("password", "newpassword123"); // Add other fields as needed

        mockUser.setEmail("updated@example.com");

        Mockito.when(userService.updateUser(any())).thenReturn(mockUser);

        mockMvc.perform(put("/api/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    /**
     * Test this endpoint return correct status and content or not
     * @throws Exception
     */
    @Test
    public void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUser();

        mockMvc.perform(delete("/api/users/me"))
                .andExpect(status().isNoContent());
    }


}