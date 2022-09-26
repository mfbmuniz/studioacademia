package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.ExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import com.example.empresasjava.models.dto.UserDto;
import com.example.empresasjava.repository.ExerciseRepository;
import com.example.empresasjava.repository.UserExercisesRepository;
import com.example.empresasjava.repository.UserFileRepository;
import com.example.empresasjava.repository.UserRepository;
import com.example.empresasjava.service.UserFileService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    private UserFileRepository userFileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserExercisesRepository userExercisesRepository;


    @Override
    public UserFileResponse create(UserFileRequest userFileRequest) throws NotFoundException {
        User user = this.userRepository.findById(userFileRequest.getUser().getId_user())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

       return UserFileResponse.fromUserFile(this.userFileRepository.save(new UserFile(user,userFileRequest.getFileName())));

    }
    @Override
    public UserExerciseResponse addExercices(UserExerciseRequest userExerciseRequest)throws NotFoundException{

        User user = this.userRepository.findById(userExerciseRequest.getUser().getId_user())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        UserFile userFile =this.userFileRepository.findById(userExerciseRequest.getFileId())
                .orElseThrow(() -> new NotFoundException("Ficha Inexistente"));

        Exercise exercise =this.exerciseRepository.findById(userExerciseRequest.getExerciseId())
                .orElseThrow(() -> new NotFoundException("Exercicio Inexistente"));

        if(userFile.getUser().getId_user() == user.getId_user()){

            return UserExerciseResponse.fromUserExercise(this.userExercisesRepository.save(new UserExercises(
                    userFile,
                    exercise,
                    userExerciseRequest.getSeries(),
                    userExerciseRequest.getRepetitions() )
                    ),
                    UserDto.fromUser(user)
            );


        }else{
            //throw new UserFileAndUserNotMatch ("id do usuario diferente do id da ficha ");
            return null;
        }

    }
}
