package week10;

import java.util.Objects;

/**
 * Problem 2: Campus Portal Browser History
 *
 * <p>Simulates a web browser navigation history backed by a custom doubly linked list.
 * Supports visiting a new URL (which clears all forward history), navigating backward
 * up to {@code x} steps, and navigating forward up to {@code x} steps.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Maintains a reference to the {@code current} node in a doubly linked list.
 * <ul>
 *   <li><b>visit(url):</b> Creates a new node, links {@code current.next = newNode} and
 *       {@code newNode.prev = current}, and updates {@code current = newNode}.
 *       Any prior forward chain starting at {@code current.next} is severed in O(1) time.</li>
 *   <li><b>back(steps):</b> Traverses backwards along {@code prev} pointers up to {@code steps}
 *       times or until the first node (head) is reached.</li>
 *   <li><b>forward(steps):</b> Traverses forwards along {@code next} pointers up to {@code steps}
 *       times or until the latest node (tail) is reached.</li>
 * </ul>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>visit:</b> O(1) time. Discarding the forward history simply requires detaching the old
 *       {@code current.next} link. In Java, unreferenced forward nodes become unreachable and are
 *       reclaimed automatically by the Garbage Collector.</li>
 *   <li><b>back:</b> O(min(steps, distance to head)) time.</li>
 *   <li><b>forward:</b> O(min(steps, distance to tail)) time.</li>
 *   <li><b>Space Complexity:</b> O(V), where V is the number of active visited pages maintained in
 *       the current history chain.</li>
 * </ul>
 */
public class BrowserHistory {

    /**
     * Doubly linked list node storing page URL and bi-directional links.
     */
    public static class Node {
        public String url;
        public Node prev;
        public Node next;

        public Node(String url) {
            this.url = url;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return url;
        }
    }

    private Node current;

    /**
     * Initializes the browser history on a home page.
     *
     * @param homepage starting portal URL
     */
    public BrowserHistory(String homepage) {
        if (homepage == null || homepage.trim().isEmpty()) {
            throw new IllegalArgumentException("Homepage URL cannot be null or empty.");
        }
        this.current = new Node(homepage.trim());
    }

    /**
     * Visits a new URL from the current page. Clears all forward history in O(1) time.
     *
     * @param url new web page URL
     */
    public void visit(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty.");
        }
        Node newNode = new Node(url.trim());
        // Link new node to current
        current.next = newNode;
        newNode.prev = current;
        // Move current to new node; previous forward chain is now unreferenced
        current = newNode;
    }

    /**
     * Moves backward in history up to {@code steps} pages.
     * Stops at the first page available if steps exceeds back history.
     *
     * @param steps number of pages to move backward
     * @return URL of the current page after moving backward
     */
    public String back(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException("Steps cannot be negative: " + steps);
        }
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    /**
     * Moves forward in history up to {@code steps} pages.
     * Stops at the last page available if steps exceeds forward history.
     *
     * @param steps number of pages to move forward
     * @return URL of the current page after moving forward
     */
    public String forward(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException("Steps cannot be negative: " + steps);
        }
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    /**
     * Returns the current page URL.
     */
    public String getCurrentUrl() {
        return current.url;
    }
}
