import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookingService implements IBookingService {

    private final IPaymentService paymentService;

    public BookingService(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Booking book(Show show, List<ShowSeat> seats, PaymentMode mode) {
        List<ShowSeat> successfullyBooked = new ArrayList<>();

        for (ShowSeat seat : seats) {
            if (!seat.book()) {
                for (ShowSeat booked : successfullyBooked) {
                    booked.release();
                }
                throw new RuntimeException("Seat already booked");
            }
            successfullyBooked.add(seat);
        }

        double total = seats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();

        Payment payment = paymentService.processPayment(total, mode);

        return new Booking(
                new Random().nextInt(),
                show,
                seats,
                payment
        );
    }

    @Override
    public void cancel(Booking booking) {
        booking.cancel();
    }
}
