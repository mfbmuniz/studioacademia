package com.example.empresasjava.controller;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.PhysicalAssessment;
import com.example.empresasjava.models.RequestEntity.PhysicalAssessmentRequest;
import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.PhysicalAssessmentResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.dto.UserDto;
import com.example.empresasjava.service.PhysicalAssessmentService;
import com.example.empresasjava.service.UserService;
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

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/physical-assessment")
public class PhysicalAssessmentController {
    @Autowired
    private PhysicalAssessmentService physicalAssessmentService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar nova avaliacao de usuario")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN','NUTRICIONISTA','PROFESSOR'})")
    public ResponseEntity<PhysicalAssessmentResponse> createPhysicalAssessment(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody PhysicalAssessmentRequest request)
            throws NonUniqueResultException, NotFoundException {

        PhysicalAssessmentResponse physicalAssessment = this.physicalAssessmentService.createPhysicalAssessment(request);
        return ResponseEntity.ok().body(
                physicalAssessment
        );
    }

    @PostMapping(path = "/edit")
    @ApiOperation(value = "Editar avaliação existente")
    public ResponseEntity<PhysicalAssessmentResponse> editPhysicalAssessment(
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody PhysicalAssessmentRequest request) throws NotFoundException {

        return ResponseEntity.ok().body(
                this.physicalAssessmentService.editPhysicalAssessment(request)
        );
    }

    @DeleteMapping(path = "/delete/physicalAssessmentId/{physicalAssessmentId}")
    @ApiOperation(value = "Desativa usuário existente")
    public ResponseEntity<PhysicalAssessmentResponse> deletePhysicalAssessment(
            @PathVariable(value="physicalAssessmentId") Long physicalAssessmentId) throws NotFoundException {
        return ResponseEntity.ok().body(
                this.physicalAssessmentService.deletePhysicalAssessment(physicalAssessmentId)
        );
    }


    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista todas as avaliações")
    public Page<PhysicalAssessment> listPhysicalAssessmentsByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);
        return this.physicalAssessmentService.listPhysicalAssessmentsByPage(pages);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN','NUTRICIONISTA','PROFESSOR'})")
    @GetMapping(path = "page/{page}/size/{size}/idUser/{idUser}")
    @ResponseBody
    @ApiOperation(value = "Lista avaliações por id")
    public Page<PhysicalAssessment> listSpecificUserPhysicalAssessmentsByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size,
            @PathVariable(value="idUser")
            Long idUser
            ) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);

        return this.physicalAssessmentService.listSpecificUserPhysicalAssessmentsByPage(pages, idUser);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN','NUTRICIONISTA','PROFESSOR'})")
    @GetMapping(path = "page/{page}/size/{size}/professionalId/{professionalId}")
    @ResponseBody
    @ApiOperation(value = "Lista avaliações efetuadas por um profissional em especifico")
    public Page<PhysicalAssessment> listSpecificProfessionalPhysicalAssessmentsByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size,
            @PathVariable(value="professionalId")
            Long professionalId
    ) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);

        return this.physicalAssessmentService.listSpecificProfessionalPhysicalAssessmentsByPage(pages, professionalId);

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN','NUTRICIONISTA','PROFESSOR'})")
    @GetMapping(path = "getphysicalassessmentbyid/physicalAssessmentId/{physicalAssessmentId}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public ResponseEntity<PhysicalAssessmentResponse> getPhysicalAssessmentById(
            @PathVariable(value="physicalAssessmentId")
            Long physicalAssessmentId)throws NotFoundException{

        return ResponseEntity.ok().body(
                this.physicalAssessmentService.getPhysicalAssessmentById(physicalAssessmentId)
        );

    }


    /*

    List<PhysicalAssessment> getSpecificUserPhysicalAssessments(Long idUser)throws  NotFoundException;

    List<PhysicalAssessment> getSpecificProfessionalPhysicalAssessments(Long professionalId)throws  NotFoundException;
     */
}
