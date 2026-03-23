public class Bill {
    private final Ticket ticket;
    private final long exitTime;
    private final double totalAmount;

    public Bill(Ticket ticket, long exitTime, double totalAmount) {
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.totalAmount = totalAmount;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public long getExitTime() {
        return exitTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

