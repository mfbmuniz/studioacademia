export interface MonthlyPayment {
  monthlyPaymentId ?: number,
  paymentDate ?: Date,
  paymentStatus ?: string,
  userId ?: number,
  message ?: string,
  paymentVoucher ?: string,
  dueDate ?: Date,
  approvedDate ?: Date,

}

export type MonthlyPayments = Array<MonthlyPayment>
