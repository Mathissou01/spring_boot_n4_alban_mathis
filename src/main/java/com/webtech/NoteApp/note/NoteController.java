package com.webtech.NoteApp.note;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public NoteEntity createNote(@RequestBody NoteRequest request) {
        return noteService.createNote(
                request.getTitle(),
                request.getContent(),
                request.getAutorId(),
                request.getCategorieId()
        );
    }

    @GetMapping
    public List<NoteEntity> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public NoteEntity getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with ID: " + id));
    }

    @PutMapping("/{id}")
    public NoteEntity updateNote(@PathVariable Long id, @RequestBody NoteRequest request) {
        return noteService.updateNote(id, request.getTitle(), request.getContent())
                .orElseThrow(() -> new RuntimeException("Failed to update note with ID: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        if (!noteService.deleteNoteById(id)) {
            throw new RuntimeException("Note not found with ID: " + id);
        }
    }

    // Endpoint pour récupérer les notes par ID de catégorie
    @GetMapping("/categorie/{categorieId}")
    public List<NoteEntity> getNotesByCategorieId(@PathVariable Long categorieId) {
        return noteService.getNotesByCategorieId(categorieId);
    }

    // Endpoint pour récupérer les notes par ID d'auteur
    @GetMapping("/autor/{autorId}")
    public List<NoteEntity> getNotesByAutorId(@PathVariable Long autorId) {
        return noteService.getNotesByAutorId(autorId);
    }

    @DeleteMapping
    public void deleteAllNotes() {
        noteService.deleteAllNotes();
    }
}
