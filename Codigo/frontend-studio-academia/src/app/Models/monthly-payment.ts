export interface MonthlyPayment {
  monthlyPaymentId ?: number,
  paymentDate ?: Date,
  paymentStatus ?: string,
  userId ?: number,
  message ?: string,
  paymentVoucher ?: File,
  dueDate ?: Date,
  approvedDate ?: Date,

}

export type MonthlyPayments = Array<MonthlyPayment>
