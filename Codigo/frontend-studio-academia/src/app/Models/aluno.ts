export interface Aluno {
  name ?: string,
  email ?: string,
  password ?: string,
  legal_document ?: string,
  phone ?: string,
  birthDate ?: Date,
  sex ?: String,
  zipCode ?: string,
  street ?: string,
  number ?: number,
  complement ?: number,
  state ?: string,
  city ?: string,
  district ?: string
}

export type Alunos = Array<Aluno>
