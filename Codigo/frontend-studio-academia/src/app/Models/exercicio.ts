export interface Exercicio {
  id ?: number,
  name ?: string,
  url ?: URL,
  description ?: string,
  series ?: number,
  repetitions ?: number
}

export type Exercicios = Array<Exercicio>


