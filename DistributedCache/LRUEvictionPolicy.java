import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final Map<K, Node<K>> mapper;
    private final DoublyLinkedList<K> dll;

    public LRUEvictionPolicy() {
        this.mapper = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    @Override
    public void keyAccessed(K key) {
        if (mapper.containsKey(key)) {
            dll.detach(mapper.get(key));
            dll.addAtFront(mapper.get(key));
        } else {
            Node<K> newNode = dll.addAtFront(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public K evictKey() {
        Node<K> last = dll.getLast();
        if (last == null) return null;
        dll.detach(last);
        mapper.remove(last.key);
        return last.key;
    }

    private static class Node<K> {
        K key;
        Node<K> next, prev;
        Node(K key) { this.key = key; }
    }

    private static class DoublyLinkedList<K> {
        private Node<K> head, tail;

        Node<K> addAtFront(K key) {
            Node<K> node = new Node<>(key);
            addAtFront(node);
            return node;
        }

        void addAtFront(Node<K> node) {
            if (head == null) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
        }

        void detach(Node<K> node) {
            if (node == null) return;
            if (node.prev != null) node.prev.next = node.next;
            if (node.next != null) node.next.prev = node.prev;
            if (node == head) head = node.next;
            if (node == tail) tail = node.prev;
            node.next = node.prev = null;
        }

        Node<K> getLast() { return tail; }
    }
}
