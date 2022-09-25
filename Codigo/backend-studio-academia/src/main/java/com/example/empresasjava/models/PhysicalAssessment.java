package com.example.empresasjava.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class PhysicalAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "physicalAssessment_id")
    private Long physicalAssessment_id;


}
