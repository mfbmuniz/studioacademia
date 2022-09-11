package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Role;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserRole;
import com.example.empresasjava.repository.UserRoleRepository;
import com.example.empresasjava.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository roleRepository;

    @Override
    public List<Role> findAllByUserId(Long id) {
        return null;// this.roleRepository.findAllByUserId(id);
    }

    @Override
    public List<UserRole> findAllByUser(User user) {
        return this.roleRepository.findAllByUser(user);
    }


}
