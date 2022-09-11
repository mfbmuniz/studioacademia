package com.example.empresasjava.models.dto;

import com.example.empresasjava.enums.RolesEnum;
import com.example.empresasjava.models.User;
import lombok.Data;

import java.util.List;

@Data
public class AuthUserDto {
    User user;
    List<RolesEnum> roles;

    public AuthUserDto(User user, List<RolesEnum> roles) {
        this.user = user;
        this.roles = roles;
    }
}
