import { Exercicio } from "./exercicio";
import {User} from "./user";

export interface Ficha {
  userFileId : string,
  fileName : string,
  description : string,
  exercises : Array<Exercicio>,
  user : User
}

export type Fichas = Array<Ficha>
