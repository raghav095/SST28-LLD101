import java.util.List;

public class PricingService implements IPricingService {

    private final List<ShowPricingStrategy> pricingStrategies;

    public PricingService(List<ShowPricingStrategy> pricingStrategies) {
        this.pricingStrategies = pricingStrategies;
    }

    @Override
    public double calculatePrice(SeatType type, ShowType showType) {
        double currentPrice = 0;
        for (ShowPricingStrategy strategy : pricingStrategies) {
            currentPrice = strategy.apply(currentPrice, type, showType);
        }
        return currentPrice;
    }
}
