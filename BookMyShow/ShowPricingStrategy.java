public interface ShowPricingStrategy {
    double apply(double currentPrice, SeatType seatType, ShowType showType);
}
