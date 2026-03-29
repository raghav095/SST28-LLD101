import java.util.List;

public class NearestElevatorSelectionStrategy implements IElevatorSelectionStrategy {
    
    @Override
    public Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int currentFloor = elevator.getCurrentFloor();
            ElevatorState state = elevator.getState();
            
            if (state == ElevatorState.IDLE) {
                int distance = Math.abs(currentFloor - request.getFloorNumber());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
            else if (state == ElevatorState.MOVING_UP && request.getDirection() == Direction.UP && currentFloor <= request.getFloorNumber()) {
                int distance = request.getFloorNumber() - currentFloor;
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
            else if (state == ElevatorState.MOVING_DOWN && request.getDirection() == Direction.DOWN && currentFloor >= request.getFloorNumber()) {
                int distance = currentFloor - request.getFloorNumber();
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }
        
        return bestElevator != null ? bestElevator : (elevators.isEmpty() ? null : elevators.get(0));
    }
}
