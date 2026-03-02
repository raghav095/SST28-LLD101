package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Global metrics registry properly implemented as a Singleton.
 *
 * FIXES APPLIED:
 * 1. volatile INSTANCE and double-checked locking for thread-safety.
 * 2. private constructor that throws if INSTANCE already exists (blocks
 * reflection).
 * 3. readResolve() method to preserve singleton during deserialization.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Fixed: volatile prevents instruction reordering in double-checked locking
    private static volatile MetricsRegistry INSTANCE;

    private final Map<String, Long> counters = new HashMap<>();

    // Fixed: private constructor
    private MetricsRegistry() {
        // Fixed: block reflection
        if (INSTANCE != null) {
            throw new RuntimeException("Use getInstance() to get the single instance of this class.");
        }
    }

    // Fixed: thread-safe lazy init using double-checked locking
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (MetricsRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // Fixed: preserve singleton on deserialization
    @Serial
    protected Object readResolve() {
        return getInstance();
    }
}
