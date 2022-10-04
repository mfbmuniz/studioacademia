package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import com.example.empresasjava.service.MonthlyPaymentService;
import com.sun.xml.bind.v2.TODO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;


    /*TODO : IMPLEMENTATIONS
     */

@Service
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {

    //TODO: 04/10/2022 //create
    @Override
    public MonthlyPaymentResponse create(MonthlyPaymentRequest request) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    @Override
    public MonthlyPaymentResponse createAutoRequest(MonthlyPaymentRequest request, Long idLong) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    @Override
    public MonthlyPaymentResponse createRequestForApprove(MonthlyPaymentRequest request) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //delete
    @Override
    public MonthlyPaymentResponse deleteMonthlyRequest(@Valid MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //edit ( usuario pode editar sua propria requisição, admin nao altera requisição do usuario, apenas aprova ou deleta)
    @Override
    public MonthlyPaymentResponse editMonthlyPaymentRequest(@Valid MonthlyPaymentRequest request, Long idUser) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //listar todas para administrador
    @Override
    public Page<MonthlyPaymentResponse> listRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022  //listar todas as pendentes para administrador
    @Override
    public Page<MonthlyPaymentResponse> listPendencyRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //listar todas ja aprovadas para administrador
    @Override
    public Page<MonthlyPaymentResponse> listApprovedRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //listar todas para usuario
    @Override
    public Page<MonthlyPaymentResponse> listUserRequestsByPage(Pageable pages, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022   //listar pendentes para aluno  ( lista suas requisições (do id dele), inclusive as passadas, o front deve separar as pendentes das comuns)
    @Override
    public Page<MonthlyPaymentResponse> listUserPendencyRequestsByPage(Pageable pages, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022  //listar todas as ja aprovadas para aluno ( lista suas requisições (do id dele), inclusive as passadas, o front deve separar as pendentes das comuns)
    @Override
    public Page<MonthlyPaymentResponse> listUserApprovedRequestsByPage(Pageable pages, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: aprova pagamento (e dispara para o usuario uma notificação de pagamento aprovado)
    @Override
    public MonthlyPaymentResponse approveMonthlyRequest(MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    //TODO: 04/10/2022 //reprova pagamento (e dispara para o usuario uma notificação de pagamento reprovado)
    @Override
    public MonthlyPaymentResponse reproveMonthlyRequest(MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }


}
