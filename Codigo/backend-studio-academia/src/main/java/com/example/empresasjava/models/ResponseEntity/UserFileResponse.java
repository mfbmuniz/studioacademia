package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserFile;
import com.example.empresasjava.models.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserFileResponse {

    private Long userFileId;

    private String fileName;
    private UserDto user;
    private List<ExerciseResponse> exercises;

    private Integer series;
    private Integer repetitions;

    public UserFileResponse() {
    }

    public UserFileResponse(Long userFileId, UserDto user, String fileName) {
        this.userFileId = userFileId;
        this.fileName = fileName;
        this.user = user;
    }

    public static UserFileResponse fromUserFile(UserFile userFile){
        UserFileResponse a = new UserFileResponse();
        return new UserFileResponse(
                userFile.getUserFileId(),
                UserDto.fromUser(userFile.getUser()),
                userFile.getFileName()
        );
    }
}
