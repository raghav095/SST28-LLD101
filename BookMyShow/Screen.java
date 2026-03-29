import java.util.List;

public class Screen {
    private final int id;
    private final ScreenType type;
    private final List<Seat> seats;

    public Screen(int id, ScreenType type, List<Seat> seats) {
        this.id = id;
        this.type = type;
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
