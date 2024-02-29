package com.spring.hw6.controllers;

import com.spring.hw6.model.Note;
import com.spring.hw6.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAll(){
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable("id") Long id){
        return new ResponseEntity<>(noteService.getNoteById(id).orElseThrow(), HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody Note note){
        return new ResponseEntity<>(noteService.editNote(id, note), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id){
        if (noteService.deleteNote(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
