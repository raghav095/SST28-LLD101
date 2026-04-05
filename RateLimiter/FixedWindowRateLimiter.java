import java.util.concurrent.locks.ReentrantLock;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int capacity;
    private final long windowSizeMs;
    private int count;
    private long lastWindowStartTime;
    private final ReentrantLock lock = new ReentrantLock();

    public FixedWindowRateLimiter(int capacity, long windowSizeMs) {
        this.capacity = capacity;
        this.windowSizeMs = windowSizeMs;
        this.lastWindowStartTime = System.currentTimeMillis();
        this.count = 0;
    }

    @Override
    public boolean isAllowed() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            if (now - lastWindowStartTime >= windowSizeMs) {
                lastWindowStartTime = now;
                count = 0;
            }
            
            if (count < capacity) {
                count++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
