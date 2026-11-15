package week12;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 3: Recent Pages Cache (Least Recently Used - LRU Cache)
 *
 * <p>Implements an LRU cache of web pages for a learning portal using a custom doubly linked
 * list paired with a {@link HashMap} for strict O(1) page access and eviction, without
 * using {@link java.util.LinkedHashMap}.
 *
 * <p><b>Algorithmic Strategy:</b>
 * <ul>
 *   <li><b>Doubly Linked List:</b> Uses sentinel {@code head} and {@code tail} dummy nodes.
 *       The most recently used (MRU) node is always placed directly after {@code head},
 *       while the least recently used (LRU) node is always located directly before {@code tail}.</li>
 *   <li><b>Hash Map:</b> Maps {@code key} to its corresponding doubly linked list {@code Node}.</li>
 *   <li><b>get(key):</b> Look up node in map. If present, detach it from its current position
 *       and move it to immediately after {@code head} (marking it MRU), then return its page.
 *       If absent, return {@code "-1"}.</li>
 *   <li><b>put(key, page):</b>
 *       <ul>
 *         <li>If key already exists: update its page content and move to head.</li>
 *         <li>If key is new and cache is at capacity: remove the LRU node ({@code tail.prev})
 *             from both the list and the map, then insert the new node at head.</li>
 *         <li>If key is new and cache has capacity: insert the new node at head and record in map.</li>
 *       </ul>
 *   </li>
 * </ul>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(1) for both {@code get} and {@code put}. HashMap provides O(1)
 *       average lookups, and the doubly linked list provides O(1) pointer updates.</li>
 *   <li><b>Space Complexity:</b> O(capacity) auxiliary memory to store at most {@code capacity} nodes
 *       in the hash map and doubly linked list.</li>
 * </ul>
 *
 * <p><b>Why a Singly Linked List Cannot Remove an Arbitrary Node in O(1):</b>
 * Removing a node requires updating the {@code next} pointer of the node that precedes it. In a
 * singly linked list, nodes only store forward pointers, so locating the predecessor of an arbitrary
 * node requires a linear scan from the head taking O(N) time. In a doubly linked list, each node
 * possesses a direct {@code prev} pointer, allowing instantaneous O(1) deletion.
 */
public class RecentPagesCache {

    /**
     * Doubly linked list node holding key, page content, and bidirectional links.
     */
    public static class Node {
        public int key;
        public String page;
        public Node prev;
        public Node next;

        public Node(int key, String page) {
            this.key = key;
            this.page = page;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return key + "=" + page;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    /**
     * Initializes the LRU cache with the specified capacity.
     *
     * @param capacity maximum number of pages the cache can hold
     */
    public RecentPagesCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Sentinel dummy nodes
        this.head = new Node(0, "");
        this.tail = new Node(0, "");
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Retrieves the page for the given key, marking it as most recently used.
     *
     * @param key page key
     * @return page content, or "-1" if not found
     */
    public String get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return "-1";
        }
        moveToHead(node);
        return node.page;
    }

    /**
     * Puts a page into the cache. If key exists, updates page. If full, evicts least recently used.
     *
     * @param key page key
     * @param page page content
     */
    public void put(int key, String page) {
        Node existingNode = map.get(key);
        if (existingNode != null) {
            existingNode.page = page;
            moveToHead(existingNode);
            return;
        }

        if (map.size() >= capacity) {
            Node lru = removeTail();
            map.remove(lru.key);
        }

        Node newNode = new Node(key, page);
        addToHead(newNode);
        map.put(key, newNode);
    }

    /**
     * Wrapper for get operation that returns the formatted output string: "get(key) -> page".
     */
    public String logGet(int key) {
        String val = get(key);
        return "get(" + key + ") -> " + val;
    }

    /**
     * Wrapper for put operation that returns the formatted output string:
     * "put(key,page)" or "put(key,page) updated" or "put(key,page) evicted <evictedKey>".
     */
    public String logPut(int key, String page) {
        Node existingNode = map.get(key);
        if (existingNode != null) {
            existingNode.page = page;
            moveToHead(existingNode);
            return "put(" + key + "," + page + ") updated";
        }

        String evictedMsg = "";
        if (map.size() >= capacity) {
            Node lru = removeTail();
            map.remove(lru.key);
            evictedMsg = " evicted " + lru.key;
        }

        Node newNode = new Node(key, page);
        addToHead(newNode);
        map.put(key, newNode);

        return "put(" + key + "," + page + ")" + evictedMsg;
    }

    /**
     * Formats the current cache state from most recent to least recent.
     */
    public String getCacheState() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cache (most recent first): ");
        Node curr = head.next;
        boolean first = true;
        while (curr != tail) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(curr.key).append("=").append(curr.page);
            first = false;
            curr = curr.next;
        }
        return sb.toString();
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }
}
