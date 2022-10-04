package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;

public interface MonthlyPaymentService {


    MonthlyPaymentResponse create(MonthlyPaymentRequest request)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse deleteMonthlyRequest(@Valid MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse editMonthlyPaymentRequest(@Valid MonthlyPaymentRequest request, Long idUser)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listRequestsByPage(Pageable pages)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listPendencyRequestsByPage(Pageable pages)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listApprovedRequestsByPage(Pageable pages)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listUserRequestsByPage(Pageable pages, Long id)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listUserPendencyRequestsByPage(Pageable pages, Long id)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listUserApprovedRequestsByPage(Pageable pages, Long id)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse approveMonthyRequest(MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;
}
