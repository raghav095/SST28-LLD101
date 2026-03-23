import java.util.List;

public class ParkingFLoor {
    private final  int floorNumber;
    private final List<Gate> entryGate;
    private final   List<Gate> exitGate;
    private final List<ParkingSlot> slots;

    public ParkingFLoor(int floorNumber, List<Gate> entryGate, List<Gate> exitGate, List<ParkingSlot> slots) {
        this.floorNumber = floorNumber;
        this.entryGate = entryGate;
        this.exitGate = exitGate;
        this.slots = slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public List<Gate> getEntryGate() {
        return entryGate;
    }

    public List<Gate> getExitGate() {
        return exitGate;
    }

    

}
