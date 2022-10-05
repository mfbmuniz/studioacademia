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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


//TODO: FINISH IMPLEMENTATIONS
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

        Long id =  Long.parseLong(userFileRequest.getIdUser());
        User user = this.userRepository.findById(Long.parseLong(userFileRequest.getIdUser()))
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));



       return UserFileResponse.fromUserFile(this.userFileRepository.save(new UserFile(user,userFileRequest.getFileName())));

    }
    @Override
    public UserExerciseResponse addExercices(UserExerciseRequest userExerciseRequest)throws NotFoundException{

        User user = this.userRepository.findById(userExerciseRequest.getUser().getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        UserFile userFile =this.userFileRepository.findById(userExerciseRequest.getFileId())
                .orElseThrow(() -> new NotFoundException("Ficha Inexistente"));

        Exercise exercise =this.exerciseRepository.findById(userExerciseRequest.getExerciseId())
                .orElseThrow(() -> new NotFoundException("Exercicio Inexistente"));

        if(userFile.getUser().getIdUser() == user.getIdUser()){

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

    @Override
    public UserFileResponse editUserFile(UserFileRequest request, Long id) throws NotFoundException {
        return null;
    }

    @Override
    public UserFileResponse deleteUserFile(UserFileRequest request, Long idFile) throws NotFoundException {
        return null;
    }

    @Override
    public UserExerciseResponse deleteUserExercise(UserExerciseRequest request, Long idExercise, Long idFile) throws NotFoundException {
        return null;
    }

    @Override
    public UserExerciseResponse editExercices(UserExerciseRequest request, Long idExercise, Long idFile) throws NotFoundException {
        return null;
    }

    //TODO: 05/10/2022 LISTAR TODAS AS FICHAS DE UM USUARIO PAGINADO (PESQUISAR POR id ) 
    @Override
    public Page<UserFileResponse> listsUserFilesByPage(Pageable pages, Long id) throws NotFoundException {
        return null;
    }

    //TODO: 05/10/2022 LISTAR TODAS AS FICHAS PAGINADO 
    @Override
    public Page<UserFileResponse> listsAllFilesByPage(Pageable pages) throws NotFoundException {
        return null;
    }

    // TODO: 05/10/2022 DENTRO DAS FICHAS DE UM USUARIO X PESQUISAR UMA FICHA EM ESPECIFICO POR NOME () 
    @Override
    public Page<UserFileResponse> listsUserFilesByNameByPage(Pageable pages, Long id, String fileName) throws NotFoundException {
        return null;
    }

    // TODO: 05/10/2022 LISTAR EXERCICIOS DA FICHA X PAGINADO
    @Override
    public Page<UserFileResponse> listsExercisesInUserFilesByIdByPage(Pageable pages, Long id_user, Long id_userFile) throws NotFoundException {
        return null;
    }


}
