public interface SlotAssignmentStrategy {
    ParkingSlot getSlot(Gate entryGate, Vehicle vehicle, ParkingLot lot);
}
