package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.UserExerciseListRequest;
import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseListResponse;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserFileService {

    UserFileResponse create(UserFileRequest userFileRequest) throws NotFoundException;
    UserExerciseListResponse addExercices(UserExerciseListRequest request) throws NotFoundException;

    UserFileResponse editUserFile(UserFileRequest request)throws NotFoundException;

    UserFileResponse deleteUserFile(Long id)throws NotFoundException;

    UserExerciseResponse deleteUserExercise(UserExerciseRequest request)throws NotFoundException;


    UserExerciseResponse editExercises(UserExerciseRequest request)throws NotFoundException;

    Page<UserFile> listsUserFilesByPage(Pageable pages, Long id)throws NotFoundException;

    Page<UserFile> listsAllFilesByPage(Pageable pages)throws NotFoundException;

    Page<UserFile> listsUserFilesByNameByPage(Pageable pages, Long id, String fileName)throws NotFoundException;


    Page<UserExercises> listsExercisesInUserFilesByIdByPage(Pageable pages, Long id_userFile)throws NotFoundException;

    UserFileResponse listUserFileById(Long idFile);

    List<UserExerciseResponse> listsUserExercicesByUserFileId(Long idFile) throws NotFoundException;
}
