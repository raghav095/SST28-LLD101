import java.util.HashMap;
import java.util.Map;

public class Database<K, V> {
    private final Map<K, V> records;

    public Database() {
        this.records = new HashMap<>();
    }

    public void put(K key, V value) {
        records.put(key, value);
    }

    public V fetch(K key) {
        System.out.println("[DB] Fetching key: " + key);
        return records.get(key);
    }
}
