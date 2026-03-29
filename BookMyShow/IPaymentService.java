public interface IPaymentService {
    Payment processPayment(double amount, PaymentMode mode);
    void processRefund(Payment payment);
}
