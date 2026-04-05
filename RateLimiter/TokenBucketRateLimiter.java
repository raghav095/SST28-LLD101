import java.util.concurrent.locks.ReentrantLock;

/**
 * Token Bucket Algorithm implementation.
 * Allows for bursts of traffic up to the bucket capacity.
 */
public class TokenBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final int refillRate; // tokens per second
    private double currentTokens;
    private long lastRefillTime;
    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.currentTokens = (double) capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    @Override
    public boolean isAllowed() {
        lock.lock();
        try {
            refill();
            if (currentTokens >= 1.0) {
                currentTokens -= 1.0;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double timeElapsed = (now - lastRefillTime) / 1000.0;
        double tokensToAdd = timeElapsed * refillRate;
        
        if (tokensToAdd > 0) {
            currentTokens = Math.min((double) capacity, currentTokens + tokensToAdd);
            lastRefillTime = now;
        }
    }
}
