package com.example.empresasjava.repository;


import com.example.empresasjava.enums.MonthlyPaymentStatusEnum;
import com.example.empresasjava.models.MonthlyPayment;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment,Long> {
    MonthlyPayment findOneByUserIdAndDueDate(Long userId, Date dueDate);

    MonthlyPayment findOneByMonthlyPaymentId(Long id);

    Page<MonthlyPayment> findAllByDeletedAtIsNullOrderByDueDateDesc (Pageable pages);

   Page<MonthlyPayment> findAllByUserIdAndDeletedAtIsNullOrderByDueDateDesc(Pageable pages, Long id);

   Page<MonthlyPayment> findAllByPaymentStatusAndDeletedAtIsNullOrderByDueDateDesc(Pageable pages, String paymentStatus);

   Page<MonthlyPayment> findAllByUserIdAndPaymentStatusAndDeletedAtIsNullOrderByDueDateDesc(Pageable pages, Long id ,String paymentStatusRequest);

   Page<MonthlyPayment> findAllByPaymentStatusNotAndDeletedAtIsNullOrderByDueDateDesc(String paymentStatus, Pageable pages);

    Page<MonthlyPayment> findAllByPaymentStatusNotAndUserIdAndDeletedAtIsNullOrderByDueDateDesc(String paymentStatus,Long monthlyPaymentId, Pageable pages);

}
