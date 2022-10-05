package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "dueDate")
public class DueDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dueDate_id")
    private Long dueDateId;

    @Column(name = "due_Date")
    private Date dueDate;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public DueDate() {

    }

    public DueDate(Long dueDateId, Date dueDate, Date createdAt, Date deletedAt) {
        this.dueDateId = dueDateId;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public DueDate(Date dueDate, Date createdAt, Date deletedAt) {
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public DueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
