public class ShowSeat {
    private final int id;
    private final Seat seat;

    private SeatType type;
    private double price;

    private SeatStatus status;
    private long lockedAt;

    public ShowSeat(int id, Seat seat, SeatType type, double price) {
        this.id = id;
        this.seat = seat;
        this.type = type;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
        this.lockedAt = 0;
    }

    public synchronized boolean lockSeat() {
        long now = System.currentTimeMillis();
        
        if (status == SeatStatus.AVAILABLE || 
           (status == SeatStatus.LOCKED && (now - lockedAt) > 10 * 60 * 1000)) {
            
            status = SeatStatus.LOCKED;
            lockedAt = now;
            return true;
        }
        return false;
    }

    public synchronized void confirmBooking() {
        if (status == SeatStatus.LOCKED) {
            status = SeatStatus.BOOKED;
            lockedAt = 0;
        }
    }

    public synchronized void release() {
        status = SeatStatus.AVAILABLE;
        lockedAt = 0;
    }

    public int getId() { return id; }
    public SeatType getType() { return type; }
    public double getPrice() { return price; }
    public SeatStatus getStatus() { return status; }
}
