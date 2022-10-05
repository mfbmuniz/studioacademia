package com.example.empresasjava.controller;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.RequestEntity.UserExerciseRequest;
import com.example.empresasjava.models.RequestEntity.UserFileRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import com.example.empresasjava.service.UserFileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user-files")

/*TODO:
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

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/pageUserFiles/{page}/size/{size}/{idUser}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<UserFileResponse> listUserFilesByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="idUser")
            String idUser)throws NotFoundException{
        Long id = Long.parseLong(idUser);

        Pageable pages = PageRequest.of(page, size);

        return this.userFileService.listsUserFilesByPage(pages,id);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/pageAllFiles/{page}/size/{size}/")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<UserFileResponse> listAllFilesByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size)throws NotFoundException{


        Pageable pages = PageRequest.of(page, size);

        return this.userFileService.listsAllFilesByPage(pages);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/pageSpecificUserFile/{page}/size/{size}/{idUser}/{fileName}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<UserFileResponse> listUserFilesByNameByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="idUser")
            String idUser,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="fileName")
            String fileName)throws NotFoundException{
        Long id = Long.parseLong(idUser);

        Pageable pages = PageRequest.of(page, size);

        return this.userFileService.listsUserFilesByNameByPage(pages,id,fileName);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/pageExercisesInFile/{page}/size/{size}/{idUser}/{fileId}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<UserFileResponse> listExercisesInUserFilesByIdByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="idUser")
            String idUser,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="fileId")
            String fileId)throws NotFoundException{
        Long id_user = Long.parseLong(idUser);
        Long id_userFile = Long.parseLong(fileId);

        Pageable pages = PageRequest.of(page, size);

        return this.userFileService.listsExercisesInUserFilesByIdByPage(pages,id_user,id_userFile);

    }

}
