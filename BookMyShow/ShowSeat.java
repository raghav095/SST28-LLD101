public class ShowSeat {
    private final int id;
    private final Seat seat;

    private SeatType type;
    private double price;

    private boolean isBooked;

    public ShowSeat(int id, Seat seat, SeatType type, double price) {
        this.id = id;
        this.seat = seat;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    public synchronized boolean book() {
        if (isBooked) return false;
        isBooked = true;
        return true;
    }

    public synchronized void release() {
        isBooked = false;
    }

    public SeatType getType() { return type; }
    public double getPrice() { return price; }
    public boolean isBooked() { return isBooked; }
}
