package io.github.agpaluch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "notes")
class Note {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @SequenceGenerator(name="seq",sequenceName="h2_database_seq")
    private int id;

    @NotBlank(message = "Title of the note must not be empty.")
    private String title;
    private String description;

    public Note() {
    }


}
