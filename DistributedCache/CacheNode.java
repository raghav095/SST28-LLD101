import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {
    private final int capacity;
    private final Map<K, V> storage;
    private final EvictionPolicy<K> evictionPolicy;

    public CacheNode(int capacity, EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public synchronized V get(K key) {
        if (!storage.containsKey(key)) return null;
        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    public synchronized void put(K key, V value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() >= capacity) {
            K evictedKey = evictionPolicy.evictKey();
            if (evictedKey != null) {
                storage.remove(evictedKey);
            }
        }

        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}
