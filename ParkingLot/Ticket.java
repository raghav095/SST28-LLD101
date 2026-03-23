public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSlot allocatedSlot;
    private final long entryTime;
    private final int entryGateId;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSlot allocatedSlot, long entryTime, int entryGateId) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.allocatedSlot = allocatedSlot;
        this.entryTime = entryTime;
        this.entryGateId = entryGateId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getAllocatedSlot() {
        return allocatedSlot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public int getEntryGateId() {
        return entryGateId;
    }
}
