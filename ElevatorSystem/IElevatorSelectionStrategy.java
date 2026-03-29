import java.util.List;

public interface IElevatorSelectionStrategy {
    Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request);
}
