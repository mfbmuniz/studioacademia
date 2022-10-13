package com.example.empresasjava.service;

import com.example.empresasjava.models.MonthlyPayment;
import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import com.example.empresasjava.models.User;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;
import java.io.IOException;

public interface MonthlyPaymentService {


    MonthlyPaymentResponse create();
    MonthlyPayment create(User user);

    MonthlyPaymentResponse createRequestForApprove(MonthlyPaymentRequest request, MultipartFile paymentVoucherImage)throws NonUniqueResultException, NotFoundException, IOException;

    MonthlyPaymentResponse deleteMonthlyRequest(@Valid MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse editMonthlyPaymentRequest(@Valid MonthlyPaymentRequest request, Long idUser)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listRequestsByPage(Pageable pages)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listPendencyRequestsByPage(Pageable pages)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listApprovedRequestsByPage(Pageable pages)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listUserRequestsByPage(Pageable pages, Long id)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listUserPendencyRequestsByPage(Pageable pages, Long id)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPaymentResponse> listUserApprovedRequestsByPage(Pageable pages, Long id)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse approveMonthlyRequest(MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse reproveMonthlyRequest(MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;


    void createNextPayment();
}
