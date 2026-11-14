package week10;

/**
 * Problem 3: Merging Two Ticket Counters
 *
 * <p>Two railway counters produce singly linked lists of ticket numbers in ascending order.
 * This class merges both lists into a single sorted list by relinking existing nodes in-place.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses a single sentinel {@code dummy} node and a {@code tail} pointer:
 * <ol>
 *   <li>Compare current nodes {@code a} and {@code b}.</li>
 *   <li>Attach the smaller (or equal) node to {@code tail.next} and advance that list's pointer.</li>
 *   <li>Advance {@code tail = tail.next}.</li>
 *   <li>When one list is exhausted, directly link the remainder of the other list:
 *       {@code tail.next = (a != null) ? a : b}.</li>
 *   <li>Return {@code dummy.next}.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(m + n), where m and n are the lengths of lists {@code a} and {@code b}.
 *       Each node from both lists is examined and relinked at most once.</li>
 *   <li><b>Space Complexity:</b> O(1) auxiliary space, as only a single dummy node is created and
 *       existing node pointers are rearranged in-place.</li>
 * </ul>
 *
 * <p><b>Comparison with Array Sorting:</b>
 * Copying both lists into an array and sorting requires O((m + n) log(m + n)) time and O(m + n)
 * auxiliary memory to allocate the array, followed by rebuilding or mutating the nodes.
 * In-place pointer merging achieves strictly linear O(m + n) time and O(1) memory without any
 * heap allocations or data copying.
 */
public class TicketCounterMerger {

    /**
     * Singly linked list node representing a ticket number.
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
     * Merges two sorted singly linked lists by relinking existing nodes.
     *
     * @param a head of first sorted list
     * @param b head of second sorted list
     * @return head of the combined sorted list
     */
    public static Node mergeSorted(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node dummy = new Node(0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        // Attach the remaining nodes of whichever list is not yet exhausted
        tail.next = (a != null) ? a : b;

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
     * Helper to format a linked list as "3 -> 5 -> 8 -> 8 -> 12".
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
