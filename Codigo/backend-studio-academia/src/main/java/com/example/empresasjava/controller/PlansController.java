package com.example.empresasjava.controller;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.Plans;
import com.example.empresasjava.models.RequestEntity.PlansRequest;
import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.PlansResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.dto.UserDto;
import com.example.empresasjava.service.PlansService;
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
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/plans")
public class PlansController {

    @Autowired
    private PlansService plansService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar novo plano")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<PlansResponse> createPlan(
            @ApiParam(value = "Json da requisição que contem o dado do plano a ser salvo")
            @Valid @RequestBody PlansRequest request) throws NotFoundException {

        PlansResponse plansResponse= this.plansService.create(request);
        return ResponseEntity.ok().body(
                plansResponse
        );
    }
    @PostMapping(path = "/edit")
    @ApiOperation(value = "Editar plano existente")
    public ResponseEntity<PlansResponse> editPlan(
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody PlansRequest request) throws NotFoundException {

        return ResponseEntity.ok().body(
                this.plansService.editPlan(request)
        );
    }


    @DeleteMapping(path = "/delete/planId/{planId}")
    @ApiOperation(value = "Desativa plano existente")
    public ResponseEntity<PlansResponse> deleteUser(
            @PathVariable(value="planId")  Long planId) throws NotFoundException {

        return ResponseEntity.ok().body(
                this.plansService.deletePlan(planId)
        );
    }


    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<Plans> listAllPlansByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);
        return this.plansService.listPlansByPage(pages);

    }
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/page/{page}/size/{size}/keySearch/{keySearch}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<Plans> listSpecificPlanByPage(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size,
            @PathVariable(value="keySearch")
            String keySearch
    ) throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);

        return this.plansService.listSpecificPlanByPage(pages, keySearch);

    }


    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getplanbyid/planId/{planId}")
    @ResponseBody
    @ApiOperation(value = "Lista planos por página quantidade")
    public ResponseEntity<PlansResponse> getPlanByPlanId(
            @PathVariable(value="planId")
            Long planId)throws NotFoundException{

        return ResponseEntity.ok().body(
                this.plansService.getPlanByPlanId(planId)
        );

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getplanbyName/planName/{planName}")
    @ResponseBody
    @ApiOperation(value = "Lista planos por página quantidade")
    public ResponseEntity<PlansResponse> getPlanByPlanName(
            @PathVariable(value="planName")
            String planName)throws NotFoundException{

        return ResponseEntity.ok().body(
                this.plansService.getPlanByName(planName)
        );

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getplanbyplancode/planCode/{planCode}")
    @ResponseBody
    @ApiOperation(value = "Lista planos por página quantidade")
    public ResponseEntity<PlansResponse> getPlanByPlanCode(
            @PathVariable(value="planCode")
            String planCode)throws NotFoundException{

        return ResponseEntity.ok().body(
                this.plansService.getPlanByPlanCode(planCode)
        );

    }

    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    @GetMapping(path = "/getplansdropdown")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public ResponseEntity<List<Plans>> getPlansForDropDown()
            throws NotFoundException{

        return ResponseEntity.ok().body(
                this.plansService.getPlansForDropDown()
        );

    }
}
