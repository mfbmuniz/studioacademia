package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data

@ToString
@Table(name = "physical_assessment")
public class PhysicalAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "physicalAssessment_id")
    private Long physicalAssessmentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "professional_id")
    private Long professionalId;

    @Column(name = "physical_assessment_date")
    private Date physicalAssessmentDate;

    @Column(name = "height")
    private Double height;

    @Column(name = "general_description")
    private Double generalDescription;

    @Column(name = "ideal_weight")
    private Double idealWeight;

    @Column(name = "actual_weight")
    private Double actualWeight;

    @Column(name = "relaxed_right_arm")
    private Double relaxedRightArm;

    @Column(name = "relaxed_left_arm")
    private Double relaxedLeftArm;

    @Column(name = "contracted_right_arm")
    private Double contractedRightArm;

    @Column(name = "contracted_left_arm")
    private Double contractedLeftArm;

    @Column(name = "right_forearm")
    private Double rightForearm;

    @Column(name = "left_forearm")
    private Double leftForearm;

    @Column(name = "right_fist")
    private Double rightFist;

    @Column(name = "left_fist")
    private Double leftFist;

    @Column(name = "neck")
    private Double neck;

    @Column(name = "chest")
    private Double chest;

    @Column(name = "abdomen")
    private Double abdomen;

    @Column(name = "shoulder")
    private Double shoulder;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "hip")
    private Double hip;

    @Column(name = "right_calf")
    private Double rightCalf;

    @Column(name = "left_calf")
    private Double leftCalf;

    @Column(name = "right_thigh")
    private Double rightThigh;

    @Column(name = "left_thigh")
    private Double leftThigh;

    @Column(name = "right_proximal_thigh")
    private Double rightProximalThigh;

    @Column(name = "left_proximal_thigh")
    private Double leftProximalThigh;

    @Column(name = "bone_fist_diameter")
    private Double boneFistDiameter;

    @Column(name = "femur")
    private Double femur;

    @Column(name = "skin_folds_biceps")
    private Double skinFoldsBiceps;

    @Column(name = "skin_folds_triceps")
    private Double skinFoldsTriceps;

    @Column(name = "skin_folds_abdominal")
    private Double skinFoldsAbdominal;

    @Column(name = "skin_folds_suprailiac")
    private Double skinFoldsSuprailiac;

    @Column(name = "skin_folds_medium_armpit")
    private Double skinFoldsMediumArmpit;

    @Column(name = "skin_folds_chest")
    private Double skinFoldsChest;

    @Column(name = "skin_folds_subscapularis")
    private Double skinFoldsSubscapularis;

    @Column(name = "skin_folds_thigh")
    private Double skinFoldsThigh;

    @Column(name = "skin_medial_calf")
    private Double skinMedialCalf;

    @Column(name = "IMC")
    private Double imc;

    @Column(name = "fat_mass")
    private Double fatMass;

    @Column(name = "corporal_water")
    private Double corporalWater;

    @Column(name = "residual_weight")
    private Double residualWeight;

    @Column(name = "visceral_fat")
    private Double visceralFat;

    @Column(name = "lean_mass")
    private Double leanMass;

    @Column(name = "muscular_weight")
    private Double muscularWeight;

    @Column(name = "metabolic_age")
    private Double metabolicAge;

    @Column(name = "bone_weight")
    private Double boneWeight;

    @Column(name = "description")
    private String description;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;


    public PhysicalAssessment() {
    }

    public PhysicalAssessment(Long userId, Long professionalId, Date physicalAssessmentDate, Double height, Double generalDescription, Double idealWeight,
                              Double actualWeight, Double relaxedRightArm, Double relaxedLeftArm, Double contractedRightArm, Double contractedLeftArm,
                              Double rightForearm, Double leftForearm, Double rightFist, Double leftFist, Double neck,
                              Double chest, Double abdomen, Double shoulder, Double waist, Double hip, Double rightCalf, Double leftCalf, Double rightThigh,
                              Double leftThigh, Double rightProximalThigh, Double leftProximalThigh, Double boneFistDiameter, Double femur,
                              Double skinFoldsBiceps, Double skinFoldsTriceps, Double skinFoldsAbdominal, Double skinFoldsSuprailiac,
                              Double skinFoldsMediumArmpit, Double skinFoldsChest, Double skinFoldsSubscapularis, Double skinFoldsThigh,
                              Double skinMedialCalf, Double imc, Double fatMass,
                              Double corporalWater, Double residualWeight, Double visceralFat, Double leanMass, Double muscularWeight, Double metabolicAge,
                              Double boneWeight, String description, String filePath) {
        this.userId = userId;
        this.professionalId = professionalId;
        this.physicalAssessmentDate = physicalAssessmentDate;
        this.height = height;
        this.generalDescription = generalDescription;
        this.idealWeight = idealWeight;
        this.actualWeight = actualWeight;
        this.relaxedRightArm = relaxedRightArm;
        this.relaxedLeftArm = relaxedLeftArm;
        this.contractedRightArm = contractedRightArm;
        this.contractedLeftArm = contractedLeftArm;
        this.rightForearm = rightForearm;
        this.leftForearm = leftForearm;
        this.rightFist = rightFist;
        this.leftFist = leftFist;
        this.neck = neck;
        this.chest = chest;
        this.abdomen = abdomen;
        this.shoulder = shoulder;
        this.waist = waist;
        this.hip = hip;
        this.rightCalf = rightCalf;
        this.leftCalf = leftCalf;
        this.rightThigh = rightThigh;
        this.leftThigh = leftThigh;
        this.rightProximalThigh = rightProximalThigh;
        this.leftProximalThigh = leftProximalThigh;
        this.boneFistDiameter = boneFistDiameter;
        this.femur = femur;
        this.skinFoldsBiceps = skinFoldsBiceps;
        this.skinFoldsTriceps = skinFoldsTriceps;
        this.skinFoldsAbdominal = skinFoldsAbdominal;
        this.skinFoldsSuprailiac = skinFoldsSuprailiac;
        this.skinFoldsMediumArmpit = skinFoldsMediumArmpit;
        this.skinFoldsChest = skinFoldsChest;
        this.skinFoldsSubscapularis = skinFoldsSubscapularis;
        this.skinFoldsThigh = skinFoldsThigh;
        this.skinMedialCalf = skinMedialCalf;
        this.imc = imc;
        this.fatMass = fatMass;
        this.corporalWater = corporalWater;
        this.residualWeight = residualWeight;
        this.visceralFat = visceralFat;
        this.leanMass = leanMass;
        this.muscularWeight = muscularWeight;
        this.metabolicAge = metabolicAge;
        this.boneWeight = boneWeight;
        this.description =description;
        this.filePath = filePath;
    }

    public PhysicalAssessment(Long physicalAssessmentId, Long userId, Long professionalId, Date physicalAssessmentDate, Double height, Double generalDescription,
                              Double idealWeight, Double actualWeight, Double relaxedRightArm, Double relaxedLeftArm, Double contractedRightArm,
                              Double contractedLeftArm, Double rightForearm, Double leftForearm, Double rightFist, Double leftFist, Double neck,
                              Double chest, Double abdomen, Double shoulder, Double waist, Double hip, Double rightCalf, Double leftCalf,
                              Double rightThigh, Double leftThigh, Double rightProximalThigh, Double leftProximalThigh, Double boneFistDiameter,
                              Double femur, Double skinFoldsBiceps, Double skinFoldsTriceps, Double skinFoldsAbdominal, Double skinFoldsSuprailiac,
                              Double skinFoldsMediumArmpit, Double skinFoldsChest, Double skinFoldsSubscapularis, Double skinFoldsThigh,
                              Double skinMedialCalf, Double imc, Double fatMass, Double corporalWater, Double residualWeight, Double visceralFat,
                              Double leanMass, Double muscularWeight, Double metabolicAge, Double boneWeight, String description, String filePath) {

        this.physicalAssessmentId = physicalAssessmentId;
        this.userId = userId;
        this.professionalId = professionalId;
        this.physicalAssessmentDate = physicalAssessmentDate;
        this.height = height;
        this.generalDescription = generalDescription;
        this.idealWeight = idealWeight;
        this.actualWeight = actualWeight;
        this.relaxedRightArm = relaxedRightArm;
        this.relaxedLeftArm = relaxedLeftArm;
        this.contractedRightArm = contractedRightArm;
        this.contractedLeftArm = contractedLeftArm;
        this.rightForearm = rightForearm;
        this.leftForearm = leftForearm;
        this.rightFist = rightFist;
        this.leftFist = leftFist;
        this.neck = neck;
        this.chest = chest;
        this.abdomen = abdomen;
        this.shoulder = shoulder;
        this.waist = waist;
        this.hip = hip;
        this.rightCalf = rightCalf;
        this.leftCalf = leftCalf;
        this.rightThigh = rightThigh;
        this.leftThigh = leftThigh;
        this.rightProximalThigh = rightProximalThigh;
        this.leftProximalThigh = leftProximalThigh;
        this.boneFistDiameter = boneFistDiameter;
        this.femur = femur;
        this.skinFoldsBiceps = skinFoldsBiceps;
        this.skinFoldsTriceps = skinFoldsTriceps;
        this.skinFoldsAbdominal = skinFoldsAbdominal;
        this.skinFoldsSuprailiac = skinFoldsSuprailiac;
        this.skinFoldsMediumArmpit = skinFoldsMediumArmpit;
        this.skinFoldsChest = skinFoldsChest;
        this.skinFoldsSubscapularis = skinFoldsSubscapularis;
        this.skinFoldsThigh = skinFoldsThigh;
        this.skinMedialCalf = skinMedialCalf;
        this.imc = imc;
        this.fatMass = fatMass;
        this.corporalWater = corporalWater;
        this.residualWeight = residualWeight;
        this.visceralFat = visceralFat;
        this.leanMass = leanMass;
        this.muscularWeight = muscularWeight;
        this.metabolicAge = metabolicAge;
        this.boneWeight = boneWeight;
        this.description=description;
        this.filePath = filePath;
    }

}
