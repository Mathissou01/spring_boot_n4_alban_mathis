package com.webtech.NoteApp.note;

import com.webtech.NoteApp.autor.Autor;
import com.webtech.NoteApp.categorie.Categorie;
import jakarta.persistence.*;

@Entity
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String content;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Categorie categorie;

    // Constructeur sans argument (nécessaire pour JPA)
    public NoteEntity() {}

    // Constructeur avec les paramètres nécessaires
    public NoteEntity(String title, String content, Autor autor, Categorie categorie) {
        this.title = title;
        this.content = content;
        this.autor = autor;
        this.categorie = categorie;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
