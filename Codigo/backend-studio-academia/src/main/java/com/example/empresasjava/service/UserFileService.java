package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import javassist.NotFoundException;

public interface UserFileService {

    UserExerciseResponse create(UserFileRequest userFileRequest) throws NotFoundException;
}
