package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserFile;
import com.example.empresasjava.models.dto.UserDto;
import com.example.empresasjava.repository.ExerciseRepository;
import com.example.empresasjava.repository.UserFileRepository;
import com.example.empresasjava.repository.UserRepository;
import com.example.empresasjava.service.ExerciseService;
import com.example.empresasjava.service.UserFileService;
import com.example.empresasjava.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    private UserFileRepository userFileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public UserExerciseResponse create(UserFileRequest userFileRequest) throws NotFoundException {
        User user = this.userRepository.findById(userFileRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        List<Exercise> allExercises = this.exerciseRepository.findAllByExerciseIdIn(userFileRequest.getExercisesIds());
//TODO: checar se tem exercicio

        this.userFileRepository.save(new UserFile(user, allExercises));

        return new UserExerciseResponse();
    }
}
