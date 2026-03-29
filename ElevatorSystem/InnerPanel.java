public class InnerPanel {
    private final Elevator elevator;

    public InnerPanel(Elevator elevator) {
        this.elevator = elevator;
    }

    public void selectFloor(int destinationFloor) {
        System.out.println("Lift " + elevator.getId() + " inner panel pressed for floor: " + destinationFloor);
        elevator.getController().addNewDestination(destinationFloor);
    }

    public void pressOpenDoor() {
        if (elevator.getState() == ElevatorState.IDLE) {
            elevator.getDoor().open();
        } else {
            System.out.println("Safety lock: Cannot open doors while elevator is moving!");
        }
    }

    public void pressCloseDoor() {
        if (elevator.getState() == ElevatorState.IDLE) {
            elevator.getDoor().close(elevator.getCurrentWeight(), elevator.getMaxCapacity());
        }
    }

    public void pressAlarm() {
        System.out.println("🚨 ALARM PRESSED! Elevator " + elevator.getId() + " is stuck!");
        elevator.setState(ElevatorState.UNDER_MAINTENANCE);
        elevator.getController().getPendingDestinations().clear();
        System.out.println("Emergency Override: Halted safely at nearest logical floor -> Floor " + elevator.getCurrentFloor());
        elevator.getDoor().open();
        System.out.println("Emergency: Doors forced open for immediate evacuation.");
    }
}
