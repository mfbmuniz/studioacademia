export interface Plan {
  id : number,
  name ?:  string,
  price ?: number,
  description ?: string,
  planConde ?: string,
  contractedDays ?: number
}

export type Plans = Array<Plan>
