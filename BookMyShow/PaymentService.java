import java.util.Random;

public class PaymentService implements IPaymentService {

    @Override
    public Payment processPayment(double amount, PaymentMode mode) {
        // In real world, integration with external PG happens here
        Payment payment = new Payment(new Random().nextInt(), amount, mode);
        return payment;
    }

    @Override
    public void processRefund(Payment payment) {
        payment.refund();
    }
}
