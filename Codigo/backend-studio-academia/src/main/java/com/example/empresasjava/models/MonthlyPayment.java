package com.example.empresasjava.models;


import com.example.empresasjava.enums.MonthlyPaymentStatusEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
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

    @Column(name = "due_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    @Column(name = "approved_date")
    private Date approved_date;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "payment_voucher")
    private String paymentVoucher;

    /*
    @Column(name = "payment_voucher")
    @Lob
    private byte[]  paymentVoucher;*/

    @Column(name = "message")
    private String message;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    public MonthlyPayment() {
    }

    public MonthlyPayment(Date dueDate, Date approved_date, Date paymentDate, Long userId, String paymentVoucher, String message, String paymentStatus) {
        this.dueDate = dueDate;
        this.approved_date = approved_date;
        this.paymentDate = paymentDate;
        this.userId = userId;
        this.paymentVoucher = paymentVoucher;
        this.message = message;
        this.paymentStatus = paymentStatus;
    }

    public static MonthlyPayment fromUser(User user){
        Calendar dueDateCalendar = Calendar.getInstance();
        dueDateCalendar.setTime(user.getDueDate());
        dueDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dueDateCalendar.set(Calendar.MINUTE, 0);
        dueDateCalendar.set(Calendar.SECOND, 0);
        dueDateCalendar.set(Calendar.MILLISECOND, 0);


        Calendar next = Calendar.getInstance();
        next.set(Calendar.DAY_OF_MONTH, dueDateCalendar.get(Calendar.DAY_OF_MONTH));
        next.add(Calendar.MONTH, 1);
        next.set(Calendar.HOUR_OF_DAY, 0);
        next.set(Calendar.MINUTE, 0);
        next.set(Calendar.SECOND, 0);
        next.set(Calendar.MILLISECOND, 0);

        return new MonthlyPayment(
                next.getTime(),
                null,
                null,
                user.getIdUser(),
                null,
                null,
                MonthlyPaymentStatusEnum.AGUARDANDO_PAGAMENTO.getCode()
        );
    }
}
