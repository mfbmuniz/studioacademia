package com.example.empresasjava.repository;


import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserExercises;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserExercisesRepository extends JpaRepository<UserExercises,Long> {
    Optional<UserExercises> findByUserExercisesIdAndDeletedAtIsNull(Long userExerciseId);

//    Page<UserExercises> findAllByUserAndDeletedAtIsNull(User user, Pageable pages);
}
