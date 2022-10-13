import { MensagemModule } from './../../components/mensagem/mensagem.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdmHomeRoutingModule } from './adm-home-routing.module';
import { CadastroUsuarioComponent } from './cadastro-usuario/cadastro-usuario.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdmHomeComponent } from './adm-home.component';
import { CadastroAlunoComponent } from './cadastro-aluno/cadastro-aluno.component';
import { AdmSidebarComponent } from './adm-sidebar/adm-sidebar.component';
import { SidebarModule } from 'src/app/components/sidebar/sidebar.module';
import { CadastroExercicioComponent } from './cadastro-exercicio/cadastro-exercicio.component';
import { SearchHomeComponent } from './search-home/search-home.component';
import { GradeAlunosComponent } from './grade-alunos/grade-alunos.component';
import { CadastroFichaComponent } from './cadastro-ficha/cadastro-ficha.component';
import { EditarAlunoComponent } from './editar-aluno/editar-aluno.component';
import { AdmGradeFichasComponent } from './adm-grade-fichas/adm-grade-fichas.component';
import { EditarFichasComponent } from './editar-fichas/editar-fichas.component';
import { TabelaExerciciosComponent } from './tabela-exercicios/tabela-exercicios.component';
import { EditarExercicioComponent } from './editar-exercicio/editar-exercicio.component';




@NgModule({
  declarations: [
    AdmHomeComponent,
    CadastroUsuarioComponent,
    CadastroAlunoComponent,
    AdmSidebarComponent,
    CadastroExercicioComponent,
    SearchHomeComponent,
    GradeAlunosComponent,
    CadastroFichaComponent,
    EditarAlunoComponent,
    AdmGradeFichasComponent,
    EditarFichasComponent,
    TabelaExerciciosComponent,
    EditarExercicioComponent,
  ],
  imports: [
    CommonModule,
    AdmHomeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MensagemModule,
    SidebarModule
  ],
  exports:[
    AdmHomeComponent
  ]
})
export class AdmHomeModule { }
