export interface Exercicio {
  exerciseId: string,
  name ?: string,
  url ?:URL,
  description ?: string,
  series ?: string,
  repetition ?:string,
  exerciseUrl ?: string
}

export type Exercicios = Array<Exercicio>


