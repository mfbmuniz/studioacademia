package com.example.empresasjava.models.ResponseEntity;


import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import com.example.empresasjava.models.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserFileResponse {

    private Long userFileId;

    private String fileName;
    private UserDto user;
    private List<UserExercises> exercises;

    public UserFileResponse() {
    }

    public UserFileResponse(Long userFileId, String fileName, UserDto user, List<UserExercises> exercises) {
        this.userFileId = userFileId;
        this.fileName = fileName;
        this.user = user;
        this.exercises = exercises;
    }

    public static UserFileResponse fromUserFile(UserFile userFile){
        UserFileResponse a = new UserFileResponse();
        return new UserFileResponse(
                userFile.getUserFileId(),
                userFile.getFileName(),
                UserDto.fromUser(userFile.getUser()),
                null
        );
    }
}
