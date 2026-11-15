package week12;

/**
 * Problem 5: Token Mirror Check
 *
 * <p>Checks whether a chain of token numbers reads the same from both ends (palindrome)
 * using strictly O(1) extra space. The list's links are temporarily modified during the check,
 * but are completely restored to their exact original structure before returning.
 *
 * <p><b>Algorithmic Strategy:</b>
 * <ol>
 *   <li><b>Locate Middle:</b> Advance {@code slow} by 1 step and {@code fast} by 2 steps until
 *       {@code fast.next == null || fast.next.next == null}. {@code slow} marks the end of the first half.</li>
 *   <li><b>Invert Second Half:</b> In-place reverse the linked list starting at {@code slow.next}.</li>
 *   <li><b>Compare:</b> Simultaneously iterate through the first half (from {@code head}) and
 *       the reversed second half, verifying matching {@code data} values.</li>
 *   <li><b>Restore:</b> In-place reverse the second half again and re-attach to {@code slow.next},
 *       leaving the original list completely unaltered.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(N), where N is the number of tokens. Finding the middle takes N/2,
 *       reversing takes N/2, comparing takes <= N/2, and restoring takes N/2, summing to ~2N operations.</li>
 *   <li><b>Space Complexity:</b> O(1) auxiliary space, utilizing only a few pointer variables.</li>
 * </ul>
 *
 * <p><b>Why Array or Stack Copying Does Not Meet the Requirement:</b>
 * Copying token values into an auxiliary array, list, or call stack consumes O(N) additional memory,
 * which violates the strict O(1) constraint essential for memory-restricted embedded kiosk hardware.
 */
public class TokenMirrorCheck {

    /**
     * Singly linked list node representing a token number.
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
     * Checks whether the token chain is a palindrome in O(N) time and O(1) space,
     * leaving the list unchanged.
     *
     * @param head head of the tokens list
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1. Locate the midpoint
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse second half
        Node secondHalf = reverse(slow.next);

        // 3. Compare first half and reversed second half
        Node p1 = head;
        Node p2 = secondHalf;
        boolean palindrome = true;

        while (p2 != null) {
            if (p1.data != p2.data) {
                palindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. Restore original list structure
        slow.next = reverse(secondHalf);

        return palindrome;
    }

    private static Node reverse(Node head) {
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
     * Helper to format a linked list as "4 -> 7 -> 9 -> 7 -> 4 -> null".
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
