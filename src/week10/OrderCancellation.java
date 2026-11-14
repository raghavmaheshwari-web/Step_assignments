package week10;

import java.util.Objects;

/**
 * Problem 1: Cancelling the k-th Last Order
 *
 * <p>A restaurant keeps today's orders in a custom singly linked list, newest at the end.
 * When a customer cancels the order that is k-th from the end, this class removes that node
 * in a single pass without using any Java Collections classes.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses the Two-Pointer (Lead and Trailing) technique with a sentinel dummy node:
 * <ol>
 *   <li>Create a {@code dummy} node pointing to {@code head}.</li>
 *   <li>Advance a {@code lead} (fast) pointer (k + 1) steps ahead from {@code dummy}.</li>
 *   <li>Start a {@code trailing} (slow) pointer at {@code dummy}.</li>
 *   <li>Advance both pointers one step at a time until {@code lead} falls off the end ({@code null}).</li>
 *   <li>Now {@code trailing} is stationed immediately before the target node to delete:
 *       {@code trailing.next = trailing.next.next}.</li>
 *   <li>Return {@code dummy.next}.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(N) single pass. The lead pointer visits each of the N nodes once,
 *       and the trailing pointer covers at most N steps.</li>
 *   <li><b>Space Complexity:</b> O(1) auxiliary space (only dummy and two pointer variables).</li>
 * </ul>
 *
 * <p><b>Handling Head Removal Without Special Cases:</b>
 * When removing the head (e.g. k = length of list), the lead pointer advances to {@code null} during
 * the initial (k + 1) step phase. The trailing pointer remains at {@code dummy}, so
 * {@code trailing.next = trailing.next.next} directly bypasses the original head by setting
 * {@code dummy.next = head.next}. No separate {@code if (headToDelete)} check is needed.
 */
public class OrderCancellation {

    /**
     * Singly linked list node representing an order ticket.
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
     * Removes the k-th node from the end of the singly linked list in a single pass.
     *
     * @param head head of the singly linked list
     * @param k 1-based index from the end
     * @return new head of the list after removal
     */
    public static Node removeKthFromEnd(Node head, int k) {
        if (head == null) {
            return null;
        }
        if (k <= 0) {
            throw new IllegalArgumentException("k must be a positive integer >= 1.");
        }

        Node dummy = new Node(0, head);
        Node lead = dummy;
        Node trailing = dummy;

        // Move lead pointer k + 1 steps ahead
        for (int i = 0; i < k + 1; i++) {
            if (lead == null) {
                throw new IllegalArgumentException(String.format(
                        "k=%d exceeds list length.", k));
            }
            lead = lead.next;
        }

        // Advance both until lead reaches the end
        while (lead != null) {
            lead = lead.next;
            trailing = trailing.next;
        }

        // Remove the target node
        if (trailing.next != null) {
            trailing.next = trailing.next.next;
        }

        return dummy.next;
    }

    /**
     * Helper to build a linked list from an array of integers.
     */
    public static Node buildList(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Helper to format a linked list as "10 -> 20 -> 30".
     */
    public static String toListString(Node head) {
        if (head == null) {
            return "(empty)";
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }
}
