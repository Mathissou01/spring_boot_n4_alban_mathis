package com.webtech.NoteApp.categorie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategorieWebController {

    private final CategorieRepository categorieRepository;

    public CategorieWebController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/categories")
    public String displayCategories(Model model) {
        List<Categorie> categories = categorieRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories"; // Correspond au fichier HTML à afficher
    }
}
