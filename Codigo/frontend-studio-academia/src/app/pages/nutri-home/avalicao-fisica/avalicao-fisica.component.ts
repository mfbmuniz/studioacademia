import { ToastrService } from 'ngx-toastr';
import { PhysicalAssessmentService } from './../../../services/physical-assessmentService.';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/AuthService';

@Component({
  selector: 'app-avalicao-fisica',
  templateUrl: './avalicao-fisica.component.html',
  styleUrls: ['./avalicao-fisica.component.css']
})
export class AvalicaoFisicaComponent implements OnInit {

  physicalForm !: FormGroup
  actualUser: any;
  idAluno: any;

  constructor(
    private formBuilder : FormBuilder,
    private authService : AuthService,
    private routeAc: ActivatedRoute,
    private PhysicalAssessmentService:PhysicalAssessmentService,
    private toastr: ToastrService,) {
      this.routeAc.params.subscribe(params => this.idAluno = params['idAluno'])
     }

  ngOnInit(): void {
    this.actualUser = this.authService.getSession().user.id
    this.physicalForm = this.formBuilder.group({
      height : [[""],Validators.required],
      general_description : [[""],Validators.required],
      ideal_weight : [[""],Validators.required],
      actual_weight : [[""],Validators.required],
      relaxed_right_arm : [[""],Validators.required],
      relaxed_left_arm : [[""],Validators.required],
      contracted_right_arm : [[""],Validators.required],
      contracted_left_arm : [[""],Validators.required],
      right_forearm : [[""],Validators.required],
      left_forearm : [[""],Validators.required],
      right_fist : [[""],Validators.required],
      left_fist : [[""],Validators.required],
      neck  : [[""],Validators.required],
      chest  : [[""],Validators.required],
      abdomen   : [[""],Validators.required],
      shoulder  : [[""],Validators.required],
      waist : [[""],Validators.required],
      hip : [[""],Validators.required],
      right_calf  : [[""],Validators.required],
      left_calf : [[""],Validators.required],
      right_thigh : [[""],Validators.required],
      left_thigh : [[""],Validators.required],
      right_proximal_thigh : [[""],Validators.required],
      left_proximal_thigh : [[""],Validators.required],
      bone_fist_diameter : [[""],Validators.required],
      femur : [[""],Validators.required],
      skin_folds_biceps : [[""],Validators.required],
      skin_folds_triceps : [[""],Validators.required],
      skin_folds_abdominal : [[""],Validators.required],
      skin_folds_suprailiac  : [[""],Validators.required],
      skin_folds_medium_armpit  : [[""],Validators.required],
      skin_folds_chest  : [[""],Validators.required],
      skin_folds_subscapularis  : [[""],Validators.required],
      skin_folds_thigh  : [[""],Validators.required],
      skin_medial_calf  : [[""],Validators.required],
      IMC  : [[""],Validators.required],
      fat_mass   : [[""],Validators.required],
      corporal_water   : [[""],Validators.required],
      residual_weight   : [[""],Validators.required],
      visceral_fat   : [[""],Validators.required],
      lean_mass   : [[""],Validators.required],
      muscular_weight   : [[""],Validators.required],
      metabolic_age    : [[""],Validators.required],
      bone_weight    : [[""],Validators.required],
      description    : [[""]]

    })
  }




  send(){

    let body = {
      userId : this.idAluno,
      professionalId : this.actualUser,
      physicalAssessmentDate : new Date,
      height : this.physicalForm.value['height'],
      generalDescription : this.physicalForm.value['general_description'],
      idealWeight : this.physicalForm.value['actual_weight'],
      actualWeight : this.physicalForm.value['actual_weight'],
      relaxedRightArm : this.physicalForm.value['relaxed_left_arm'],
      relaxedLeftArm : this.physicalForm.value['relaxed_left_arm'],
      contractedRightArm : this.physicalForm.value['contracted_right_arm'],
      contractedLeftArm : this.physicalForm.value['contracted_left_arm'],
      rightForearm : this.physicalForm.value['right_forearm'],
      leftForearm : this.physicalForm.value['left_forearm'],
      rightFist : this.physicalForm.value['right_fist'],
      leftFist : this.physicalForm.value['left_fist'],
      neck  : this.physicalForm.value['neck'],
      chest  : this.physicalForm.value['chest'],
      abdomen   : this.physicalForm.value['abdomen'],
      shoulder  : this.physicalForm.value['shoulder'],
      waist : this.physicalForm.value['waist'],
      hip : this.physicalForm.value['hip'],
      rightCalf  : this.physicalForm.value['right_calf'],
      leftCalf : this.physicalForm.value['left_calf'],
      rightThigh : this.physicalForm.value['right_thigh'],
      leftThigh : this.physicalForm.value['left_thigh'],
      rightProximalThigh : this.physicalForm.value['right_proximal_thigh'],
      leftProximalThigh : this.physicalForm.value['left_proximal_thigh'],
      boneFistDiameter : this.physicalForm.value['bone_fist_diameter'],
      femur : this.physicalForm.value['femur'],
      skinFoldsBiceps : this.physicalForm.value['skin_folds_triceps'],
      skinFoldsTriceps : this.physicalForm.value['skin_folds_triceps'],
      skinFoldsAbdominal : this.physicalForm.value['skin_folds_abdominal'],
      skinFoldsSuprailiac  : this.physicalForm.value['skin_folds_medium_armpit'],
      skinFoldsMediumArmpit  : this.physicalForm.value['skin_folds_medium_armpit'],
      skinFoldsChest  : this.physicalForm.value['skin_folds_chest'],
      skinFoldsSubscapularis  : this.physicalForm.value['skin_folds_subscapularis'],
      skinFoldsThigh  : this.physicalForm.value['skin_folds_thigh'],
      skinMedialCalf  : this.physicalForm.value['skin_medial_calf'],
      imc  : this.physicalForm.value['IMC'],
      fatMass   : this.physicalForm.value['fat_mass'],
      corporalWater   : this.physicalForm.value['corporal_water'],
      residualWeight   : this.physicalForm.value['residual_weight'],
      visceralFat   : this.physicalForm.value['visceral_fat'],
      leanMass   : this.physicalForm.value['lean_mass'],
      muscularWeight   : this.physicalForm.value['metabolic_age'],
      metabolicAge   : this.physicalForm.value['metabolic_age'],
      boneWeight    : this.physicalForm.value['bone_weight'],
      description    : this.physicalForm.value['description'],
    }

    // console.log(body)
    this.PhysicalAssessmentService.create(body).subscribe({
      next:(res)=>{
        this.showSuccessToastr()
        this.physicalForm.reset()
      },
      error: (err)=>{
        console.log(err)
        this.showErrorToastr()
      }
    })

  }

  showSuccessToastr(){
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("O envio não pode ser feito", "Erro")
  }

}
