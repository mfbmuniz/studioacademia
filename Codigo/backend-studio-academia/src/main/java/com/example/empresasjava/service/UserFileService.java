package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFileService {

    UserFileResponse create(UserFileRequest userFileRequest) throws NotFoundException;
    UserExerciseResponse addExercices(UserExerciseRequest request) throws NotFoundException;

    UserFileResponse editUserFile(UserFileRequest request, Long id)throws NotFoundException;

    UserFileResponse deleteUserFile(UserFileRequest request, Long idFile)throws NotFoundException;

    UserExerciseResponse deleteUserExercise(UserExerciseRequest request, Long idExercise, Long idFile)throws NotFoundException;


    UserExerciseResponse editExercices(UserExerciseRequest request, Long idExercise, Long idFile)throws NotFoundException;

    Page<UserFileResponse> listsUserFilesByPage(Pageable pages, Long id)throws NotFoundException;

    Page<UserFileResponse> listsAllFilesByPage(Pageable pages)throws NotFoundException;

    Page<UserFileResponse> listsUserFilesByNameByPage(Pageable pages, Long id, String fileName)throws NotFoundException;


    Page<UserFileResponse> listsExercisesInUserFilesByIdByPage(Pageable pages, Long id_user, Long id_userFile)throws NotFoundException;
}
