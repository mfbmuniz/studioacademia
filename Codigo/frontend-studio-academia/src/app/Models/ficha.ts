import { Exercicio } from "./exercicio";

export interface Ficha {
  id : string,
  name : string,
  description : string,
  exercicios : Array<Exercicio>,
}

export type Fichas = Array<Ficha>
