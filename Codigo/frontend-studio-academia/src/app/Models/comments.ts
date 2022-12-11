export interface Comments {
  userId ?: number,
  messageContent ?: string,
  title ?: string,
}


export type Messages = Array<Comments>
