import java.util.List;

public class Booking {
    private final int id;
    private final Show show;
    private final List<ShowSeat> seats;

    private BookingStatus status;
    private Payment payment;

    public Booking(int id, Show show, List<ShowSeat> seats, Payment payment) {
        this.id = id;
        this.show = show;
        this.seats = seats;
        this.payment = payment;
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;

        for (ShowSeat seat : seats) {
            seat.release();
        }

        payment.refund();
    }
}
