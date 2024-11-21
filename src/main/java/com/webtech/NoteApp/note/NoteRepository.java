package com.webtech.NoteApp.note;

import com.webtech.NoteApp.autor.Autor;
import com.webtech.NoteApp.categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    // Récupérer les notes par ID de catégorie
    List<NoteEntity> findByCategorieId(Long categorieId);

    // Récupérer les notes par ID d'auteur
    List<NoteEntity> findByAutorId(Long autorId);
}
