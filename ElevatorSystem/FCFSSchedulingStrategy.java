import java.util.List;

public class FCFSSchedulingStrategy implements ITaskSchedulingStrategy {

    @Override
    public void scheduleRequest(List<Integer> pendingFloors, int currentFloor, Direction direction, int destination) {
        if (!pendingFloors.contains(destination)) {
            pendingFloors.add(destination);
        }
    }

    @Override
    public int getNextFloor(List<Integer> pendingFloors, int currentFloor, Direction direction) {
        if (pendingFloors.isEmpty()) {
            throw new IllegalStateException("No pending requests");
        }
        return pendingFloors.get(0);
    }
}
