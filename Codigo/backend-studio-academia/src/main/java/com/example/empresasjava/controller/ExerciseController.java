package com.example.empresasjava.controller;


import com.example.empresasjava.models.RequestEntity.ExerciseRequest;
import com.example.empresasjava.models.ResponseEntity.ExerciseDto;
import com.example.empresasjava.service.ExerciseService;
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
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;




    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar novo exercicio")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<ExerciseDto> createExercise(
            @ApiParam(value = "Json da requisição que contem o dado do exercicio a ser salvo")
            @Valid @RequestBody ExerciseRequest request) throws NotFoundException {

        ExerciseDto exerciseDto = this.exerciseService.create(request);
        return ResponseEntity.ok().body(
                exerciseDto
        );
    }

}
