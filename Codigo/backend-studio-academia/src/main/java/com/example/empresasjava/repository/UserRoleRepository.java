package com.example.empresasjava.repository;

import com.example.empresasjava.models.Role;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    List<UserRole> findAllByUser(User user);
    UserRole findByUserAndRole(User user, Role role);
    //List<Role> findAllByUserId(Long userid);
}
