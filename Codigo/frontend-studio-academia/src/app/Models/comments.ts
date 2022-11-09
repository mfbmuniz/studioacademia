export interface Comments {
  idUser ?: number,
  messageContent ?: Text,
  title ?: string,
}


export type Messages = Array<Comments>
