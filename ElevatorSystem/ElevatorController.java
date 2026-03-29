import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private final Elevator elevator;
    private final ITaskSchedulingStrategy schedulingStrategy;
    private final List<Integer> pendingDestinations;

    public ElevatorController(Elevator elevator, ITaskSchedulingStrategy schedulingStrategy) {
        this.elevator = elevator;
        this.schedulingStrategy = schedulingStrategy;
        this.pendingDestinations = new ArrayList<>();
    }

    public void addNewDestination(int floor) {
        schedulingStrategy.scheduleRequest(pendingDestinations, elevator.getCurrentFloor(), 
            elevator.getState() == ElevatorState.MOVING_DOWN ? Direction.DOWN : Direction.UP, floor);
    }

    public void processNext() {
        if (pendingDestinations.isEmpty()) {
            elevator.setState(ElevatorState.IDLE);
            return;
        }

        int nextFloor = schedulingStrategy.getNextFloor(pendingDestinations, elevator.getCurrentFloor(), 
            elevator.getState() == ElevatorState.MOVING_DOWN ? Direction.DOWN : Direction.UP);
        
        System.out.println("Elevator " + elevator.getId() + " moving to " + nextFloor);
        elevator.setCurrentFloor(nextFloor);
        pendingDestinations.remove(Integer.valueOf(nextFloor));
        
        elevator.getDoor().open();
    }
    
    public List<Integer> getPendingDestinations() { return pendingDestinations; }
    public Elevator getElevator() { return elevator; }
}
