public class Payment {
    private final int id;
    private final double amount;
    private final PaymentMode mode;
    private PaymentStatus status;

    public Payment(int id, double amount, PaymentMode mode) {
        this.id = id;
        this.amount = amount;
        this.mode = mode;
        this.status = PaymentStatus.SUCCESS;
    }

    public void refund() {
        this.status = PaymentStatus.REFUNDED;
    }
}
