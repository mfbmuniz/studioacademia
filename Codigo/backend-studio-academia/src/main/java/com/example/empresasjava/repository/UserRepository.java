package com.example.empresasjava.repository;

import com.example.empresasjava.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long > {
    User findOneByEmail(String email);
    User findOneByIdUser(String email);
    Page<User> findAllByDeletedAtIsNullOrderByName(Pageable page);
    Page<User> findAllByNameAndDeletedAtIsNull(Pageable page, String name);
}
