package com.webtech.NoteApp.autor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AutorWebController {

    private final AutorRepository autorRepository;

    public AutorWebController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("/autors")
    public String displayAuthors(Model model) {
        List<Autor> autors = autorRepository.findAll();
        model.addAttribute("autors", autors);
        return "autors"; // Correspond au fichier HTML à afficher
    }
}
