import java.util.concurrent.locks.ReentrantLock;

public class LeakyBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final int leakRate; 
    private double currentWater;
    private long lastLeakTime;
    private final ReentrantLock lock = new ReentrantLock();

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.currentWater = 0.0;
        this.lastLeakTime = System.currentTimeMillis();
    }

    @Override
    public boolean isAllowed() {
        lock.lock();
        try {
            leak();
            if (currentWater < (double) capacity) {
                currentWater += 1.0;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void leak() {
        long now = System.currentTimeMillis();
        double timeElapsed = (now - lastLeakTime) / 1000.0;
        double leakAmount = timeElapsed * leakRate;
        
        if (leakAmount > 0) {
            currentWater = Math.max(0.0, currentWater - leakAmount);
            lastLeakTime = now;
        }
    }
}
