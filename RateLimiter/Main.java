import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Rate Limiter LLD Demonstration ---");

        // 1. Token Bucket Demo
        demoTokenBucket();

        // 2. Leaky Bucket Demo
        demoLeakyBucket();

        // 3. Sliding Window Counter Demo
        demoSlidingWindowCounter();
    }

    private static void demoTokenBucket() throws InterruptedException {
        System.out.println("\n[Token Bucket Demo] Capacity: 5, Refill Rate: 2/sec");
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 2);

        // Burst: Try to send 10 requests at once
        System.out.println("Trying to send 10 requests immediately (Burst):");
        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.isAllowed();
            System.out.print(allowed ? "✔ " : "✘ ");
        }
        System.out.println("\nWaiting for 1 second...");
        Thread.sleep(1000);

        System.out.println("Trying to send 5 more requests after 1s (should allow ~2 tokens):");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.isAllowed();
            System.out.print(allowed ? "✔ " : "✘ ");
        }
        System.out.println();
    }

    private static void demoLeakyBucket() throws InterruptedException {
        System.out.println("\n[Leaky Bucket Demo] Capacity: 3, Leak Rate: 1/sec");
        LeakyBucketRateLimiter limiter = new LeakyBucketRateLimiter(3, 1);

        System.out.println("Trying to send 5 requests immediately:");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.isAllowed();
            System.out.print(allowed ? "✔ " : "✘ ");
        }
        
        System.out.println("\nWaiting for 2 seconds...");
        Thread.sleep(2000);

        System.out.println("Trying to send 3 more requests after 2s (should allow 2 tokens leaked):");
        for (int i = 1; i <= 3; i++) {
            boolean allowed = limiter.isAllowed();
            System.out.print(allowed ? "✔ " : "✘ ");
        }
        System.out.println();
    }

    private static void demoSlidingWindowCounter() throws InterruptedException {
        System.out.println("\n[Sliding Window Counter Demo] Capacity: 5, Window: 1s");
        SlidingWindowCounterRateLimiter limiter = new SlidingWindowCounterRateLimiter(5, 1000);

        System.out.println("Sending 5 requests (Limit reached):");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.isAllowed();
            System.out.print(allowed ? "✔ " : "✘ ");
        }
        
        System.out.println("\nTry 1 more immediately:");
        System.out.println(limiter.isAllowed() ? "✔ " : "✘ ");

        System.out.println("Waiting 500ms (50% through next window)...");
        Thread.sleep(500);
        
        System.out.println("Try requests (interpolated limit check)...");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.isAllowed();
            System.out.print(allowed ? "✔ " : "✘ ");
        }
        System.out.println();
    }
}
