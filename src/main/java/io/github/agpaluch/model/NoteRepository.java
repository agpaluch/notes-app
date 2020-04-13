package io.github.agpaluch.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Note note);

    @RestResource(path = "findByTitle", rel = "title")
    List<Note> findByTitle(@Param("title") String title);

}
