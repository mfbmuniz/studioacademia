package com.example.empresasjava.service;

import com.example.empresasjava.models.Role;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserRole;

import java.util.List;

public interface UserRoleService {


    List<Role> findAllByUserId(Long id);
    List<UserRole> findAllByUser(User id);
}
