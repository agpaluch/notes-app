package io.github.agpaluch.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlNoteRepository extends NoteRepository, JpaRepository<Note, Integer> {

    @Override
    void deleteById(Integer id);
}
