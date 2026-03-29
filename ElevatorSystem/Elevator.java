public class Elevator {
    private final int id;
    private int currentFloor;
    private ElevatorState state;
    private double currentWeight;
    private final double maxCapacity;
    private final Door door;
    private final InnerPanel innerPanel;
    private ElevatorController controller;

    public Elevator(int id, double maxCapacity) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.currentWeight = 0;
        this.door = new Door();
        this.innerPanel = new InnerPanel(this);
    }

    public void setController(ElevatorController controller) {
        this.controller = controller;
    }

    public void addWeight(double weight) {
        this.currentWeight += weight;
    }

    public void removeWeight(double weight) {
        this.currentWeight = Math.max(0, this.currentWeight - weight);
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorState getState() { return state; }
    public double getCurrentWeight() { return currentWeight; }
    public double getMaxCapacity() { return maxCapacity; }
    public Door getDoor() { return door; }
    public InnerPanel getInnerPanel() { return innerPanel; }
    public ElevatorController getController() { return controller; }
}
