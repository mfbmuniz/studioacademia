package com.example.empresasjava.repository;

import com.example.empresasjava.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findOneByEmail(String email);
    Page<User> findAllByDeletedAtIsNullOrderByName(Pageable page);
}
