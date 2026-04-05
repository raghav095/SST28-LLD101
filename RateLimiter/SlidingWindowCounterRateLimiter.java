import java.util.concurrent.locks.ReentrantLock;

/**
 * Sliding Window Counter Algorithm implementation.
 * More accurate than Fixed Window, avoids boundary spikes by interpolating across windows.
 */
public class SlidingWindowCounterRateLimiter implements RateLimiter {
    private final int capacity;
    private final long windowSizeMs; // size of each window in milliseconds
    private long currentWindowStart;
    private int currentWindowCount;
    private int previousWindowCount;
    private final ReentrantLock lock = new ReentrantLock();

    public SlidingWindowCounterRateLimiter(int capacity, long windowSizeMs) {
        this.capacity = capacity;
        this.windowSizeMs = windowSizeMs;
        this.currentWindowStart = System.currentTimeMillis();
        this.currentWindowCount = 0;
        this.previousWindowCount = 0;
    }

    @Override
    public boolean isAllowed() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            updateWindow(now);
            
            double elapsedTimeInCurrentWindow = now - currentWindowStart;
            double overlapPercentage = (windowSizeMs - elapsedTimeInCurrentWindow) / (double) windowSizeMs;
            double weightedCount = overlapPercentage * previousWindowCount + currentWindowCount;
            
            if (weightedCount < capacity) {
                currentWindowCount++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void updateWindow(long now) {
        if (now - currentWindowStart >= windowSizeMs) {
            long windowsPassed = (now - currentWindowStart) / windowSizeMs;
            
            if (windowsPassed == 1) {
                previousWindowCount = currentWindowCount;
            } else {
                previousWindowCount = 0;
            }
            
            currentWindowCount = 0;
            currentWindowStart = currentWindowStart + windowsPassed * windowSizeMs;
        }
    }
}
