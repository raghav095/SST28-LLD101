public class Gate {
    private final int gateID;
    private final GateType type;
    private final int floorNumber;

    enum GateType{
        ENTRY, EXIT;
    }

    public Gate(int gateID, GateType type, int floorNumber) {
        this.gateID = gateID;
        this.type = type;
        this.floorNumber = floorNumber;
    }

    public int getGateID() {
        return gateID;
    }

    public GateType getType() {
        return type;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
    
}
