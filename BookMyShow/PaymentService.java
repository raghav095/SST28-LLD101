import java.util.Random;

public class PaymentService implements IPaymentService {

    @Override
    public Payment processPayment(double amount, PaymentMode mode) {
        Payment payment = new Payment(new Random().nextInt(), amount, mode);
        
        boolean gatewaySuccess = true;

        if (gatewaySuccess) {
            payment.complete(true);
            return payment;
        } else {
            payment.complete(false);
            throw new PaymentFailedException("Payment failed at external gateway.");
        }
    }

    @Override
    public void processRefund(Payment payment) {
        payment.refund();
    }
}
