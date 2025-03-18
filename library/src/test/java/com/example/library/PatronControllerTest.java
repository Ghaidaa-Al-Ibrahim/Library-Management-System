package com.example.library;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.library.controller.PatronController;
import com.example.library.entities.Patron;
import com.example.library.service.PatronService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;



public class PatronControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patronController).build();
    }
    @Test
    public void testGetAllPatrons() throws Exception {
        Patron patron1 = new Patron();
        patron1.setId(1);
        patron1.setName("Patron 1");
        patron1.setUsername("patron");
        patron1.setEmail("patron@patron.com");
        patron1.setPhoneNumber("1234567890");
        patron1.setPassword("password");

        List<Patron> patrons = Arrays.asList(patron1);

        when(patronService.getAllPatrons()).thenReturn(patrons);

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(patrons)));

        verify(patronService, times(1)).getAllPatrons();
    }

    @Test
    public void testGetPatronById() throws Exception {
        long patronId = 1L;
        Patron patron = new Patron();
        patron.setId(patronId);
        patron.setName("Patron 1");
        patron.setUsername("patron");
        patron.setEmail("patron@patron.com");
        patron.setPhoneNumber("1234567890");
        patron.setPassword("password");

        when(patronService.getPatronById(patronId)).thenReturn(patron);

        mockMvc.perform(get("/api/patrons/{id}", patronId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(patron)));

        verify(patronService, times(1)).getPatronById(patronId);
    }

    @Test
    public void testAddPatron() throws Exception {
        Patron patron = new Patron();
        patron.setName("Patron 1");
        patron.setUsername("patron");
        patron.setEmail("patron@patron.com");
        patron.setPhoneNumber("1234567890");
        patron.setPassword("password");
        when(patronService.addPatron(any(Patron.class))).thenReturn(patron);

        mockMvc.perform(post("/api/patrons")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(patron)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Patron created successfully"));

        verify(patronService, times(1)).addPatron(any(Patron.class));
    }

    @Test
    public void testDeletePatron() throws Exception {
        long patronId = 1L;
        doNothing().when(patronService).deletePatron(patronId);

        mockMvc.perform(delete("/api/patrons/{id}", patronId))
                .andExpect(status().isOk());

        verify(patronService, times(1)).deletePatron(patronId);
    }
}



