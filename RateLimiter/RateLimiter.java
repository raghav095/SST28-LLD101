/**
 * Strategy interface for rate limiting algorithms.
 */
public interface RateLimiter {
    /**
     * Determines whether a request is allowed to proceed.
     * @return true if the request is permitted, false otherwise.
     */
    boolean isAllowed();
}
