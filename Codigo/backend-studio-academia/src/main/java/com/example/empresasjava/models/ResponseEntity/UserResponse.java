package com.example.empresasjava.models.ResponseEntity;

import com.example.empresasjava.enums.SexEnum;
import com.example.empresasjava.models.Address;
import com.example.empresasjava.models.Role;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.dto.AddressDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
public class UserResponse {

    private String name;
    private String email;
    private List<String> roles;
    private String legalDocument;
    private AddressDto address;
    private String sex;

    public UserResponse() {
    }

    public UserResponse(String name, String email, List<String> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}
