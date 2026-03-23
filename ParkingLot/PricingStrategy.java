public interface PricingStrategy {
    double calculateAmount(Ticket ticket, long exitTime);
}
