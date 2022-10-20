package com.example.empresasjava.service;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.dto.ExerciseDto;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface ExerciseService {
    ExerciseDto create (ExerciseRequest exercise) throws NonUniqueResultException, NotFoundException;
    ExerciseDto editExercise(ExerciseRequest request, Long id);

    ExerciseDto deleteExercise(Long id);

    Page<Exercise> listUsersByPage(Pageable pages);

    Page<Exercise> listSpecificUsersByPage(Pageable pages, String searchName);

    Exercise getExerciseById(Long exerciseId)throws  NotFoundException;

    List<Exercise> getExercisesForDropDown()throws  NotFoundException;

}
