package com.example.empresasjava.repository;


import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {


    Exercise findOneByName(String name);
    Exercise findOneByExerciseId(Long id);

    Page<Exercise> findAllByDeletedAtIsNullOrderByName(Pageable pages);

    Page<Exercise> findAllByNameContainingIgnoreCaseOrderByName(String name, Pageable pages);
}
