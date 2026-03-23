public class Gate {
    private final int gateID;
    private final GateType type;

    public enum GateType{
        ENTRY, EXIT;
    }

    public Gate(int gateID, GateType type) {
        this.gateID = gateID;
        this.type = type;
    }

    public int getGateID() {
        return gateID;
    }

    public GateType getType() {
        return type;
    }
}
