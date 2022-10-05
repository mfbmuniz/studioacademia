package com.example.empresasjava.repository;


import com.example.empresasjava.models.DueDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DueDateRepository extends JpaRepository<DueDate,Long> {

}
