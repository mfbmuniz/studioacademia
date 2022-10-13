package com.example.empresasjava.repository;


import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserExercises;
import com.example.empresasjava.models.UserFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserExercisesRepository extends JpaRepository<UserExercises,Long> {
    Optional<UserExercises> findByUserExercisesIdAndDeletedAtIsNull(Long userExerciseId);

    Page<UserExercises> findAllByUserFileAndDeletedAtIsNull(UserFile userFile, Pageable pages);

//    Page<UserExercises> findAllByUserAndDeletedAtIsNull(User user, Pageable pages);
}
