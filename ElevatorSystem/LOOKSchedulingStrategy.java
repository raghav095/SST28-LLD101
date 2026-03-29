import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LOOKSchedulingStrategy implements ITaskSchedulingStrategy {

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

        List<Integer> upRequests = new ArrayList<>();
        List<Integer> downRequests = new ArrayList<>();

        for (int floor : pendingFloors) {
            if (floor >= currentFloor) {
                upRequests.add(floor);
            } else {
                downRequests.add(floor);
            }
        }

        Collections.sort(upRequests);
        Collections.sort(downRequests, Collections.reverseOrder());

        if (direction == Direction.UP) {
            if (!upRequests.isEmpty()) {
                return upRequests.get(0);
            } else if (!downRequests.isEmpty()) {
                return downRequests.get(0);
            }
        } else {
            if (!downRequests.isEmpty()) {
                return downRequests.get(0);
            } else if (!upRequests.isEmpty()) {
                return upRequests.get(0);
            }
        }
        return pendingFloors.get(0);
    }
}
