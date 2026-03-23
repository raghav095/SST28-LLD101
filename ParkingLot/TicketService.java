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

        ParkingSlot bestSlot = null;
        boolean success = false;
        
        for (int i = 0; i < 5; i++) {
            bestSlot = slotAssignmentStrategy.getSlot(entryGate, vehicle, parkingLot);
            
            if (bestSlot == null) {
                System.out.println("Parking Lot is Full for this vehicle type.");
                return null;
            }

            if (bestSlot.tryOccupy(vehicle)) {
                success = true;
                break;
            }
        }

        if (!success) {
            System.out.println("System busy! Multiple vehicles competing for the same slot. Try again.");
            return null;
        }

        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle, bestSlot, entryTime, entryGateID);
        System.out.println("Generated Ticket: " + ticketId + " for " + vehicle.getVehicleNumber() + 
                           " at Slot: " + bestSlot.getSlotNumber() + " (Type: " + bestSlot.getType() + ")");
        return ticket;
    }
}
