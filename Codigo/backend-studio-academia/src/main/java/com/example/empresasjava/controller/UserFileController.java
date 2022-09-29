package com.example.empresasjava.controller;

import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import com.example.empresasjava.service.UserFileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user-files")

/*TODO:
    LISTAR EXERCICIOS DA FICHA X PAGINADO
    LISTAR TODAS AS FICHAS PAGINADO
    LISTAR TODAS AS FICHAS DE UM USUARIO PAGINADO (PESQUISAR POR NOME )
    DENTRO DAS FICHAS DE UM USUARIO X PESQUISAR UMA FICHA EM ESPECIFICO POR NOME ()

*/
public class UserFileController {

    @Autowired
    private UserFileService userFileService;



    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar nova ficha para um usuário")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<UserFileResponse> createFile(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody UserFileRequest request) throws NotFoundException {

                UserFileResponse userFileResponse = this.userFileService.create(request);

                return ResponseEntity.ok().body(
                        userFileResponse
                );

    }
    @PostMapping(path = "/edit/{idFile}")
    @ApiOperation(value = "ficha de usuario")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<UserFileResponse> editUserFile(
            @ApiParam(value = "Json da requisição que contem o dado do exercicio a ser salvo")
            @Valid @RequestBody UserFileRequest request,
            @PathVariable Long id) throws NotFoundException {


        UserFileResponse userFileResponse = this.userFileService.editUserFile(request,id);

        return ResponseEntity.ok().body(
                userFileResponse
        );
    }

    @DeleteMapping(path = "/deleteFile/{idFile}")
    @ApiOperation(value = "ficha de usuario")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<UserFileResponse> deleteUserFile(
            @ApiParam(value = "Json da requisição que contem o dado do exercicio a ser salvo")
            @Valid @RequestBody UserFileRequest request,
            @PathVariable Long idFile) throws NotFoundException {


        UserFileResponse userFileResponse = this.userFileService.deleteUserFile(request,idFile);

        return ResponseEntity.ok().body(
                userFileResponse
        );
    }


    @PostMapping(path = "/addExercises")
    @ApiOperation(value = "adiciona exercicios na ficha")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<UserExerciseResponse> addExercices(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody UserExerciseRequest request) throws NotFoundException {

        UserExerciseResponse userExerciseResponse = this.userFileService.addExercices(request);

        return ResponseEntity.ok().body(userExerciseResponse);

    }
    @DeleteMapping(path = "/deleteExercise/{idFile}/{idExercise}")
    @ApiOperation(value = "ficha de usuario")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<UserExerciseResponse> deleteUserExercise(
            @ApiParam(value = "Json da requisição que contem o dado do exercicio a ser salvo")
            @Valid @RequestBody UserExerciseRequest request,
            @PathVariable Long idExercise,
            @PathVariable Long idFile) throws NotFoundException {


        UserExerciseResponse userExerciseResponse = this.userFileService.deleteUserExercise(request,idExercise,idFile);

        return ResponseEntity.ok().body(
                userExerciseResponse
        );
    }
    @PostMapping(path = "/editExercises/{idFile}/{idExercise}")
    @ApiOperation(value = "adiciona exercicios na ficha")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<UserExerciseResponse> editExercices(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody UserExerciseRequest request,
            @PathVariable Long idExercise,
            @PathVariable Long idFile) throws NotFoundException {

        UserExerciseResponse userExerciseResponse = this.userFileService.editExercices(request,idExercise,idFile);

        return ResponseEntity.ok().body(userExerciseResponse);

    }



}
