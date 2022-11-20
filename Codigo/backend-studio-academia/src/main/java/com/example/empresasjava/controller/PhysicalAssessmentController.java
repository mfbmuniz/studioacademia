package com.example.empresasjava.controller;

import com.example.empresasjava.models.PhysicalAssessment;
import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.dto.UserDto;
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

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/physical-assessment")
public class PhysicalAssessmentController {

    /*
        PhysicalAssessmentResponse createPhysicalAssessment (PhysicalAssessmentRequest physicalAssessmentRequest) throws NonUniqueResultException, NotFoundException;

    PhysicalAssessmentResponse editPhysicalAssessment(PhysicalAssessmentRequest physicalAssessmentRequest)throws  NotFoundException;

    PhysicalAssessmentResponse deletePhysicalAssessment(Long physicalAssessmentId)throws  NotFoundException;

    Page<PhysicalAssessment> listPhysicalAssessmentsByPage(Pageable pages)throws  NotFoundException;

    Page<PhysicalAssessment> listSpecificUserPhysicalAssessmentsByPage(Pageable pages, Long idUser)throws  NotFoundException;

    Page<PhysicalAssessment> listSpecificProfessionalPhysicalAssessmentsByPage(Pageable pages, Long professionalId)throws  NotFoundException;

    PhysicalAssessmentResponse getPhysicalAssessmentById(Long physicalAssessmentId)throws  NotFoundException;

    List<PhysicalAssessment> getSpecificUserPhysicalAssessments(Long idUser)throws  NotFoundException;

    List<PhysicalAssessment> getSpecificProfessionalPhysicalAssessments(Long professionalId)throws  NotFoundException;
     */




}
