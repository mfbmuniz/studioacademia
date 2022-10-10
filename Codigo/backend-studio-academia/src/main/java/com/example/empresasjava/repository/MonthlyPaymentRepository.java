package com.example.empresasjava.repository;


import com.example.empresasjava.models.MonthlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment,Long> {
    MonthlyPayment findByUserIdAndDueDate(Long userId, Date dueDate);
}
