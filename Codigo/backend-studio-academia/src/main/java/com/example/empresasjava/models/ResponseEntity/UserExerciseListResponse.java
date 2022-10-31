package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class UserExerciseListResponse {

    private UserFile userFile;
    private LinkedList<UserExercises> insertedExercises;

    public UserExerciseListResponse() {
    }

    public UserExerciseListResponse(UserFile userFile, LinkedList<UserExercises> insertedExercises) {
        this.userFile = userFile;
        this.insertedExercises = insertedExercises;
    }

    public static UserExerciseListResponse fromUserExercise(UserFile userFile, LinkedList<UserExercises> insertedExercises){
       return new UserExerciseListResponse(
               userFile,
               insertedExercises

       );
    }

}
