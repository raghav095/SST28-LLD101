import java.util.List;

public interface ITaskSchedulingStrategy {
    void scheduleRequest(List<Integer> pendingFloors, int currentFloor, Direction direction, int destination);
    int getNextFloor(List<Integer> pendingFloors, int currentFloor, Direction direction);
}
