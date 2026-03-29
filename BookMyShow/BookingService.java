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

        // 1. Lock seats
        for (ShowSeat seat : seats) {
            if (!seat.book()) {
                // Rollback successfully locked seats in this transaction
                for (ShowSeat booked : successfullyBooked) {
                    booked.release();
                }
                throw new RuntimeException("Seat already booked");
            }
            successfullyBooked.add(seat);
        }

        // 2. Calculate amount
        double total = seats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();

        // 3. Payment
        Payment payment = paymentService.processPayment(total, mode);

        // 4. Create booking
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
