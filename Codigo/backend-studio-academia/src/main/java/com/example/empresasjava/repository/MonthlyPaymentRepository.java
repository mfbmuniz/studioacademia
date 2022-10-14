package com.example.empresasjava.repository;


import com.example.empresasjava.models.MonthlyPayment;
import com.example.empresasjava.models.ResponseEntity.MonthlyPaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment,Long> {
    MonthlyPayment findByUserIdAndDueDate(Long userId, Date dueDate);

    MonthlyPayment findOneByMonthlyPaymentId(Long id);

    Page<MonthlyPayment> findAllByDeletedAtIsNullOrderByName(Pageable pages);

    Page<MonthlyPayment> findAllByUserIdAndDeletedAtIsNullOrderByName(Pageable pages, Long id);

    Page<MonthlyPayment> findAllByPaymentStatusAndDeletedAtIsNullContainingIgnoreCaseOrderByName(Pageable pages, String paymentStatusRequest);

    Page<MonthlyPayment> findAllByUserIdAndPaymentStatusAndDeletedAtIsNullContainingIgnoreCaseOrderByName(Pageable pages, Long id, String paymentStatusRequest);
}
