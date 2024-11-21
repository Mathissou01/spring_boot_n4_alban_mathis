package com.webtech.NoteApp.autor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autors")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public Autor createAutor(@RequestBody AutorRequest request) {
        return autorService.createAutor(request.getName());
    }

    @GetMapping
    public List<Autor> getAllAutors() {
        return autorService.getAllAutors();
    }
}
