import java.util.Map;

public class SeatCategoryConfig {
    private final Map<Integer, SeatType> seatTypeMap;

    public SeatCategoryConfig(Map<Integer, SeatType> seatTypeMap) {
        this.seatTypeMap = seatTypeMap;
    }

    public SeatType getSeatType(Seat seat) {
        return seatTypeMap.get(seat.getId());
    }
}
