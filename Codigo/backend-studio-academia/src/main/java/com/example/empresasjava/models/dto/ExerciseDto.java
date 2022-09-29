package com.example.empresasjava.models.dto;


import com.example.empresasjava.models.Exercise;
import lombok.Data;

import java.util.Date;

@Data
public class ExerciseDto {

    private Long exercise_id;
    private String name;
    private String exerciseUrl;

    private String description;
    private Date createdAt;
    private Date deletedAt;

    public ExerciseDto(Long exercise_id, String name, String exerciseUrl, String description) {
        this.exercise_id = exercise_id;
        this.name = name;
        this.exerciseUrl = exerciseUrl;
        this.description = description;
    }


    public ExerciseDto(Long exercise_id, String name, String exerciseUrl, Date createdAt, Date deletedAt, String description) {
        this.exercise_id = exercise_id;
        this.name = name;
        this.exerciseUrl = exerciseUrl;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.description = description;

    }

    public static ExerciseDto fromExercise(Exercise exercise){
        return new ExerciseDto(exercise.getExerciseId(), exercise.getName(), exercise.getExerciseUrl(), exercise.getDescription());
    }
}

