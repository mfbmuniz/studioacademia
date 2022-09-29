package com.example.empresasjava.repository;


import com.example.empresasjava.models.MonthlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment,Long> {
}
