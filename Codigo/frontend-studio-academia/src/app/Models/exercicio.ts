export interface Exercicio {
  exerciseId: string,
  name ?: string,
  url ?:URL,
  description ?: string,
  series ?: string,
  repetitions ?:string
}

export type Exercicios = Array<Exercicio>


