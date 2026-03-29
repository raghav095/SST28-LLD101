public class WeekendPricingStrategy implements ShowPricingStrategy {
    @Override
    public double apply(double currentPrice, SeatType seatType, ShowType showType) {
        if (showType == ShowType.WEEKEND) {
            return currentPrice + 50;
        }
        return currentPrice;
    }
}
