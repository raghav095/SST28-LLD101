import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- External Resource Rate Limiter LLD ---");
        
        // Scenario: User T1 is allowed 5 external calls per minute.
        testExternalResourceProtection("T1", 5, 60 * 1000L);
        
        // Comparison between Fixed and Sliding Window
        System.out.println("\n--- Algorithm Comparison ---");
        testFixedWindowBoundarySpike();
    }

    private static void testExternalResourceProtection(String userId, int capacity, long windowMs) throws InterruptedException {
        System.out.println("\n[External Resource Protection Demo] User: " + userId + ", Limit: " + capacity + " per min");
        
        RateLimitManager manager = RateLimitManager.getInstance();
        Random random = new Random();

        // Simulate 10 incoming API calls
        for (int i = 1; i <= 10; i++) {
            System.out.println("\nIncoming API Request #" + i);
            
            // 1. Business logic runs first
            boolean needsExternalResource = random.nextBoolean() || i <= 6; // Force true for demo to show limiting
            
            if (!needsExternalResource) {
                System.out.println("Result: Success (No external resource call needed)");
                continue;
            }

            // 2. Rate limiting check is performed BEFORE calling the external resource
            System.out.println("External resource call REQUIRED. Checking quota...");

            // Use SLIDING_WINDOW_COUNTER for better accuracy
            boolean allowed = manager.isAllowed(userId, RateLimiterType.SLIDING_WINDOW_COUNTER, capacity, windowMs);

            if (allowed) {
                // 3. Make the external call
                System.out.println("Quota APPROVED. Calling External Resource...");
                System.out.println("Result: Success (External call completed)");
            } else {
                // 4. Handle rejection gracefully
                System.out.println("Quota EXCEEDED. External call rejected.");
                System.out.println("Result: Failure (Rate limited)");
            }
        }
    }

    private static void testFixedWindowBoundarySpike() throws InterruptedException {
        System.out.println("\n[Fixed Window Boundary Spike Demo] Limit: 2 per sec");
        FixedWindowRateLimiter fixed = new FixedWindowRateLimiter(2, 1000);

        // Send 2 at the END of window 1
        System.out.println("Sending 2 at the end of Window 1:");
        Thread.sleep(800);
        System.out.print(fixed.isAllowed() ? "✔ " : "✘ ");
        System.out.print(fixed.isAllowed() ? "✔ " : "✘ ");
        
        // Send 2 at the START of window 2
        System.out.println("\nSending 2 at the start of Window 2:");
        Thread.sleep(300);
        System.out.print(fixed.isAllowed() ? "✔ " : "✘ ");
        System.out.print(fixed.isAllowed() ? "✔ " : "✘ ");
        
        System.out.println("\nTotal in 1.1s: 4 (Should have been 2 max in any rolling 1s window)");
    }
}
