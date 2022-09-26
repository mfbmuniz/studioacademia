package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import javassist.NotFoundException;

public interface UserFileService {

    UserFileResponse create(UserFileRequest userFileRequest) throws NotFoundException;
    UserExerciseResponse addExercices(UserExerciseRequest request) throws NotFoundException;
}
