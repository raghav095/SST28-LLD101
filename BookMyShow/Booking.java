import java.util.List;

public class Booking {
    private final int id;
    private final Show show;
    private final List<ShowSeat> seats;

    private BookingStatus status;
    private Payment payment;
    private final User user;

    public Booking(int id, Show show, List<ShowSeat> seats, Payment payment, User user) {
        this.id = id;
        this.show = show;
        this.seats = seats;
        this.payment = payment;
        this.status = BookingStatus.CONFIRMED;
        this.user = user;
    }

    public void cancel() {
        if (System.currentTimeMillis() >= show.getStartTime().getTime()) {
            throw new IllegalStateException("Cannot cancel after the show has started.");
        }

        this.status = BookingStatus.CANCELLED;

        for (ShowSeat seat : seats) {
            seat.release();
        }

        payment.refund();
    }
}
