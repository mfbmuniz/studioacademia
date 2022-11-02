import { User } from './user';
import {Exercicio, Exercicios} from './exercicio';
export interface UserExercises {
  exercises ?:  Exercicio,
  series ?: string,
  repetition ?:string,
  user: User,
  userExerciseId ?: string,
}
