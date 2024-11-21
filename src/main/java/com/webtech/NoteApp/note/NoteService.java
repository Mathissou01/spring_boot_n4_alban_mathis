package com.webtech.NoteApp.note;

import com.webtech.NoteApp.autor.Autor;
import com.webtech.NoteApp.autor.AutorRepository;
import com.webtech.NoteApp.categorie.Categorie;
import com.webtech.NoteApp.categorie.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final AutorRepository autorRepository;
    private final CategorieRepository categorieRepository;

    public NoteService(NoteRepository noteRepository, AutorRepository autorRepository, CategorieRepository categorieRepository) {
        this.noteRepository = noteRepository;
        this.autorRepository = autorRepository;
        this.categorieRepository = categorieRepository;
    }

    // Méthode pour récupérer les notes par catégorie
    public List<NoteEntity> getNotesByCategorieId(Long categorieId) {
        return noteRepository.findByCategorieId(categorieId);
    }

    // Méthode pour récupérer les notes par auteur
    public List<NoteEntity> getNotesByAutorId(Long autorId) {
        return noteRepository.findByAutorId(autorId);
    }

    public NoteEntity createNote(String title, String content, Long autorId, Long categorieId) {
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return noteRepository.save(new NoteEntity(title, content, autor, categorie));
    }

    public void createNotes(List<NoteEntity> notes) {
        noteRepository.saveAll(notes);
    }

    public List<NoteEntity> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<NoteEntity> getNoteById(Long id) {
        return noteRepository.findById(id); // Cette méthode est utilisée dans le controller
    }

    public Optional<NoteEntity> updateNote(Long id, String title, String content) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(title);
            note.setContent(content);
            return noteRepository.save(note);
        });
    }

    public boolean deleteNoteById(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllNotes() {
        noteRepository.deleteAll();
    }
}
