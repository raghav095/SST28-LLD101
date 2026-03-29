import java.util.ArrayList;
import java.util.List;

public class ElevatorSystemDispatcher {
    private static ElevatorSystemDispatcher instance;
    private final List<ElevatorController> controllers;
    private IElevatorSelectionStrategy selectionStrategy;

    private ElevatorSystemDispatcher() {
        this.controllers = new ArrayList<>();
    }

    public static synchronized ElevatorSystemDispatcher getInstance() {
        if (instance == null) {
            instance = new ElevatorSystemDispatcher();
        }
        return instance;
    }

    public void setSelectionStrategy(IElevatorSelectionStrategy strategy) {
        this.selectionStrategy = strategy;
    }

    public void addElevatorController(ElevatorController controller) {
        controllers.add(controller);
    }

    public void requestElevator(int floorNumber, Direction direction) {
        if (selectionStrategy == null) {
            throw new IllegalStateException("Selection Strategy is not set.");
        }

        List<Elevator> healthyElevators = new ArrayList<>();
        for (ElevatorController controller : controllers) {
            if (controller.getElevator().getState() != ElevatorState.UNDER_MAINTENANCE) {
                healthyElevators.add(controller.getElevator());
            }
        }

        ElevatorRequest request = new ElevatorRequest(floorNumber, direction);
        Elevator selectedElevator = selectionStrategy.selectElevator(healthyElevators, request);

        if (selectedElevator != null) {
            System.out.println("Central Dispatcher selected Elevator " + selectedElevator.getId());
            selectedElevator.getController().addNewDestination(floorNumber);
        } else {
            System.out.println("SYSTEM BUSY: No elevator available to handle request at this time.");
        }
    }
}
