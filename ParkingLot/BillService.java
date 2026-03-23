public class BillService {

    private final PricingStrategy pricingStrategy;

    public BillService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public Bill generateBill(Ticket ticket, long exitTime) {
        double amount = pricingStrategy.calculateAmount(ticket, exitTime);
        
        ticket.getAllocatedSlot().removeVehicle();

        Bill bill = new Bill(ticket, exitTime, amount);
        System.out.println("Generated Bill for Ticket: " + ticket.getTicketId() + " Amount: $" + amount);
        return bill;
    }
}
