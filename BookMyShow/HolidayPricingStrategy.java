public class HolidayPricingStrategy implements ShowPricingStrategy {
    @Override
    public double apply(double currentPrice, SeatType seatType, ShowType showType) {
        if (showType == ShowType.HOLIDAY) {
            return currentPrice + 100;
        }
        return currentPrice;
    }
}
