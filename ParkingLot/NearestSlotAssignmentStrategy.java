import java.util.Arrays;
import java.util.List;

public class NearestSlotAssignmentStrategy implements SlotAssignmentStrategy {

    @Override
    public ParkingSlot getSlot(Gate entryGate, Vehicle vehicle, ParkingLot lot) {
        if (entryGate == null || entryGate.getType() != Gate.GateType.ENTRY) {
            throw new IllegalArgumentException("Invalid Entry Gate");
        }

        List<ParkingSlot.SlotType> compatible = getCompatibleSlots(vehicle.getType());
        ParkingSlot bestSlot = null;
        int minDistance = Integer.MAX_VALUE;
        int gateFloor = -1;
        for (ParkingFLoor f : lot.getFloors()) {
            if (f.getGates().contains(entryGate)) {
                gateFloor = f.getFloorNumber();
                break;
            }
        }
        
        if (gateFloor == -1) {
            throw new IllegalArgumentException("Entry Gate not found in any floor");
        }

        for (ParkingSlot.SlotType typeToSearch : compatible) {
            if (bestSlot != null) break; 
            for (ParkingFLoor floor : lot.getFloors()) {
                for (ParkingSlot slot : floor.getSlots()) {
                    if (!slot.isOccupied() && slot.getType() == typeToSearch) {
                        int dist = Math.abs(floor.getFloorNumber() - gateFloor) * 1000 + slot.getSlotNumber();
                        if (dist < minDistance) {
                            minDistance = dist;
                            bestSlot = slot;
                        }
                    }
                }
            }
            if (bestSlot != null) {
                break; 
            }
        }
        return bestSlot;
    }

    private List<ParkingSlot.SlotType> getCompatibleSlots(Vehicle.VehicleType vType) {
        if (vType == Vehicle.VehicleType.BIKE) {
            return Arrays.asList(ParkingSlot.SlotType.SMALL, ParkingSlot.SlotType.MEDIUM, ParkingSlot.SlotType.LARGE);
        } else if (vType == Vehicle.VehicleType.CAR) {
            return Arrays.asList(ParkingSlot.SlotType.MEDIUM, ParkingSlot.SlotType.LARGE);
        } else {
            return Arrays.asList(ParkingSlot.SlotType.LARGE);
        }
    }
}
