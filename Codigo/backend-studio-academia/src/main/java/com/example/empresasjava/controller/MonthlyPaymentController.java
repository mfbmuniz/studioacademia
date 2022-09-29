package com.example.empresasjava.controller;

import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.service.MonthlyPaymentService;
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

    /*TODO
        //listar todas ja aprovadas para administrador
        //listar todos para aluno ( lista suas requisições (do id dele), inclusive as passadas, o front deve separar as pendentes das comuns)
        //listar pendentes para aluno  ( lista suas requisições (do id dele), inclusive as passadas, o front deve separar as pendentes das comuns)
        //listar todas as ja aprovadas para aluno ( lista suas requisições (do id dele), inclusive as passadas, o front deve separar as pendentes das comuns)
        //aprova pagamento (e dispara para o usuario uma notificação de pagamento aprovado)
        //reprova pagamento (e dispara para o usuario uma notificação de pagamento reprovado)
    */


@RestController
@CrossOrigin
@RequestMapping("/monthly-payment")
public class MonthlyPaymentController {

    @Autowired
    private MonthlyPaymentService monthlyPaymentService;


    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar nova requisição de mensalidade para aprovar")
    @PreAuthorize("@authorityChecker.isAllowed({'USER'})")
    public ResponseEntity<MonthlyPaymentResponse> createMonthyRequest(
            @ApiParam(value = "Json da requisição que contem o dado do exercicio a ser salvo")
            @Valid @RequestBody MonthlyPaymentRequest request) throws NotFoundException {

        MonthlyPaymentResponse monthlyPaymentResponse = this.monthlyPaymentService.create(request);

        return ResponseEntity.ok().body(
                monthlyPaymentResponse
        );
    }


    //se for usuario tem que verificar se a pessoa que ta tentanto deletar, tem o id
    //igual do parametro que ela passou, para evitar injecton

    @DeleteMapping(path = "/delete/{idUser}/")
    @ApiOperation(value = "deletar A REQUISIÇÃO ")
    //@PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public ResponseEntity<MonthlyPaymentResponse> deleteMonthlyRequest(
            @ApiParam(value = "Json da requisição que contem o dado do exercicio a ser salvo")
            @Valid @RequestBody MonthlyPaymentRequest request,
            @PathVariable Long id) throws NotFoundException {

        MonthlyPaymentResponse monthlyPaymentResponse = this.monthlyPaymentService.deleteMonthlyRequest(request,id);

        return ResponseEntity.ok().body(
                monthlyPaymentResponse
        );
    }

    @PostMapping(path = "/editMonthlyPaymentRequest/{idUser}")
    @ApiOperation(value = "edita a requisição de mensalidade")
    @PreAuthorize("@authorityChecker.isAllowed({'USER'})")
    public ResponseEntity<MonthlyPaymentResponse> editMonthlyPaymentRequest(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody MonthlyPaymentRequest request,
            @PathVariable Long idUser) throws NotFoundException {

        MonthlyPaymentResponse monthlyPaymentResponse= this.monthlyPaymentService.editMonthlyPaymentRequest(request,idUser);

        return ResponseEntity.ok().body(monthlyPaymentResponse);

    }

    @GetMapping(path = "/pageAll/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public Page<MonthlyPaymentRequest> listRequestsByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size)throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);

        return this.monthlyPaymentService.listRequestsByPage(pages);

    }
    @GetMapping(path = "/pageAllPendency/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "lista todas as requisições pendentes de aprovação")
    @PreAuthorize("@authorityChecker.isAllowed({'ADMIN'})")
    public Page<MonthlyPaymentRequest> listRequestsPendencyByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size)throws NotFoundException {

        Pageable pages = PageRequest.of(page, size);

        return this.monthlyPaymentService.listPendencyRequestsByPage(pages);

    }





}
