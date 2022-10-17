package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
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

import java.util.Date;
import java.util.Optional;



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
    public UserExerciseResponse addExercices(UserExerciseRequest userExerciseRequest)throws NotFoundException{

        User user = this.userRepository.findById(userExerciseRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        UserFile userFile =this.userFileRepository.findById(userExerciseRequest.getFileId())
                .orElseThrow(() -> new NotFoundException("Ficha Inexistente"));

        Exercise exercise =this.exerciseRepository.findById(userExerciseRequest.getExerciseId())
                .orElseThrow(() -> new NotFoundException("Exercicio Inexistente"));

        if(userFile.getUser().getIdUser() == user.getIdUser()){

            return UserExerciseResponse.fromUserExercise(
                    this.userExercisesRepository.save(
                            new UserExercises(
                                userFile,
                                exercise,
                                userExerciseRequest.getSeries(),
                                userExerciseRequest.getRepetitions()
                            )
                    ),
                    UserDto.fromUser(user)
            );


        }else{
            //throw new UserFileAndUserNotMatch ("id do usuario diferente do id da ficha ");
            return null;
        }

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
        userExercises.setRepetition(request.getRepetitions());

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
    public Page<UserExercises> listsExercisesInUserFilesByIdByPage(Pageable pages, Long id_user, Long id_userFile) throws NotFoundException {
        User user = this.userRepository.findById(id_user)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        UserFile userFile = this.userFileRepository.findById(id_userFile)
                .orElseThrow(() -> new NotFoundException("Ficha  não encontrado"));

       if(userFile.getUser().getIdUser() == id_user ){
           return this.userExercisesRepository.findAllByUserFileAndDeletedAtIsNull(userFile, pages);
       }else{
           new NotFoundException("Usuario diferente do ID da ficha");
           return  null;
       }


    }


}
