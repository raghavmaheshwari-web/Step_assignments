package week12;

/**
 * Problem 1: Delivery Route Loop Detector
 *
 * <p>Detects cycles in a delivery route represented as a singly linked list. If a cycle
 * exists due to a data-entry bug, reports the stop where the loop begins and the count
 * of stops in the loop, using strictly O(1) auxiliary space without modifying nodes or
 * using hash sets or visited flags.
 *
 * <p><b>Algorithmic Strategy (Floyd's Tortoise and Hare Cycle-Finding Algorithm):</b>
 * <ol>
 *   <li><b>Cycle Detection:</b> Advance {@code slow} by 1 step and {@code fast} by 2 steps.
 *       If {@code fast} or {@code fast.next} reaches {@code null}, there is no cycle.
 *       If {@code slow == fast}, a cycle is confirmed.</li>
 *   <li><b>Loop Length Calculation:</b> Keep {@code slow} fixed and advance a pointer around
 *       the loop until it returns to {@code slow}, counting the number of steps {@code k}.</li>
 *   <li><b>Loop Start Identification:</b> Move one pointer to the list {@code head} while keeping
 *       the other at the meeting point. Advance both pointers 1 step at a time.
 *       The node where they intersect is the entry point of the loop.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(N), where N is the number of stops. The fast pointer traverses
 *       at most 2N steps before meeting, and finding the length and start node takes <= N steps.</li>
 *   <li><b>Space Complexity:</b> O(1) auxiliary space, using only local pointer variables.</li>
 * </ul>
 *
 * <p><b>Why Slow and Fast Pointers Must Meet Inside the Loop:</b>
 * Once both pointers enter the cycle, the relative distance between them decreases by exactly 1 node
 * in each iteration (fast gains 1 step on slow per turn). In a cycle of length L, the gap modulo L
 * decreases by 1 on every step until it reaches 0, guaranteeing a meeting in at most L steps.
 */
public class DeliveryRouteLoopDetector {

    /**
     * Custom singly linked list node representing a delivery stop.
     */
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    /**
     * Detects if the route contains a cycle and returns a summary description.
     *
     * @param head head of the delivery route linked list
     * @return "Loop: no" or "Loop: yes, starts at stop s, length k"
     */
    public static String detectLoop(Node head) {
        if (head == null || head.next == null) {
            return "Loop: no";
        }

        Node slow = head;
        Node fast = head;
        boolean hasLoop = false;

        // 1. Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }

        if (!hasLoop) {
            return "Loop: no";
        }

        // 2. Count loop length
        int length = 1;
        Node curr = slow.next;
        while (curr != slow) {
            length++;
            curr = curr.next;
        }

        // 3. Find start of loop
        Node p1 = head;
        Node p2 = slow;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return "Loop: yes, starts at stop " + p1.data + ", length " + length;
    }

    /**
     * Helper to construct a linked list with an optional cycle.
     *
     * @param stops array of stop IDs
     * @param pos 0-based index the last node connects back to (-1 for no cycle)
     * @return head of the constructed linked list
     */
    public static Node buildRoute(int[] stops, int pos) {
        if (stops == null || stops.length == 0) {
            return null;
        }

        Node head = new Node(stops[0]);
        Node current = head;
        Node cycleEntry = (pos == 0) ? head : null;

        for (int i = 1; i < stops.length; i++) {
            current.next = new Node(stops[i]);
            current = current.next;
            if (i == pos) {
                cycleEntry = current;
            }
        }

        if (pos >= 0 && cycleEntry != null) {
            current.next = cycleEntry;
        }

        return head;
    }
}
