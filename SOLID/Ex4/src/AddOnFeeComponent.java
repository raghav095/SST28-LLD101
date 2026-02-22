public class AddOnFeeComponent implements FeeComponent {
    private final AddOn addOn;
    private final Money monthly;

    public AddOnFeeComponent(AddOn addOn, double monthly) {
        this.addOn = addOn;
        this.monthly = new Money(monthly);
    }

    @Override
    public Money monthlyCharge(BookingRequest req) {
        if (req.addOns.contains(addOn)) return monthly;
        return new Money(0.0);
    }
}
