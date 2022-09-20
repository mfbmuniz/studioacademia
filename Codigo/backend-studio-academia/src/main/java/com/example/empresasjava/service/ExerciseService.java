package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.ResponseEntity.ExerciseDto;
import javassist.NotFoundException;

import javax.persistence.NonUniqueResultException;

public interface ExerciseService {
    ExerciseDto create (ExerciseRequest exercise) throws NonUniqueResultException, NotFoundException;
}
