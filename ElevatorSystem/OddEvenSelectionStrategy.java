import java.util.List;

public class OddEvenSelectionStrategy implements IElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request) {
        for (Elevator elevator : elevators) {
            if (request.getFloorNumber() % 2 == 0 && elevator.getId() % 2 == 0) {
                return elevator;
            } 
            else if (request.getFloorNumber() % 2 != 0 && elevator.getId() % 2 != 0) {
                return elevator;
            }
        }
        return elevators.isEmpty() ? null : elevators.get(0);
    }
}
