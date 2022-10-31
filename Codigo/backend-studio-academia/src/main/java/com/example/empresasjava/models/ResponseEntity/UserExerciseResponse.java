package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import com.example.empresasjava.models.dto.UserDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class UserExerciseResponse {

    private Long userExercisesId;
    private UserFile userFile;
    private Exercise exercises;
    private Integer series;
    private Integer repetition;
    private UserDto user;




    public UserExerciseResponse() {
    }

    public UserExerciseResponse(UserFile userFile, UserDto user, Exercise exercises, Integer series, Integer repetition, Long userExercisesId) {
        this.userFile = userFile;
        this.user = user;
        this.exercises = exercises;
        this.series = series;
        this.repetition = repetition;
        this.userExercisesId = userExercisesId;
    }

    public UserExerciseResponse(UserFile userFile, UserDto user, Exercise exercises, Integer series, Integer repetition) {
        this.userFile = userFile;
        this.user = user;
        this.exercises = exercises;
        this.series = series;
        this.repetition = repetition;
    }

    public static UserExerciseResponse fromUserExercise(UserExercises userExercise,UserDto userDto){
       return new UserExerciseResponse(
               userExercise.getUserFile(),
               userDto,
               userExercise.getExercises(),
               userExercise.getSeries(),
               userExercise.getRepetition(),
               userExercise.getUserExercisesId()

       );
    }

}
