package com.webtech.NoteApp.note;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoteWebController {

    private final NoteService noteService;

    public NoteWebController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String displayNotes(Model model) {
        List<NoteEntity> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "notes"; // Correspond au nom du fichier HTML à afficher
    }
}
