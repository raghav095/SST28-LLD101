public class ElevatorRequest {
    private final int floorNumber;
    private final Direction direction;

    public ElevatorRequest(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    public int getFloorNumber() { return floorNumber; }
    public Direction getDirection() { return direction; }
}
