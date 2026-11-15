package week12;

/**
 * Problem 4: Library Returns by Due Day
 *
 * <p>Sorts a chain of returned library books by due day in ascending order using Merge Sort
 * on the linked list. The sorting is strictly stable: books with the same due day retain their
 * original drop order. Relinks pointers in-place without array copying.
 *
 * <p><b>Algorithmic Strategy:</b>
 * <ol>
 *   <li><b>Base Case:</b> If the list is empty or has a single node, it is already sorted.</li>
 *   <li><b>Divide:</b> Locate the middle of the list using slow and fast pointers
 *       ({@code slow = head}, {@code fast = head.next}) and cut the link after {@code slow}.</li>
 *   <li><b>Conquer:</b> Recursively sort the left half and right half.</li>
 *   <li><b>Merge:</b> In-place merge the two sorted halves using a dummy node.
 *       Using {@code left.day <= right.day} ensures stability by picking from the left half on ties.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(N log N), where N is the number of books. The list is divided into
 *       halves in O(log N) levels, and merging at each level takes linear O(N) time.</li>
 *   <li><b>Space Complexity:</b> O(log N) auxiliary memory for the recursion call stack.
 *       Zero auxiliary node copies or arrays are created.</li>
 * </ul>
 *
 * <p><b>Why Merge Sort Suits Linked Lists Better than Quick Sort:</b>
 * Merge sort is ideally matched to linked lists because splitting and merging can be accomplished
 * in O(1) auxiliary space purely by relinking existing pointers, without needing contiguous memory
 * or random access. In contrast, Quick Sort relies heavily on random-access indexing to select
 * effective pivots (like median-of-three) and exhibits poor cache locality on linked structures.
 * Furthermore, linked list Merge Sort guarantees worst-case O(N log N) performance and is naturally stable.
 */
public class LibraryReturnsByDueDay {

    /**
     * Singly linked list node representing a returned library book.
     */
    public static class Node {
        public String title;
        public int day;
        public Node next;

        public Node(String title, int day) {
            this.title = title;
            this.day = day;
            this.next = null;
        }

        public Node(String title, int day, Node next) {
            this.title = title;
            this.day = day;
            this.next = next;
        }

        @Override
        public String toString() {
            return title + ":" + day;
        }
    }

    /**
     * Sorts the linked list by due day in ascending order using stable Merge Sort.
     *
     * @param head head of the returned books list
     * @return head of the sorted list
     */
    public static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Split the list into two halves
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node mid = slow.next;
        slow.next = null; // Sever link

        // 2. Recursively sort each half
        Node left = sortByDueDay(head);
        Node right = sortByDueDay(mid);

        // 3. In-place stable merge
        return merge(left, right);
    }

    private static Node merge(Node left, Node right) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (left != null && right != null) {
            // '<=' preserves drop order on equal due dates (stability)
            if (left.day <= right.day) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        tail.next = (left != null) ? left : right;
        return dummy.next;
    }

    /**
     * Helper to construct a linked list from an array of "Title:Day" strings.
     */
    public static Node buildList(String... entries) {
        if (entries == null || entries.length == 0) {
            return null;
        }

        Node dummy = new Node("", 0);
        Node tail = dummy;
        for (String entry : entries) {
            if (entry == null || !entry.contains(":")) {
                continue;
            }
            String[] parts = entry.split(":");
            tail.next = new Node(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            tail = tail.next;
        }
        return dummy.next;
    }

    /**
     * Formats the list as "Title:Day -> ... -> null" or "null".
     */
    public static String toListString(Node head) {
        if (head == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.title).append(":").append(curr.day).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
