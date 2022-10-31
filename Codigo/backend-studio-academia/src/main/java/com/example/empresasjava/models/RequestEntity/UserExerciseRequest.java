package com.example.empresasjava.models.RequestEntity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserExerciseRequest {

    @NotNull(message = "Id de exercicio não pode ser nulo")
    private Long exerciseId;

    @NotNull(message = "Id da ficha não pode ser nulo")
    private Long fileId;

    @NotNull(message = "Id do usuário não pode ser nulo")
    private Long userId;

    @NotNull(message = "Quantidade de series não pode ser nulo")
    private Integer series;

    @NotNull(message = "Quantidade de repetições não pode ser nulo")
    private Integer repetition;
}
