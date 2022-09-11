package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.enums.SexEnum;
import com.example.empresasjava.models.*;
import com.example.empresasjava.models.dto.AddressDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
public class UserRequest {

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String name;
    @NotNull(message = "Campo email não pode ser nulo")
    @NotEmpty(message = "Campo email não pode ser vazio")
    @Email(message = "Digite um email válido")
    private String email;
    @NotNull(message = "Campo password não pode ser nulo")
    @NotEmpty(message = "Campo password não pode ser vazio")
    private String password;
    @NotNull(message = "Campo roles não pode ser nulo")
    private List<String> roles;
    @NotNull(message = "Campo legal_document não pode ser nulo")
    @NotEmpty(message = "Campo legal_document não pode ser vazio")
    private String legalDocument;

    @NotNull(message = "Campo address não pode ser nulo")
    private AddressDto address;
    @NotNull(message = "Campo sex não pode ser nulo")
    @NotEmpty(message = "Campo sex não pode ser vazio")
    private String sex;

    public UserRequest() {
    }

    public UserRequest(String name, String email, String password, List<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static User toUser(UserRequest user, List<Role> roles, Address address) {
        return new User(
                user.name,
                user.email,
                user.password,
                user.getLegalDocument(),
                address,
                Objects.requireNonNull(SexEnum.getByCd(user.getSex())),
                roles);
    }

}
