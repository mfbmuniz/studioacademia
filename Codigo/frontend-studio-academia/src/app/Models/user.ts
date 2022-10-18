import {Role} from "./roles";

export interface User {
  roles ?: Array<Role>,
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
  dueDate?: Date,
  plan?:string,
  weekday?:string
}

export type Users = Array<User>
