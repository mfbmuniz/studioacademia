package com.example.empresasjava.service;

import com.example.empresasjava.enums.RolesEnum;
import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.dto.UserDto;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    UserDto create(UserRequest user) throws NotFoundException;
    Optional<User> findByEmail(String email);
    UserDto save(User user);
    UserDto editUser(UserRequest userRequest);
    UserDto deleteUser(String email);
    UserDto deleteLoggedUser();
    User getUserByPrincipal();
    boolean hasRole(User user, RolesEnum admin);
    Page<User> listUsersByPage(Pageable page);

    Page<User> listUsersByPageAndName(Pageable pages, String name);

    User getUserById(Long userId) throws NotFoundException;
}
