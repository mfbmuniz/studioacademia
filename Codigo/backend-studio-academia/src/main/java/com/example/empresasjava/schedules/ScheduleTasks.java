package com.example.empresasjava.schedules;

import com.example.empresasjava.service.MonthlyPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {

    @Autowired
    private MonthlyPaymentService monthlyPaymentService;

    @Scheduled(cron = "0 1 1 * * *")
    private void createNextMonthlyPayment(){
        System.out.println("teste");
        this.monthlyPaymentService.createNextPayment();
    }
}
