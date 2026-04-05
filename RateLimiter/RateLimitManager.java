import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class RateLimitManager {
    private final Map<String, RateLimiter> limiters;
    private static RateLimitManager instance;

    private RateLimitManager() {
        this.limiters = new ConcurrentHashMap<>();
    }

    public static synchronized RateLimitManager getInstance() {
        if (instance == null) {
            instance = new RateLimitManager();
        }
        return instance;
    }

    public boolean isAllowed(String key, RateLimiterType type, int capacity, long windowOrRate) {
        RateLimiter limiter = limiters.computeIfAbsent(key, k -> RateLimiterFactory.createRateLimiter(type, capacity, windowOrRate));
        return limiter.isAllowed();
    }
}
