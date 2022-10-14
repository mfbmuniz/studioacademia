export interface MonthlyPayment {
  id ?: number,
  paymentDate ?: Date,
  paymentStatus ?: string,
  user_id ?: number,
  message ?: string,
  payment_voucher ?: File,
  dueDate ?: Date,
  approvedDate ?: Date,
}

export type MonthlyPayments = Array<MonthlyPayment>
