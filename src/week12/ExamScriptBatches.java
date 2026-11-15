package week12;

/**
 * Problem 2: Exam Script Batches
 *
 * <p>Reverses answer script chains in groups of {@code k} nodes in-place by relinking existing nodes.
 * A final trailing group with fewer than {@code k} nodes remains in its original order.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses an iterative approach with a sentinel {@code dummy} node:
 * <ol>
 *   <li>Station {@code groupPrev} at {@code dummy}.</li>
 *   <li>In each step, verify whether {@code k} nodes exist ahead:
 *       advance a pointer {@code kth} by {@code k} steps. If {@code null} is reached before
 *       completing {@code k} steps, the final group has fewer than {@code k} nodes and is left as-is.</li>
 *   <li>Store {@code groupNext = kth.next}.</li>
 *   <li>Reverse the sublist of {@code k} nodes between {@code groupPrev.next} and {@code kth}
 *       such that the first node of the group points to {@code groupNext}.</li>
 *   <li>Connect {@code groupPrev.next = kth} and update {@code groupPrev} to the original group head
 *       (which is now the tail of the reversed group).</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(N), where N is the total number of nodes in the list.
 *       Each node is scanned once to count and once to reverse its link.</li>
 *   <li><b>Space Complexity:</b> O(1) auxiliary space, operating purely iteratively.
 *       Unlike a recursive approach which requires O(N/k) call-stack memory, this avoids
 *       all stack allocations.</li>
 * </ul>
 */
public class ExamScriptBatches {

    /**
     * Custom singly linked list node representing an answer script.
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
     * Reverses the linked list in groups of k nodes. Trailing nodes (< k) stay in original order.
     *
     * @param head head of the scripts linked list
     * @param k batch size
     * @return new head of the rearranged linked list
     */
    public static Node reverseInGroups(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node dummy = new Node(0, head);
        Node groupPrev = dummy;

        while (true) {
            // Check if k nodes exist in the current group
            Node kth = groupPrev;
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) {
                    return dummy.next; // fewer than k nodes left; keep original order
                }
            }

            Node groupNext = kth.next;

            // Reverse current group of k nodes
            Node prev = groupNext;
            Node curr = groupPrev.next;
            while (curr != groupNext) {
                Node tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // Relink the previous group tail to the new group head (kth)
            Node oldGroupStart = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = oldGroupStart;
        }
    }

    /**
     * Helper to construct a linked list from an array of integers.
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
     * Helper to format a linked list ending with "-> null".
     */
    public static String toListString(Node head) {
        if (head == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
