package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.enums.SexEnum;
import com.example.empresasjava.models.Address;
import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.Role;
import com.example.empresasjava.models.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class ExerciseRequest {

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String name;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String exerciseUrl;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
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
