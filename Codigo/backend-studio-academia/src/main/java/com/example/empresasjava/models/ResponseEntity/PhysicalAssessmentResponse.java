package com.example.empresasjava.models.ResponseEntity;


import com.example.empresasjava.models.PhysicalAssessment;

import lombok.Data;

import java.util.Date;

@Data
public class PhysicalAssessmentResponse {

    private Long physicalAssessmentId;

    private Long userId;

    private Long professionalId;

    private Date physicalAssessmentDate;

    private Double height;

    private Double generalDescription;

    private Double idealWeight;

    private Double actualWeight;

    private Double relaxedRightArm;

    private Double relaxedLeftArm;

    private Double contractedRightArm;

    private Double contractedLeftArm;

    private Double rightForearm;

    private Double leftForearm;

    private Double rightFist;

    private Double leftFist;

    private Double neck;

    private Double chest;

    private Double abdomen;

    private Double shoulder;

    private Double waist;

    private Double hip;

    private Double rightCalf;

    private Double leftCalf;

    private Double rightThigh;

    private Double leftThigh;

    private Double rightProximalThigh;

    private Double leftProximalThigh;

    private Double boneFistDiameter;

    private Double femur;

    private Double skinFoldsBiceps;

    private Double skinFoldsTriceps;

    private Double skinFoldsAbdominal;

    private Double skinFoldsSuprailiac;

    private Double skinFoldsMediumArmpit;

    private Double skinFoldsChest;

    private Double skinFoldsSubscapularis;

    private Double skinFoldsThigh;

    private Double skinMedialCalf;

    private Double imc;

    private Double fatMass;

    private Double corporalWater;

    private Double residualWeight;

    private Double visceralFat;

    private Double leanMass;

    private Double muscularWeight;

    private Double metabolicAge;

    private Double boneWeight;

    private Date createdAt;

    private Date deletedAt;

    private String description;

    private String filePath;

    public PhysicalAssessmentResponse() {
    }

    public PhysicalAssessmentResponse(Long physicalAssessmentId, Long userId,Long professionalId, Date physicalAssessmentDate, Double height, Double generalDescription,
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
        this.description = description;
        this.filePath = filePath;
    }

    public static PhysicalAssessmentResponse fromPhysicalAssessment (PhysicalAssessment physicalAssessment){

        return new PhysicalAssessmentResponse(

                physicalAssessment.getPhysicalAssessmentId(),

                physicalAssessment.getUserId(),

                physicalAssessment.getProfessionalId(),

                physicalAssessment.getPhysicalAssessmentDate(),

                physicalAssessment.getHeight(),

                physicalAssessment.getGeneralDescription(),

                physicalAssessment.getIdealWeight(),

                physicalAssessment.getActualWeight(),

                physicalAssessment.getRelaxedRightArm() ,

                physicalAssessment.getRelaxedLeftArm() ,

                physicalAssessment.getContractedRightArm() ,

                physicalAssessment.getContractedLeftArm() ,

                physicalAssessment.getRightForearm() ,

                physicalAssessment.getLeftForearm() ,

                physicalAssessment.getRightFist() ,

                physicalAssessment.getLeftFist() ,

                physicalAssessment.getNeck() ,

                physicalAssessment.getChest() ,

                physicalAssessment.getAbdomen() ,

                physicalAssessment.getShoulder() ,

                physicalAssessment.getWaist() ,

                physicalAssessment.getHip() ,

                physicalAssessment.getRightCalf() ,

                physicalAssessment.getLeftCalf() ,

                physicalAssessment.getRightThigh() ,

                physicalAssessment.getLeftThigh() ,

                physicalAssessment.getRightProximalThigh() ,

                physicalAssessment.getLeftProximalThigh() ,

                physicalAssessment.getBoneFistDiameter() ,

                physicalAssessment.getFemur() ,

                physicalAssessment.getSkinFoldsBiceps() ,

                physicalAssessment.getSkinFoldsTriceps() ,

                physicalAssessment.getSkinFoldsAbdominal() ,

                physicalAssessment.getSkinFoldsSuprailiac() ,

                physicalAssessment.getSkinFoldsMediumArmpit() ,

                physicalAssessment.getSkinFoldsChest() ,

                physicalAssessment.getSkinFoldsSubscapularis() ,

                physicalAssessment.getSkinFoldsThigh() ,

                physicalAssessment.getSkinMedialCalf() ,

                physicalAssessment.getImc() ,

                physicalAssessment.getFatMass() ,

                physicalAssessment.getCorporalWater() ,

                physicalAssessment.getResidualWeight() ,

                physicalAssessment.getVisceralFat(),

                physicalAssessment.getLeanMass(),

                physicalAssessment.getMuscularWeight() ,

                physicalAssessment.getMetabolicAge() ,

                physicalAssessment.getBoneWeight(),

                physicalAssessment.getDescription(),

                physicalAssessment.getFilePath()


        );
    }
}
