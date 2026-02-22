public class RoomTypeFeeComponent implements FeeComponent {
    private final int roomType;
    private final Money monthly;

    public RoomTypeFeeComponent(int roomType, double monthly) {
        this.roomType = roomType;
        this.monthly = new Money(monthly);
    }

    @Override
    public Money monthlyCharge(BookingRequest req) {
        if (req.roomType == roomType) return monthly;
        return new Money(0.0);
    }
}
