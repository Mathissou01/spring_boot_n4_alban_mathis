package com.webtech.NoteApp.note;

import com.webtech.NoteApp.autor.Autor;
import com.webtech.NoteApp.autor.AutorRepository;
import com.webtech.NoteApp.categorie.Categorie;
import com.webtech.NoteApp.categorie.CategorieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    private final NoteRepository noteRepository = mock(NoteRepository.class);
    private final AutorRepository autorRepository = mock(AutorRepository.class);
    private final CategorieRepository categorieRepository = mock(CategorieRepository.class);

    private final NoteService noteService = new NoteService(noteRepository, autorRepository, categorieRepository);

    @Test
    void createNote_shouldReturnCreatedNote() {
        // Arrange
        Autor mockAutor = new Autor("Author Test");
        mockAutor.setId(1L);

        Categorie mockCategorie = new Categorie("Category Test");
        mockCategorie.setId(1L);

        NoteEntity mockNote = new NoteEntity("Test Title", "Test Content", mockAutor, mockCategorie);

        when(autorRepository.findById(1L)).thenReturn(Optional.of(mockAutor));
        when(categorieRepository.findById(1L)).thenReturn(Optional.of(mockCategorie));
        when(noteRepository.save(Mockito.any(NoteEntity.class))).thenReturn(mockNote);

        // Act
        NoteEntity createdNote = noteService.createNote("Test Title", "Test Content", 1L, 1L);

        // Assert
        assertNotNull(createdNote);
        assertEquals("Test Title", createdNote.getTitle());
        assertEquals("Test Content", createdNote.getContent());
        assertEquals(mockAutor, createdNote.getAutor());
        assertEquals(mockCategorie, createdNote.getCategorie());

        verify(autorRepository, times(1)).findById(1L);
        verify(categorieRepository, times(1)).findById(1L);
        verify(noteRepository, times(1)).save(any(NoteEntity.class));
    }

    @Test
    void getAllNotes_shouldReturnListOfNotes() {
        // Arrange
        List<NoteEntity> notes = Arrays.asList(
                new NoteEntity("Title 1", "Content 1", null, null),
                new NoteEntity("Title 2", "Content 2", null, null)
        );

        when(noteRepository.findAll()).thenReturn(notes);

        // Act
        List<NoteEntity> allNotes = noteService.getAllNotes();

        // Assert
        assertNotNull(allNotes);
        assertEquals(2, allNotes.size());
        assertEquals("Title 1", allNotes.get(0).getTitle());
        assertEquals("Content 1", allNotes.get(0).getContent());

        verify(noteRepository, times(1)).findAll();
    }
}
