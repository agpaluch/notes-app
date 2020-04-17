package io.github.agpaluch.controller;


import io.github.agpaluch.model.Note;
import io.github.agpaluch.model.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/notes")
class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteRepository repository;

    NoteController(final NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Note>> readAllNotes(){
        logger.warn("Exposing all tasks from the repository.");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping
    ResponseEntity<List<Note>> readAllNotesPaged(Pageable page){
        logger.warn("Read paged tasks from the repository.");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("/{id}")
    ResponseEntity<Note> readTask(@PathVariable int id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Note> createTask(@RequestBody @Valid Note toCreate){
        Note savedNote = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/"+savedNote.getId())).body(savedNote);
    }

    @PutMapping("/{id}")
    ResponseEntity<Note> updateTask(@PathVariable int id, @RequestBody @Valid Note toUpdate){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        Note updatedNote = repository.save(toUpdate);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Note> deleteTask(@PathVariable int id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }


}
