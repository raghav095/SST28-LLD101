public class Vehicle {

    private final String vehicleNumber;
    private final String color;
    private final VehicleType type;
    private final String model;

    public enum VehicleType {
        BIKE, CAR, BUS
    }

    public Vehicle(String vehicleNumber, String color, VehicleType type, String model) {
        this.vehicleNumber = vehicleNumber;
        this.color = color;
        this.type = type;
        this.model = model;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getColor() {
        return color;
    }

    public VehicleType getType() {
        return type;
    }

    public String getModel() {
        return model;
    }
}
