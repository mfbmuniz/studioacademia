package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserExerciseRequest {

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    long exerciseId;

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    long fileId;

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    User user;

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    Integer series;

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    Integer repetitions;
}
