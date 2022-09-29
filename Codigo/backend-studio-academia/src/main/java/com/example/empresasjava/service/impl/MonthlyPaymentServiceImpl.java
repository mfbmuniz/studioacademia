package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import com.example.empresasjava.service.MonthlyPaymentService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;


    /*TODO : IMPLEMENTATIONS
        //create
        //delete
        //edit ( usuario pode editar sua propria requisição, admin nao altera requisição do usuario, apenas aprova ou deleta)
        //listar todas para administrador
        //listar todas as pendentes para administrador

     */

@Service
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {


    @Override
    public MonthlyPaymentResponse create(MonthlyPaymentRequest request) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    @Override
    public MonthlyPaymentResponse deleteMonthlyRequest(@Valid MonthlyPaymentRequest request, Long id) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    @Override
    public MonthlyPaymentResponse editMonthlyPaymentRequest(@Valid MonthlyPaymentRequest request, Long idUser) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    @Override
    public Page<MonthlyPaymentRequest> listRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }

    @Override
    public Page<MonthlyPaymentRequest> listPendencyRequestsByPage(Pageable pages) throws NonUniqueResultException, NotFoundException {
        return null;
    }
}
