package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserExerciseResponse {

    private Long userFileId;
    private UserDto user;
    private List<ExerciseResponse> exercises;
    private Integer serie;
    private Integer repetitions;

    public UserExerciseResponse() {
    }

    public UserExerciseResponse(Long userFileId, UserDto user, List<ExerciseResponse> exercises, Integer serie, Integer repetitions) {
        this.userFileId = userFileId;
        this.user = user;
        this.exercises = exercises;
        this.serie = serie;
        this.repetitions = repetitions;
    }

}
