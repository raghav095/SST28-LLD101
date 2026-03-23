import java.util.List;

public class ParkingFLoor {
    private final int floorNumber;
    private final List<Gate> gates;
    private final List<ParkingSlot> slots;

    public ParkingFLoor(int floorNumber, List<Gate> gates, List<ParkingSlot> slots) {
        this.floorNumber = floorNumber;
        this.gates = gates;
        this.slots = slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public List<Gate> getGates() {
        return gates;
    }
}
