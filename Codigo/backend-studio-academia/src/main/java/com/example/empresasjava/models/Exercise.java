package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercises_id")
    private Long exerciseId;

    @Column(name = "exercise_name")
    private String name ;

    @Column(name = "exercise_link")
    private String exerciseUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Exercise() {

    }

    public Exercise(String name, String exerciseUrl, String description) {
        this.name = name;
        this.exerciseUrl = exerciseUrl;
        this.description = description;


    }

    public Exercise(String name, String exerciseUrl, String description, Long idExercise) {
        this.name = name;
        this.exerciseUrl = exerciseUrl;
        this.description = description;
        this.exerciseId=idExercise;
    }
}
