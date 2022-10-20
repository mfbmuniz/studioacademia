package com.example.empresasjava.repository;

import com.example.empresasjava.models.Role;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);

    List<Role> findAllByNameIn(List<String> roles);
}
