public class OuterPanel {
    private final Floor floor;

    public OuterPanel(Floor floor) {
        this.floor = floor;
    }

    public void pressButton(Direction direction) {
        if (floor.isUnderMaintenance()) {
            System.out.println("Floor " + floor.getFloorNumber() + " is under maintenance. Buttons are disabled.");
            return;
        }
        
        System.out.println("Floor " + floor.getFloorNumber() + " requested elevator going " + direction);
        ElevatorSystemDispatcher.getInstance().requestElevator(floor.getFloorNumber(), direction);
    }
}
