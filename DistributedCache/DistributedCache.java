import java.util.ArrayList;
import java.util.List;

public class DistributedCache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy distributionStrategy;
    private final Database<K, V> database;

    public DistributedCache(int numNodes, int nodeCapacity, DistributionStrategy strategy, Database<K, V> database) {
        this.nodes = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new CacheNode<>(nodeCapacity, new LRUEvictionPolicy<>()));
        }
        this.distributionStrategy = strategy;
        this.database = database;
    }

    public V get(K key) {
        int nodeIndex = distributionStrategy.getTargetNodeIndex(key.toString(), nodes.size());
        CacheNode<K, V> targetNode = nodes.get(nodeIndex);

        V value = targetNode.get(key);
        if (value != null) {
            System.out.println("[Cache] Hit: " + key + " on Node " + nodeIndex);
            return value;
        }

        System.out.println("[Cache] Miss: " + key + " on Node " + nodeIndex);
        value = database.fetch(key);

        if (value != null) {
            targetNode.put(key, value);
        }

        return value;
    }

    public void put(K key, V value) {
        int nodeIndex = distributionStrategy.getTargetNodeIndex(key.toString(), nodes.size());
        CacheNode<K, V> targetNode = nodes.get(nodeIndex);

        System.out.println("[Cache] Put: " + key + " on Node " + nodeIndex);
        targetNode.put(key, value);
        database.put(key, value);
    }
}
