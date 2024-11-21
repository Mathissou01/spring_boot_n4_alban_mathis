package com.webtech.NoteApp.categorie;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping
    public Categorie createCategorie(@RequestBody CategorieRequest request) {
        return categorieService.createCategorie(request.getName());
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }
}
