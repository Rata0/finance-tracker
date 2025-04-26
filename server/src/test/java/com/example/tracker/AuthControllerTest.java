package com.example.tracker;

import com.example.tracker.dto.LoginDTO;
import com.example.tracker.dto.RegistrationDTO;
import com.example.tracker.model.User;
import com.example.tracker.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
public class AuthControllerTest {
    private static final String BASE_API_PATH = "/api/v1/auth";
    private static final String REGISTRATION_PATH = BASE_API_PATH + "/registration";
    private static final String LOGIN_PATH = BASE_API_PATH + "/login";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        RegistrationDTO dto = new RegistrationDTO();
        dto.setEmail("rata0@google.com");
        dto.setUsername("rata0");
        dto.setPassword("password123");

        mockMvc.perform(post(REGISTRATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        User savedUser = userRepository.findByUsername(dto.getUsername());
        assertNotNull(savedUser);
        assertEquals(dto.getUsername(), savedUser.getUsername());
        assertTrue(passwordEncoder.matches(dto.getPassword(), savedUser.getPassword()));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail("rata0@google.com");
        registrationDTO.setUsername("rata0");
        registrationDTO.setPassword("password123");

        mockMvc.perform(post(REGISTRATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDTO)))
                .andExpect(status().isCreated());

        LoginDTO loginDto = new LoginDTO();
        loginDto.setUsername("rata0");
        loginDto.setPassword("password123");

        var result = mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertNotNull(body);
        assertFalse(body.isEmpty());
    }

}
