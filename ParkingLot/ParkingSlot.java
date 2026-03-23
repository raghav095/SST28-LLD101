public class ParkingSlot {
    private final int slotNumber;
    private final int floorNumber;
    private final SlotType type;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public enum SlotType {
        SMALL(50.0),   
        MEDIUM(80.0),  
        LARGE(120.0);  

        private final double hourlyRate;

        SlotType(double hourlyRate) {
            this.hourlyRate = hourlyRate;
        }

        public double getHourlyRate() {
            return hourlyRate;
        }
    }


    
    public ParkingSlot(int slotNumber, int floorNumber, SlotType type) {
        this.slotNumber = slotNumber;
        this.floorNumber = floorNumber;
        this.type = type;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public SlotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.isOccupied = true;
        this.parkedVehicle = vehicle;
    }

    public void removeVehicle() {
        this.isOccupied = false;
        this.parkedVehicle = null;
    }
}
