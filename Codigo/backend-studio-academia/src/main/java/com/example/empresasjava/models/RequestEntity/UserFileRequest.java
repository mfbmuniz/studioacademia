package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserFileRequest {

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    String idUser;

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    String fileName;

}
