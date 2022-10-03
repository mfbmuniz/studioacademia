export interface User {
  roles ?: string,
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
  idUser?: string
}

export type Users = Array<User>
