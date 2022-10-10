package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFileService {

    UserFileResponse create(UserFileRequest userFileRequest) throws NotFoundException;
    UserExerciseResponse addExercices(UserExerciseRequest request) throws NotFoundException;

    UserFileResponse editUserFile(UserFileRequest request)throws NotFoundException;

    UserFileResponse deleteUserFile(UserFileRequest request)throws NotFoundException;

    UserExerciseResponse deleteUserExercise(UserExerciseRequest request)throws NotFoundException;


    UserExerciseResponse editExercises(UserExerciseRequest request)throws NotFoundException;

    Page<UserFile> listsUserFilesByPage(Pageable pages, Long id)throws NotFoundException;

    Page<UserFileResponse> listsAllFilesByPage(Pageable pages)throws NotFoundException;

    Page<UserFileResponse> listsUserFilesByNameByPage(Pageable pages, Long id, String fileName)throws NotFoundException;


    Page<UserFileResponse> listsExercisesInUserFilesByIdByPage(Pageable pages, Long id_user, Long id_userFile)throws NotFoundException;
}
