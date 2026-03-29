public class Floor {
    private final int floorNumber;
    private boolean isUnderMaintenance;
    private final OuterPanel outerPanel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.isUnderMaintenance = false;
        this.outerPanel = new OuterPanel(this);
    }

    public void setUnderMaintenance(boolean maintenance) {
        this.isUnderMaintenance = maintenance;
    }

    public boolean isUnderMaintenance() { return isUnderMaintenance; }
    public int getFloorNumber() { return floorNumber; }
    public OuterPanel getOuterPanel() { return outerPanel; }
}
