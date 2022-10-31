package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.UserExercises;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;

@Data
public class UserExerciseListRequest {

    @NotNull(message = "Id da ficha não pode ser nulo")
    private Long fileId;

    @NotNull(message = "Id da ficha não pode ser nulo")
    private String fileName;

    @NotNull(message = "Id da ficha não pode ser nulo")
    private LinkedList<UserExercises> fileExercises;

}
