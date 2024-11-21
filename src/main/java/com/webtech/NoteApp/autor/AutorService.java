package com.webtech.NoteApp.autor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor createAutor(String name) {
        Autor autor = new Autor(name);
        return autorRepository.save(autor);
    }

    public List<Autor> getAllAutors() {
        return autorRepository.findAll();
    }
}
