package week10;

/**
 * Problem 4: The Palindrome Train Code
 *
 * <p>Each train code is represented as a singly linked list of digits. This class checks whether
 * the train code reads identically forwards and backwards (a palindrome) in strictly O(N) time
 * and O(1) auxiliary space, fully restoring the original list structure before returning.
 *
 * <p><b>Algorithmic Strategy:</b>
 * <ol>
 *   <li><b>Find the middle:</b> Advance a fast pointer by 2 steps and a slow pointer by 1 step.
 *       When the fast pointer reaches the end, the slow pointer rests at the end of the first half.</li>
 *   <li><b>Reverse the second half:</b> Invert the {@code next} pointers of the second half in-place.</li>
 *   <li><b>Compare halves:</b> Step through the first half and the reversed second half simultaneously,
 *       comparing node data values.</li>
 *   <li><b>Restore original list:</b> Reverse the second half once more and reconnect it to the
 *       middle node so the linked list is unchanged.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(N) total:
 *       <ul>
 *         <li>Midpoint search: N/2 steps</li>
 *         <li>Second-half reversal: N/2 steps</li>
 *         <li>Data comparison: at most N/2 steps</li>
 *         <li>List restoration reversal: N/2 steps</li>
 *         <li>Total time: ~2N pointer movements = O(N).</li>
 *       </ul>
 *   </li>
 *   <li><b>Space Complexity:</b> O(1) auxiliary space, using only local pointer variables.</li>
 * </ul>
 *
 * <p><b>Comparison with Array/Stack Copying:</b>
 * Copying values into an array, stack, or relying on recursive call-stack unwinding requires O(N)
 * extra memory. In-place mid-point reversal guarantees strict O(1) space compliance while ensuring
 * that the list is left completely intact.
 */
public class PalindromeTrainCode {

    /**
     * Singly linked list node representing a train code digit.
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
     * Determines whether the linked list represents a palindrome in O(N) time and O(1) space,
     * leaving the list unchanged.
     *
     * @param head head of the train code linked list
     * @return true if the code is a palindrome, false otherwise
     */
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1. Locate middle of the list
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half
        Node secondHalfHead = reverseList(slow.next);

        // 3. Compare first half and reversed second half
        Node p1 = head;
        Node p2 = secondHalfHead;
        boolean palindrome = true;

        while (p2 != null) {
            if (p1.data != p2.data) {
                palindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. Restore the list by reversing the second half back
        slow.next = reverseList(secondHalfHead);

        return palindrome;
    }

    /**
     * Helper to reverse a singly linked list in-place.
     */
    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
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
     * Helper to format a linked list as "1 -> 2 -> 3 -> 2 -> 1".
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
