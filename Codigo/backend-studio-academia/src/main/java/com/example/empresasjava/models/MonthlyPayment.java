package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "monthly_payment")
public class MonthlyPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " monthly_payment_id")
    private Long monthlyPaymentId;

    @ManyToOne
    @JoinColumn(name = "dueDate_id")
    DueDate dueDate;

    @Column(name = "payment_CHECK")
    boolean paymentCheck;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "payment_voucher")
    String paymentVoucher;

    @Column(name = "optional_message")
    String optionalMessage;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    public MonthlyPayment(Long monthlyPaymentId, DueDate dueDate, boolean paymentCheck, Long userId, String paymentVoucher) {
        this.monthlyPaymentId = monthlyPaymentId;
        this.dueDate = dueDate;
        this.paymentCheck = paymentCheck;
        this.userId = userId;
        this.paymentVoucher = paymentVoucher;
    }

    public MonthlyPayment() {
    }

    public MonthlyPayment(DueDate dueDate, boolean paymentCheck, Long userId, String paymentVoucher) {
        this.dueDate = dueDate;
        this.paymentCheck = paymentCheck;
        this.userId = userId;
        this.paymentVoucher = paymentVoucher;
    }



}
