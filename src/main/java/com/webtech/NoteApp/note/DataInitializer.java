package com.webtech.NoteApp.note;

import com.github.javafaker.Faker;
import com.webtech.NoteApp.autor.Autor;
import com.webtech.NoteApp.autor.AutorRepository;
import com.webtech.NoteApp.categorie.Categorie;
import com.webtech.NoteApp.categorie.CategorieRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private final NoteService noteService;
    private final AutorRepository autorRepository;
    private final CategorieRepository categorieRepository;
    private final Faker faker;

    public DataInitializer(NoteService noteService, AutorRepository autorRepository, CategorieRepository categorieRepository) {
        this.noteService = noteService;
        this.autorRepository = autorRepository;
        this.categorieRepository = categorieRepository;
        this.faker = new Faker();
    }

    @PostConstruct
    public void init() {
        if (categorieRepository.count() == 0) {
            createCategories();
        }
        if (autorRepository.count() == 0) {
            createAuthors();
        }
        createNotes();
    }

    private void createCategories() {
        List<String> categoryNames = List.of("Science", "Literature", "History", "Technology");
        List<Categorie> categories = new ArrayList<>();
        for (String name : categoryNames) {
            categories.add(new Categorie(name));
        }
        categorieRepository.saveAll(categories);
        System.out.println("Categories have been created!");
    }

    private void createAuthors() {
        List<Autor> authors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            authors.add(new Autor(faker.name().fullName()));
        }
        autorRepository.saveAll(authors);
        System.out.println("Authors have been created!");
    }

    private void createNotes() {
        List<Autor> authors = autorRepository.findAll();
        List<Categorie> categories = categorieRepository.findAll();

        if (authors.isEmpty() || categories.isEmpty()) {
            System.out.println("No authors or categories found in the database.");
            return;
        }

        Random random = new Random();
        List<NoteEntity> notes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String title = faker.lorem().sentence(5);
            String content = faker.lorem().paragraph(3);

            // Ensure the strings do not exceed 255 characters
            title = title.length() > 255 ? title.substring(0, 255) : title;
            content = content.length() > 255 ? content.substring(0, 255) : content;

            Autor autor = authors.get(random.nextInt(authors.size()));
            Categorie categorie = categories.get(random.nextInt(categories.size()));
            notes.add(new NoteEntity(title, content, autor, categorie));
        }

        noteService.createNotes(notes);
        System.out.println("10 random notes have been generated and initialized!");
    }

}
