package com.example.library;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.library.controller.AuthController;
import com.example.library.entities.Patron;
import com.example.library.service.PatronService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatronService patronService;

    @InjectMocks
    private AuthController authController;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testRegister() throws Exception {
        Patron patron = new Patron();
        patron.setId(1);
        patron.setName("Patron 1");
        patron.setUsername("patron");
        patron.setEmail("patron@patron.com");
        patron.setPhoneNumber("1234567890");
        patron.setPassword("password");


        doNothing().when(patronService).registerPatron(any(Patron.class));

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patron)))
                .andExpect(status().isOk())
                .andExpect(content().string("Register Successfully"));

        verify(patronService, times(1)).registerPatron(any(Patron.class));
    }

    @Test
    public void testLogin() throws Exception {
        Patron patron = new Patron();
        patron.setEmail("patron@patron.com");
        patron.setPassword("password123");


        doNothing().when(patronService).loginPatron(any(Patron.class));

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patron)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login Successfully"));

        verify(patronService, times(1)).loginPatron(any(Patron.class));
    }
}
