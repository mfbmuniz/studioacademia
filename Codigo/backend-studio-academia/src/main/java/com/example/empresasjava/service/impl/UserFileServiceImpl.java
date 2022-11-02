package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.RequestEntity.UserExerciseListRequest;
import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseListResponse;
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

import java.util.*;
import java.util.stream.Collectors;


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

        User user = this.userRepository.findById(userFileRequest.getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));



       return UserFileResponse.fromUserFile(this.userFileRepository.save(new UserFile(user,userFileRequest.getFileName())));

    }
    @Override
    public UserExerciseListResponse addExercices(UserExerciseListRequest userExerciseRequest)throws NotFoundException{

        LinkedList<UserExercises> insertedExercises = new LinkedList<UserExercises>();

        UserFile userFile =this.userFileRepository.findById(userExerciseRequest.getFileId())
                .orElseThrow(() -> new NotFoundException("Ficha Inexistente"));


        User user = this.userRepository.findById(userFile.getUser().getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        for (UserExercises actualUserExercise:
                userExerciseRequest.getFileExercises()) {

            Exercise exercise =this.exerciseRepository.findById(actualUserExercise.getExercises().getExerciseId())
                    .orElseThrow(() -> new NotFoundException("Exercicio Inexistente"));

            Optional<UserExercises> userExercise = this.userExercisesRepository.findById(actualUserExercise.getUserExercisesId());
            UserExercises temp = null;
            /*todo: buscar todos que existe
                ver qual nao existe mais e remover
                dos que existe faz abaixo
             */

            if(userExercise.isPresent()){
                //todo: atualizar
                temp = this.userExercisesRepository.save(userExercise.get());
            }else{
                temp = this.userExercisesRepository.save(
                        new UserExercises(
                                userFile,
                                exercise,
                                actualUserExercise.getSeries(),
                                actualUserExercise.getRepetition()
                        )
                );
            }

            this.userExercisesRepository.save(temp);

            insertedExercises.add(temp);


        }


        return UserExerciseListResponse.fromUserExercise(userFile , insertedExercises );
    }
    @Override
    public UserFileResponse editUserFile(UserFileRequest request) throws NotFoundException {

        UserFile userFile = getUserFile(request.getIdUser(), request.getIdFile());
        userFile.setFileName(request.getFileName());

        return UserFileResponse.fromUserFile(this.userFileRepository.save(userFile));
    }

    @Override
    public UserFileResponse deleteUserFile(Long id) throws NotFoundException {
        UserFile userFile_ = this.userFileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ficha nao encontrada"));

        User user = userFile_.getUser();

        UserFile userFile = getUserFile(user.getIdUser(),id);
        userFile.setDeletedAt(new Date());

        return UserFileResponse.fromUserFile(this.userFileRepository.save(userFile));
    }

    private UserFile getUserFile(Long userId, Long fileId) throws NotFoundException {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        return Optional.of(this.userFileRepository.findByUserFileIdAndUserAndDeletedAtIsNull(fileId, user))
                .orElseThrow(() -> new NotFoundException("Ficha não encontrada para o usuário"));
    }

    @Override
    public UserExerciseResponse deleteUserExercise(UserExerciseRequest request) throws NotFoundException {
        UserExercises userExercises = this.userExercisesRepository.findById(request.getExerciseId())
                .orElseThrow(() -> new NotFoundException("Id não pertence a nenhuma fica com exercicios"));
        userExercises.setDeletedAt(new Date());
        UserDto userDto = UserDto.fromUser(this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado")));
        return UserExerciseResponse.fromUserExercise(this.userExercisesRepository.save(userExercises), userDto);
    }

    @Override
    public UserExerciseResponse editExercises(UserExerciseRequest request) throws NotFoundException {
        UserExercises userExercises = this.userExercisesRepository.findByUserExercisesIdAndDeletedAtIsNull(request.getExerciseId())
                .orElseThrow(() -> new NotFoundException("Id não pertence a nenhuma fica com exercicios"));
        UserDto userDto = UserDto.fromUser(this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado")));

        userExercises.setSeries(request.getSeries());
        userExercises.setRepetition(request.getRepetition());

        return UserExerciseResponse.fromUserExercise(this.userExercisesRepository.save(userExercises), userDto);

    }


    @Override
    public Page<UserFile> listsUserFilesByPage(Pageable pages, Long userId) throws NotFoundException {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
//        return this.userExercisesRepository.findAllByUserAndDeletedAtIsNull(user, pages);
        return this.userFileRepository.findAllByUserAndDeletedAtIsNull(user, pages);
    }


    @Override
    public Page<UserFile> listsAllFilesByPage(Pageable pages) throws NotFoundException {
        return this.userFileRepository.findAllByDeletedAtIsNull( pages);
    }

    @Override
    public Page<UserFile> listsUserFilesByNameByPage(Pageable pages, Long id, String fileName) throws NotFoundException {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        return this.userFileRepository.findAllByUserAndFileNameAndDeletedAtIsNull(user, pages,fileName);
    }

    @Override
    public Page<UserExercises> listsExercisesInUserFilesByIdByPage(Pageable pages, Long id_userFile) throws NotFoundException {

        UserFile userFile = this.userFileRepository.findById(id_userFile)
                .orElseThrow(() -> new NotFoundException("Ficha  não encontrado"));

        User user = this.userRepository.findById(userFile.getUser().getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));



       if(userFile.getUser().getIdUser() == user.getIdUser() ){
           return this.userExercisesRepository.findAllByUserFileAndDeletedAtIsNull(userFile, pages);
       }else{
           new NotFoundException("Usuario diferente do ID da ficha");
           return  null;
       }


    }

    @Override
    public UserFileResponse listUserFileById(Long idFile) {
        return UserFileResponse.fromUserFile(this.userFileRepository.findByUserFileIdAndDeletedAtIsNull(idFile)
                .orElseThrow(() -> new NoSuchElementException("Ficha não encontrada")));
    }

    @Override
    public List<UserExerciseResponse> listsUserExercicesByUserFileId(Long idFile) throws NotFoundException {
        UserFile userFile = this.userFileRepository.findById(idFile)
                .orElseThrow(() -> new NotFoundException("Ficha  não encontrado"));
        UserDto user = UserDto.fromUser(userFile.getUser());

        return this.userExercisesRepository.findAllByUserFileAndDeletedAtIsNull(userFile)
                .stream().map(m -> UserExerciseResponse.fromUserExercise(m, user))
                .collect(Collectors.toList());
    }

}
