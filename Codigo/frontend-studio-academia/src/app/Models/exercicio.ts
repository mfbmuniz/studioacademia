export interface Exercicio {
  exerciseId: number,
  name ?: string,
  url ?: URL,
  description ?: string,
  series ?: number,
  repetitions ?: number
}

export type Exercicios = Array<Exercicio>


