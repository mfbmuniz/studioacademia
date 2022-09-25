package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Role;
import com.example.empresasjava.repository.RoleRepository;
import com.example.empresasjava.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return Optional.of(this.roleRepository.findByName(name))
                .orElseThrow(() -> new NoSuchElementException("Not founded Role with name "+name));
    }

    @Override
    public List<Role> findAllByNameIn(List<String> roles) {
        return this.roleRepository.findAllByNameIn(roles);
    }
}
