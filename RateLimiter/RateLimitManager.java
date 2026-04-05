import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Manages rate limiters for multiple clients.
 */
public class RateLimitManager {
    private final Map<String, RateLimiter> clientLimiters;
    private static RateLimitManager instance;

    private RateLimitManager() {
        this.clientLimiters = new ConcurrentHashMap<>();
    }

    public static synchronized RateLimitManager getInstance() {
        if (instance == null) {
            instance = new RateLimitManager();
        }
        return instance;
    }

    /**
     * Checks if a request for a specific client is allowed.
     * @param clientId The unique client ID.
     * @param type The type of rate limiter to use if not already initialized.
     * @param capacity The capacity for the limiter.
     * @param rate The rate for the limiter.
     * @return true if allowed, false otherwise.
     */
    public boolean isAllowed(String clientId, RateLimiterType type, int capacity, int rate) {
        // Compute if absent ensures we only create one limiter per client
        RateLimiter limiter = clientLimiters.computeIfAbsent(clientId, k -> RateLimiterFactory.createRateLimiter(type, capacity, rate));
        return limiter.isAllowed();
    }
}
