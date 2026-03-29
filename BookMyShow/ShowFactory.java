import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ShowFactory {

    private final IPricingService pricingService;

    public ShowFactory(IPricingService pricingService) {
        this.pricingService = pricingService;
    }

    public Show createShow(int id, Movie movie, Screen screen,
                           ShowType showType, Date time,
                           SeatCategoryConfig config) {

        List<ShowSeat> showSeats = new ArrayList<>();

        for (Seat seat : screen.getSeats()) {

            SeatType type = config.getSeatType(seat);

            double price = pricingService.calculatePrice(type, showType);

            ShowSeat ss = new ShowSeat(
                    generateId(), seat, type, price
            );

            showSeats.add(ss);
        }

        return new Show(id, movie, screen, showType, time, showSeats);
    }

    private int generateId() {
        return new Random().nextInt(100000);
    }
}
