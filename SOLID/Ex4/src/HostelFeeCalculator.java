import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final List<FeeComponent> components;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.components = List.of(
            new RoomTypeFeeComponent(LegacyRoomTypes.SINGLE, 14000.0),
            new RoomTypeFeeComponent(LegacyRoomTypes.DOUBLE, 15000.0),
            new RoomTypeFeeComponent(LegacyRoomTypes.TRIPLE, 12000.0),
            new RoomTypeFeeComponent(LegacyRoomTypes.DELUXE, 16000.0),
            new AddOnFeeComponent(AddOn.MESS, 1000.0),
            new AddOnFeeComponent(AddOn.LAUNDRY, 500.0),
            new AddOnFeeComponent(AddOn.GYM, 300.0)
        );
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = new Money(0.0);
        for (FeeComponent c : components) {
            total = total.plus(c.monthlyCharge(req));
        }
        return total;
    }
}
