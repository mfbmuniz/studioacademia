package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "plans")

public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plans_id")
    private Long planId;


    @Column(name = "planCode")
    private String planCode;

    @Column(name = "name")
    private String name ;

    @Column(name = "contracted_days")
    private String contractedDays;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Plans() {

    }

    public Plans(String planCode, String name, String contractedDays, BigDecimal price, String description) {
        this.planCode = planCode;
        this.name = name;
        this.contractedDays = contractedDays;
        this.price = price;
        this.description = description;

    }

    public Plans(Long planId, String planCode, String name, String contractedDays, BigDecimal price, String description) {
        this.planId = planId;
        this.planCode = planCode;
        this.name = name;
        this.contractedDays = contractedDays;
        this.price = price;
        this.description = description;

    }

    public Plans(String name, String contractedDays, BigDecimal price, String description) {
        this.name = name;
        this.contractedDays = contractedDays;
        this.price = price;
        this.description = description;

    }
}
