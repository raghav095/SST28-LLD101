import java.util.List;

public interface IBookingService {
    Booking book(Show show, List<ShowSeat> seats, PaymentMode mode);
    void cancel(Booking booking);
}
