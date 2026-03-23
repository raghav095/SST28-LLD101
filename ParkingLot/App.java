import java.util.Arrays;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        
        Gate gate1 = new Gate(1, Gate.GateType.ENTRY);
        Gate gate2 = new Gate(2, Gate.GateType.EXIT);

        ParkingSlot slot1 = new ParkingSlot(101, 1, ParkingSlot.SlotType.SMALL);
        ParkingSlot slot2 = new ParkingSlot(102, 1, ParkingSlot.SlotType.MEDIUM);
        ParkingSlot slot3 = new ParkingSlot(201, 2, ParkingSlot.SlotType.LARGE);

        ParkingFloor floor1 = new ParkingFloor(1, Arrays.asList(gate1, gate2), Arrays.asList(slot1, slot2));
        ParkingFloor floor2 = new ParkingFloor(2, Arrays.asList(), Arrays.asList(slot3));

        ParkingLot parkingLot = new ParkingLot("Central Parking", Arrays.asList(floor1, floor2));

        SlotAssignmentStrategy slotAssignmentStrategy = new NearestSlotAssignmentStrategy();
        TicketService ticketService = new TicketService(slotAssignmentStrategy);

        PricingStrategy pricingStrategy = new HourlyPricingStrategy();
        BillService billService = new BillService(pricingStrategy);

        System.out.println("Status prior to parking:");
        printStatus(parkingLot.status());

       
        Vehicle bike = new Vehicle("B-123", "Red", Vehicle.VehicleType.BIKE, "Honda");
        long entryTime = System.currentTimeMillis();
        
        System.out.println("\n--- Parking Bike ---");
        Ticket ticket1 = ticketService.generateTicket(bike, entryTime, 1, parkingLot);

        System.out.println("\n--- Parking Car ---");
        Vehicle car = new Vehicle("C-456", "Blue", Vehicle.VehicleType.CAR, "Sedan");
        Ticket ticket2 = ticketService.generateTicket(car, entryTime, 1, parkingLot);

        System.out.println("\n--- Parking Another Bike (should upgrade to LARGE since SMALL and MEDIUM are full) ---");
        Vehicle bike2 = new Vehicle("B-999", "Black", Vehicle.VehicleType.BIKE, "Yamaha");
        Ticket ticket3 = ticketService.generateTicket(bike2, entryTime, 1, parkingLot);

        System.out.println("\nStatus after parking:");
        printStatus(parkingLot.status());

       
        System.out.println("\n--- Exiting Car after 2.5 hours ---");
        long exitTime2 = entryTime + (long)(2.5 * 60 * 60 * 1000); 
        Bill bill2 = billService.generateBill(ticket2, exitTime2);

        System.out.println("\n--- Exiting Bike (Upgraded to Large) after 2.5 hours ---");
        long exitTime3 = entryTime + (long)(2.5 * 60 * 60 * 1000); 
        Bill bill3 = billService.generateBill(ticket3, exitTime3);

        System.out.println("\nStatus after exiting:");
        printStatus(parkingLot.status());
    }

    private static void printStatus(Map<ParkingSlot.SlotType, Integer> status) {
        for (Map.Entry<ParkingSlot.SlotType, Integer> entry : status.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
    }
}
