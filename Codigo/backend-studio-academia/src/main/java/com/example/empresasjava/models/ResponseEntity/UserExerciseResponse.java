package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import com.example.empresasjava.models.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserExerciseResponse {

    private UserFile userFile;
    private Exercise exercises;
    private Integer series;
    private Integer repetitions;
    private UserDto user;

    private Long userExercisesID;

    public UserExerciseResponse() {
    }

    public UserExerciseResponse(UserFile userFile, UserDto user, Exercise exercises, Integer series, Integer repetitions, Long userExercisesID) {
        this.userFile = userFile;
        this.user = user;
        this.exercises = exercises;
        this.series = series;
        this.repetitions = repetitions;
        this.userExercisesID = userExercisesID;
    }

    public UserExerciseResponse(UserFile userFile, UserDto user, Exercise exercises, Integer series, Integer repetitions) {
        this.userFile = userFile;
        this.user = user;
        this.exercises = exercises;
        this.series = series;
        this.repetitions = repetitions;
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
