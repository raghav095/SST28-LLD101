public class Payment {
    private final int id;
    private final double amount;
    private final PaymentMode mode;
    private PaymentStatus status;

    public Payment(int id, double amount, PaymentMode mode) {
        this.id = id;
        this.amount = amount;
        this.mode = mode;
        this.status = PaymentStatus.INITIATED;
    }

    public void complete(boolean isSuccess) {
        if (isSuccess) {
            this.status = PaymentStatus.SUCCESS;
        } else {
            this.status = PaymentStatus.FAILED;
        }
    }

    public void refund() {
        this.status = PaymentStatus.REFUNDED;
    }
}
