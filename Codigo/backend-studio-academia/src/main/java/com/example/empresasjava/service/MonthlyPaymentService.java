package com.example.empresasjava.service;

import com.example.empresasjava.models.MonthlyPayment;
import com.example.empresasjava.models.RequestEntity.MonthlyPaymentRequest;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import com.example.empresasjava.models.User;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;
import java.io.IOException;

public interface MonthlyPaymentService {


    MonthlyPaymentResponse create();
    MonthlyPayment create(User user);

    MonthlyPaymentResponse createRequestForApprove(MonthlyPaymentRequest request)throws NonUniqueResultException, NotFoundException, IOException;

    MonthlyPaymentResponse deleteMonthlyRequest(@Valid MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse editMonthlyPaymentRequest(@Valid MonthlyPaymentRequest request, Long idUser)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPayment> listRequestsByPage(Pageable pages)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPayment> listSpecificRequestsByPage(Pageable pages, String paymentStatusRequest)throws NonUniqueResultException, NotFoundException;



    Page<MonthlyPayment> listUserRequestsByPage(Pageable pages, Long id)throws NonUniqueResultException, NotFoundException;

    Page<MonthlyPayment> listUserSpecificRequestsByPage(Pageable pages, Long id,String paymentStatusRequest)throws NonUniqueResultException, NotFoundException;


    MonthlyPaymentResponse approveMonthlyRequest(MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;

    MonthlyPaymentResponse reproveMonthlyRequest(MonthlyPaymentRequest request, Long id)throws NonUniqueResultException, NotFoundException;


    void createNextPayment();

    String uploadImage(MultipartFile paymentVoucherImage)throws NonUniqueResultException, NotFoundException, IOException;

    Page<MonthlyPayment> listUserPendencyRequestsByPageWithSize(Pageable pages, Long idUser)throws NonUniqueResultException, NotFoundException, IOException;

    Page<MonthlyPayment> listAllPendencyRequestsByPageWithSize(Pageable pages)throws NonUniqueResultException, NotFoundException, IOException;

    public @ResponseBody byte[] getImages(Long id) throws NonUniqueResultException, NotFoundException, IOException;
}
