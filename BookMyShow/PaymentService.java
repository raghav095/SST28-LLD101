import java.util.Random;

public class PaymentService implements IPaymentService {

    @Override
    public Payment processPayment(double amount, PaymentMode mode) {
        Payment payment = new Payment(new Random().nextInt(), amount, mode);
        return payment;
    }

    @Override
    public void processRefund(Payment payment) {
        payment.refund();
    }
}
