package com.example.empresasjava.models.RequestEntity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserFileRequest {

    @NotNull(message = "Campo user não pode ser nulo")
    private Long idUser;

    @NotNull(message = "Campo user não pode ser nulo")
    private Long idFile;

    @NotNull(message = "Campo user não pode ser nulo")
    @NotEmpty(message = "Campo user não pode ser vazio")
    private String fileName;

}
