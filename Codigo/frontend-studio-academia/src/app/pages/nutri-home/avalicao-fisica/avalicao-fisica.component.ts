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
      height : this.physicalForm.value['height'],
      general_description : this.physicalForm.value['general_description'],
      ideal_weight : this.physicalForm.value['actual_weight'],
      actual_weight : this.physicalForm.value['actual_weight'],
      relaxed_right_arm : this.physicalForm.value['relaxed_left_arm'],
      relaxed_left_arm : this.physicalForm.value['relaxed_left_arm'],
      contracted_right_arm : this.physicalForm.value['contracted_right_arm'],
      contracted_left_arm : this.physicalForm.value['contracted_left_arm'],
      right_forearm : this.physicalForm.value['right_forearm'],
      left_forearm : this.physicalForm.value['left_forearm'],
      right_fist : this.physicalForm.value['right_fist'],
      left_fist : this.physicalForm.value['left_fist'],
      neck  : this.physicalForm.value['neck'],
      chest  : this.physicalForm.value['chest'],
      abdomen   : this.physicalForm.value['abdomen'],
      shoulder  : this.physicalForm.value['shoulder'],
      waist : this.physicalForm.value['waist'],
      hip : this.physicalForm.value['hip'],
      right_calf  : this.physicalForm.value['right_calf'],
      left_calf : this.physicalForm.value['left_calf'],
      right_thigh : this.physicalForm.value['right_thigh'],
      left_thigh : this.physicalForm.value['left_thigh'],
      right_proximal_thigh : this.physicalForm.value['right_proximal_thigh'],
      left_proximal_thigh : this.physicalForm.value['left_proximal_thigh'],
      bone_fist_diameter : this.physicalForm.value['bone_fist_diameter'],
      femur : this.physicalForm.value['femur'],
      skin_folds_biceps : this.physicalForm.value['skin_folds_triceps'],
      skin_folds_triceps : this.physicalForm.value['skin_folds_triceps'],
      skin_folds_abdominal : this.physicalForm.value['skin_folds_abdominal'],
      skin_folds_suprailiac  : this.physicalForm.value['skin_folds_medium_armpit'],
      skin_folds_medium_armpit  : this.physicalForm.value['skin_folds_medium_armpit'],
      skin_folds_chest  : this.physicalForm.value['skin_folds_chest'],
      skin_folds_subscapularis  : this.physicalForm.value['skin_folds_subscapularis'],
      skin_folds_thigh  : this.physicalForm.value['skin_folds_thigh'],
      skin_medial_calf  : this.physicalForm.value['skin_medial_calf'],
      IMC  : this.physicalForm.value['IMC'],
      fat_mass   : this.physicalForm.value['fat_mass'],
      corporal_water   : this.physicalForm.value['corporal_water'],
      residual_weight   : this.physicalForm.value['residual_weight'],
      visceral_fat   : this.physicalForm.value['visceral_fat'],
      lean_mass   : this.physicalForm.value['lean_mass'],
      muscular_weight   : this.physicalForm.value['metabolic_age'],
      metabolic_age    : this.physicalForm.value['metabolic_age'],
      bone_weight    : this.physicalForm.value['bone_weight'],
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
