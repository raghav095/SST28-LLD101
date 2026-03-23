import java.util.UUID;

public class TicketService {

    private final SlotAssignmentStrategy slotAssignmentStrategy;

    public TicketService(SlotAssignmentStrategy slotAssignmentStrategy) {
        this.slotAssignmentStrategy = slotAssignmentStrategy;
    }

    public Ticket generateTicket(Vehicle vehicle, long entryTime, int entryGateID, ParkingLot parkingLot) {
        Gate entryGate = parkingLot.getGateById(entryGateID);
        if (entryGate == null || entryGate.getType() != Gate.GateType.ENTRY) {
            System.out.println("Invalid Entry Gate");
            return null;
        }

        ParkingSlot bestSlot = slotAssignmentStrategy.getSlot(entryGate, vehicle, parkingLot);

        if (bestSlot == null) {
            System.out.println("Parking Lot is Full for this vehicle type.");
            return null;
        }

        bestSlot.parkVehicle(vehicle);

        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle, bestSlot, entryTime, entryGateID);
        System.out.println("Generated Ticket: " + ticketId + " for " + vehicle.getVehicleNumber() + 
                           " at Slot: " + bestSlot.getSlotNumber() + " (Type: " + bestSlot.getType() + ")");
        return ticket;
    }
}
