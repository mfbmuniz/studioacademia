export interface Aluno {
  name ?: string,
  email ?: string,
  sex ?: String,
  legal_document ?: String,
  birhDate ?: Date
}

export type Alunos = Array<Aluno>

