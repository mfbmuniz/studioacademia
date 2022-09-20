package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.*;
import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.ExerciseDto;
import com.example.empresasjava.models.dto.AddressDto;
import com.example.empresasjava.models.dto.UserDto;
import com.example.empresasjava.repository.ExerciseRepository;
import com.example.empresasjava.service.ExerciseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;






@Service
public class ExerciseServiceImpl implements ExerciseService {




    @Autowired
    ExerciseRepository exerciseRepository;

    @Override
    public ExerciseDto create(ExerciseRequest receivedExercise) throws NonUniqueResultException, NotFoundException {

        Optional<Exercise> exercise = Optional.ofNullable(this.exerciseRepository.findOneByName(receivedExercise.getName()));

        if(!exercise.isPresent()){

            return ExerciseDto.fromExercise(this.exerciseRepository.save(ExerciseRequest.toExercise(receivedExercise)));
        }else{
            throw new NonUniqueResultException("Exercicio ja cadastrado!");
        }
    }
}
