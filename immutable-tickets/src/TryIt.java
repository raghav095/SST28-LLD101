import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Updated demo showing immutability.
 *
 * FIXES APPLIED:
 * - We now capture the new ticket returned by service updates.
 * - Trying to change `tags` throws an UnsupportedOperationException.
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Create a ticket using the service (which now uses the Builder internally)
        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // 2. Demonstrate updates now return NEW instances instead of modifying the old
        // one
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nAfter service updates (t3): " + t3);
        System.out.println("Original ticket remains untouched (t1): " + t1);
        System.out.println("Are they the same object? " + (t1 == t3)); // Should be false

        // 3. Demonstrate external mutation is blocked
        List<String> tags = t3.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE"); // This will throw an exception
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccessfully blocked external tag mutation: " + e.toString());
        }

        System.out.println("Final ticket state: " + t3);
    }
}
