public class RateLimiterFactory {
    /**
     * Creates a RateLimiter based on the specified type and configuration.
     * @param type The type of rate limiter to create.
     * @param capacity The maximum capacity.
     * @param rate The rate of refill/leak (per second) or the window size (in ms).
     * @return A concrete RateLimiter instance.
     */
    public static RateLimiter createRateLimiter(RateLimiterType type, int capacity, int rate) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(capacity, rate);
            case LEAKY_BUCKET:
                return new LeakyBucketRateLimiter(capacity, rate);
            case SLIDING_WINDOW_COUNTER:
                // Use default 1 second window for simplicity
                return new SlidingWindowCounterRateLimiter(capacity, 1000L);
            default:
                throw new IllegalArgumentException("Unknown rate limiter type: " + type);
        }
    }
}
