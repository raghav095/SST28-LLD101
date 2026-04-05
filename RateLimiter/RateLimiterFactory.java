public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterType type, int capacity, long rateOrWindow) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(capacity, (int) rateOrWindow);
            case LEAKY_BUCKET:
                return new LeakyBucketRateLimiter(capacity, (int) rateOrWindow);
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter(capacity, rateOrWindow);
            case SLIDING_WINDOW_COUNTER:
                return new SlidingWindowCounterRateLimiter(capacity, rateOrWindow);
            default:
                throw new IllegalArgumentException("Unknown rate limiter type: " + type);
        }
    }
}
