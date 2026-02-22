public class Bill {
    public final double subtotal;
    public final double tax;
    public final double discount;
    public final double total;

    public Bill(double subtotal, double tax, double discount, double total) {
        this.subtotal = subtotal;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}
