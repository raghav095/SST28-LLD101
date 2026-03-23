import java.util.*;

public class ParkingLot {
    private final String name;
    private final List<ParkingFLoor> floors;

    public ParkingLot(String name, List<ParkingFLoor> floors) {
        this.name = name;
        this.floors = floors;
    }

    public String getName() {
        return name;
    }

    public List<ParkingFLoor> getFloors() {
        return floors;
    }

    public Gate getGateById(int gateId) {
        for (ParkingFLoor floor : floors) {
            for (Gate g : floor.getGates()) {
                if (g.getGateID() == gateId) {
                    return g;
                }
            }
        }
        return null;
    }

    public Map<ParkingSlot.SlotType, Integer> status() {
        Map<ParkingSlot.SlotType, Integer> availability = new HashMap<>();
        for (ParkingSlot.SlotType type : ParkingSlot.SlotType.values()) {
            availability.put(type, 0);
        }

        for (ParkingFLoor floor : floors) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (!slot.isOccupied()) {
                    availability.put(slot.getType(), availability.get(slot.getType()) + 1);
                }
            }
        }
        return availability;
    }

    public void showAvailableSlots() {
        System.out.println("\n--- Detailed Available Slots ---");
        for (ParkingFLoor floor : floors) {
            System.out.print("Floor " + floor.getFloorNumber() + ": ");
            boolean hasSlots = false;
            for (ParkingSlot slot : floor.getSlots()) {
                if (!slot.isOccupied()) {
                    System.out.print("[Slot " + slot.getSlotNumber() + " - " + slot.getType() + "] ");
                    hasSlots = true;
                }
            }
            if (!hasSlots) {
                System.out.print("Full");
            }
            System.out.println();
        }
        System.out.println("--------------------------------\n");
    }
}
