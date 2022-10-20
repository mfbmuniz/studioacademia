package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.Exercise;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ExerciseRequest {

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String name;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String exerciseUrl;


    private String description;

    public ExerciseRequest() {
    }

    public ExerciseRequest(String name, String exerciseUrl, String description) {
        this.name = name;
        this.exerciseUrl = exerciseUrl;
        this.description = description;
    }

    public static Exercise toExercise(ExerciseRequest exercise) {

            return new Exercise(
                    exercise.name,
                    exercise.exerciseUrl,
                    exercise.description

            );

    }

}
