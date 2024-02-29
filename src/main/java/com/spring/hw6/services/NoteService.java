package com.spring.hw6.services;

import com.spring.hw6.model.Note;
import com.spring.hw6.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note addNote(Note note){
        if(note.getCreationTime() == null) {
            note.setCreationTime(LocalDateTime.now());
        }
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id){
        return noteRepository.findById(id);
    }

    public Note editNote(Long id, Note note) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note oldNote = optionalNote.get();
            oldNote.setHeader(note.getHeader());
            oldNote.setBody(note.getBody());
            return noteRepository.save(oldNote);
        } else {
            throw new IllegalArgumentException("Note not found with id: " + id);
        }
    }

    public boolean deleteNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
