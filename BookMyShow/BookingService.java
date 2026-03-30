import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BookingService implements IBookingService {

    private final IPaymentService paymentService;

    public BookingService(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Booking book(User user, Show show, List<ShowSeat> seats, PaymentMode mode) {
        List<ShowSeat> successfullyLocked = new ArrayList<>();

        seats.sort(Comparator.comparingInt(ShowSeat::getId));

        for (ShowSeat seat : seats) {
            if (!seat.lockSeat()) {
                for (ShowSeat booked : successfullyLocked) {
                    booked.release();
                }
                throw new SeatNotAvailableException("Seat ID " + seat.getId() + " is already secured by someone else.");
            }
            successfullyLocked.add(seat);
        }

        double total = seats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();

        try {
            Payment payment = paymentService.processPayment(total, mode);

            for (ShowSeat seat : successfullyLocked) {
                seat.confirmBooking();
            }

            return new Booking(
                    new Random().nextInt(),
                    show,
                    seats,
                    payment,
                    user
            );
        } catch (Exception e) {
            for (ShowSeat seat : successfullyLocked) {
                seat.release();
            }
            throw new PaymentFailedException("Payment failed or aborted: " + e.getMessage());
        }
    }

    @Override
    public void cancel(Booking booking) {
        booking.cancel();
    }
}
