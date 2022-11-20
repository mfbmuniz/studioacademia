package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.PhysicalAssessment;

import lombok.Data;

import java.util.Date;

@Data
public class PhysicalAssessmentRequest {

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





    public static PhysicalAssessment toPhysicalAssessment(PhysicalAssessmentRequest physicalAssessmentRequest) {
        return new PhysicalAssessment(

            physicalAssessmentRequest.getUserId(),

            physicalAssessmentRequest.getProfessionalId(),

            physicalAssessmentRequest.getPhysicalAssessmentDate(),

            physicalAssessmentRequest.getHeight(),

            physicalAssessmentRequest.getGeneralDescription(),

            physicalAssessmentRequest.getIdealWeight(),

            physicalAssessmentRequest.getActualWeight(),

            physicalAssessmentRequest.getRelaxedRightArm() ,

            physicalAssessmentRequest.getRelaxedLeftArm() ,

            physicalAssessmentRequest.getContractedRightArm() ,

            physicalAssessmentRequest.getContractedLeftArm() ,

            physicalAssessmentRequest.getRightForearm() ,

            physicalAssessmentRequest.getLeftForearm() ,

            physicalAssessmentRequest.getRightFist() ,

            physicalAssessmentRequest.getLeftFist() ,

            physicalAssessmentRequest.getNeck() ,

            physicalAssessmentRequest.getChest() ,

            physicalAssessmentRequest.getAbdomen() ,

            physicalAssessmentRequest.getShoulder() ,

            physicalAssessmentRequest.getWaist() ,

            physicalAssessmentRequest.getHip() ,

            physicalAssessmentRequest.getRightCalf() ,

            physicalAssessmentRequest.getLeftCalf() ,

            physicalAssessmentRequest.getRightThigh() ,

            physicalAssessmentRequest.getLeftThigh() ,

            physicalAssessmentRequest.getRightProximalThigh() ,

            physicalAssessmentRequest.getLeftProximalThigh() ,

            physicalAssessmentRequest.getBoneFistDiameter() ,

            physicalAssessmentRequest.getFemur() ,

            physicalAssessmentRequest.getSkinFoldsBiceps() ,

            physicalAssessmentRequest.getSkinFoldsTriceps() ,

            physicalAssessmentRequest.getSkinFoldsAbdominal() ,

            physicalAssessmentRequest.getSkinFoldsSuprailiac() ,

            physicalAssessmentRequest.getSkinFoldsMediumArmpit() ,

            physicalAssessmentRequest.getSkinFoldsChest() ,

            physicalAssessmentRequest.getSkinFoldsSubscapularis() ,

            physicalAssessmentRequest.getSkinFoldsThigh() ,

            physicalAssessmentRequest.getSkinMedialCalf() ,

            physicalAssessmentRequest.getImc(),

            physicalAssessmentRequest.getFatMass() ,

            physicalAssessmentRequest.getCorporalWater() ,

            physicalAssessmentRequest.getResidualWeight() ,

            physicalAssessmentRequest.getVisceralFat(),

            physicalAssessmentRequest.getLeanMass(),

            physicalAssessmentRequest.getMuscularWeight() ,

            physicalAssessmentRequest.getMetabolicAge() ,

            physicalAssessmentRequest.getBoneWeight(),

            physicalAssessmentRequest.getDescription()

        );
    }

}
