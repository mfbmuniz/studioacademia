import { User } from './user';
import { Exercicios } from './exercicio';
export interface UserExercises {
  exercises ?:  Exercicios,
  series ?: string,
  repetition ?:string,
  user: User,
  userExerciseId ?: string,
}
