package com.example.empresasjava.models.ResponseEntity;


import com.example.empresasjava.models.MonthlyPayment;


import java.util.Date;

public class MonthlyPaymentResponse {

    private Date dueDate;
    private Long userId;
    private String paymentVoucher;
    private String optionalMessage;

    private Long monthlyPaymentId;

    public MonthlyPaymentResponse() {
    }

    public MonthlyPaymentResponse(Date dueDate, Long userId, String paymentVoucher, String optionalMessage) {
        this.dueDate = dueDate;
        this.userId = userId;
        this.paymentVoucher = paymentVoucher;
        this.optionalMessage = optionalMessage;
    }

    public MonthlyPaymentResponse(Date dueDate, Long userId, String paymentVoucher, String optionalMessage, Long monthlyPaymentId) {
        this.dueDate = dueDate;
        this.userId = userId;
        this.paymentVoucher = paymentVoucher;
        this.optionalMessage = optionalMessage;
        this.monthlyPaymentId = monthlyPaymentId;
    }

    public static MonthlyPaymentResponse fromMonthlyPayment(MonthlyPayment monthlyPayment){

        return new MonthlyPaymentResponse(
                monthlyPayment.getDueDate(),
                monthlyPayment.getUserId(),
                monthlyPayment.getPaymentVoucher(),
                monthlyPayment.getOptionalMessage(),
                monthlyPayment.getMonthlyPaymentId()

        );
    }
}
