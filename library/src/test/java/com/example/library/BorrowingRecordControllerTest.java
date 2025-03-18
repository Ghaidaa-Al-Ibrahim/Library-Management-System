package com.example.library;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.library.controller.BorrowingRecordController;
import com.example.library.entities.BorrowingRecord;
import com.example.library.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BorrowingRecordControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowingRecordController).build();
    }

    @Test
    public void testBorrowingABook() throws Exception {
        long bookId = 1L;
        long patronId = 1L;


        BorrowingRecord borrowingRecord = new BorrowingRecord();

        when(borrowingRecordService.borrowingABook(bookId, patronId)).thenReturn(borrowingRecord);

        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book borrowed successfully"));

        verify(borrowingRecordService, times(1)).borrowingABook(bookId, patronId);
    }

    @Test
    public void testReturnABook() throws Exception {
        long bookId = 1L;
        long patronId = 1L;

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L); // Set other properties if needed

        when(borrowingRecordService.returnABook(bookId, patronId)).thenReturn(borrowingRecord);

        mockMvc.perform(put("/api/return/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        verify(borrowingRecordService, times(1)).returnABook(bookId, patronId);
    }

}
