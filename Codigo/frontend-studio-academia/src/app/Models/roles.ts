export interface Role {
  id ?: string,
  name ?: string,
  createdAt ?: Date,
  deletedAt ?: Date,
}

export type Roles = Array<Role>
