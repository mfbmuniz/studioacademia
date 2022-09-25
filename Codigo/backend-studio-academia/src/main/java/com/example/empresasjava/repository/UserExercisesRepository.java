package com.example.empresasjava.repository;


import com.example.empresasjava.models.UserExercises;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExercisesRepository extends JpaRepository<UserExercises,Long> {
}
