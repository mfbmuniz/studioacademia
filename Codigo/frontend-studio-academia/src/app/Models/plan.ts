export interface Plan {
  planId : number,
  name ?:  string,
  price ?: number,
  description ?: string,
  planCode ?: string,
  contractedDays ?: number
}

export type Plans = Array<Plan>
