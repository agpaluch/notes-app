package io.github.agpaluch.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface NoteRepository {

    List<Note> findAll();

    Page<Note> findAll(Pageable pageable);

    boolean existsById(Integer integer);

    Optional<Note> findById(Integer integer);

    Note save(Note entity);

    List<Note> findByTitle(@Param("title") String title);

    void deleteById(Integer id);

}
