package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.*;
import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.dto.ExerciseDto;
import com.example.empresasjava.repository.ExerciseRepository;
import com.example.empresasjava.service.ExerciseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.Date;
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

    @Override
    public ExerciseDto editExercise(ExerciseRequest request, Long id) {
        Exercise exercise = Optional.of(this.exerciseRepository.findOneByExerciseId(id)).orElseThrow(()-> new NonUniqueResultException("Exercicio inexistente"));

        exercise.setDescription(request.getDescription());
        exercise.setExerciseUrl(request.getExerciseUrl());
        exercise.setName(request.getName());

        return ExerciseDto.fromExercise(this.exerciseRepository.save(exercise));

    }

    @Override
    public ExerciseDto deleteExercise(Long id) {
        Exercise exercise = Optional.of(this.exerciseRepository.findOneByExerciseId(id)).orElseThrow(()-> new NonUniqueResultException("Exercicio inexistente"));
        exercise.setDeletedAt(new Date());
        return ExerciseDto.fromExercise(this.exerciseRepository.save(exercise));

    }

    @Override
    public Page<Exercise> listUsersByPage(Pageable pages) {
        return this.exerciseRepository.findAllByDeletedAtIsNullOrderByName(pages);
    }

    @Override
    public Page<Exercise> listSpecificUsersByPage(Pageable pages, String searchName) {
        return this.exerciseRepository.findAllByNameContainingIgnoreCaseOrderByName(searchName,pages);
    }

    @Override
    public Exercise getExerciseById(Long exerciseId) throws NotFoundException {
        return this.exerciseRepository.findOneByExerciseId(exerciseId);
    }


}
