public class BasePricingStrategy implements ShowPricingStrategy {
    @Override
    public double apply(double currentPrice, SeatType seatType, ShowType showType) {
        switch (seatType) {
            case GOLD: return currentPrice + 300;
            case PREMIUM: return currentPrice + 200;
            case NORMAL: return currentPrice + 100;
            default: return currentPrice + 100;
        }
    }
}
