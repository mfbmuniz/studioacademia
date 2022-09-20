package com.example.empresasjava.repository;


import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {


    Exercise findOneByName(String name);

}
