package com.webtech.NoteApp.note;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NoteControllerTest {

    private final NoteService noteService = mock(NoteService.class);
    private final NoteController noteController = new NoteController(noteService);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();

    @Test
    void createNote_shouldReturnCreatedNote() throws Exception {
        // Arrange
        NoteEntity note = new NoteEntity("Test Title", "Test Content");
        when(noteService.createNote("Test Title", "Test Content")).thenReturn(note);

        // Act & Assert
        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Title\",\"content\":\"Test Content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"));

        verify(noteService, times(1)).createNote("Test Title", "Test Content");
    }

    @Test
    void getAllNotes_shouldReturnListOfNotes() throws Exception {
        // Arrange
        when(noteService.getAllNotes()).thenReturn(Arrays.asList(
                new NoteEntity("Title 1", "Content 1"),
                new NoteEntity("Title 2", "Content 2")
        ));

        // Act & Assert
        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[1].content").value("Content 2"));

        verify(noteService, times(1)).getAllNotes();
    }
}
