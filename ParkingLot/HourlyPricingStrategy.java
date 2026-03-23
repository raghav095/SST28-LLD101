public class HourlyPricingStrategy implements PricingStrategy {

    @Override
    public double calculateAmount(Ticket ticket, long exitTime) {
        if (exitTime < ticket.getEntryTime()) {
            throw new IllegalArgumentException("Exit time cannot be earlier than entry time.");
        }

        long durationMillis = exitTime - ticket.getEntryTime();
        double hours = (double) durationMillis / (1000 * 60 * 60);
        
        long fullHours = (long) Math.ceil(hours);
        if (fullHours == 0) fullHours = 1;

        double rate = ticket.getAllocatedSlot().getType().getHourlyRate();
        return fullHours * rate;
    }
}
