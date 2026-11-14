package week10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unified Test Runner for Week 10 Assignment:
 * Linked List Data Structures (Single-pass Two Pointers, Doubly Linked List Browser History,
 * In-place Sorted Merge, In-place Palindrome Verification & Restoration, Polynomial Arithmetic).
 */
public class Week10TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================================");
        System.out.println("          STEP SEM-3 · Week 10 · Linked Lists Test Suite                       ");
        System.out.println("===============================================================================");
        System.out.println();

        testProblem1OrderCancellation();
        testProblem2BrowserHistory();
        testProblem3TicketCounterMerger();
        testProblem4PalindromeTrainCode();
        testProblem5PolynomialCalculator();

        System.out.println("===============================================================================");
        System.out.println("                 All Week 10 Problems Verified Successfully!                   ");
        System.out.println("===============================================================================");
    }

    private static void testProblem1OrderCancellation() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 1: Cancelling the k-th Last Order (Two-Pointer Single Pass)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: orders = 10 -> 20 -> 30 -> 40 -> 50, k = 2
        OrderCancellation.Node orders1 = OrderCancellation.buildList(10, 20, 30, 40, 50);
        System.out.println("Input:    " + OrderCancellation.toListString(orders1) + ", k = 2");
        OrderCancellation.Node res1 = OrderCancellation.removeKthFromEnd(orders1, 2);
        String actual1 = OrderCancellation.toListString(res1);
        String expected1 = "10 -> 20 -> 30 -> 50";
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        if (!expected1.equals(actual1)) {
            throw new AssertionError("Problem 1 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2: orders = 10 -> 20 -> 30 -> 40 -> 50, k = 5 (removes head)
        OrderCancellation.Node orders2 = OrderCancellation.buildList(10, 20, 30, 40, 50);
        System.out.println("Input:    " + OrderCancellation.toListString(orders2) + ", k = 5");
        OrderCancellation.Node res2 = OrderCancellation.removeKthFromEnd(orders2, 5);
        String actual2 = OrderCancellation.toListString(res2);
        String expected2 = "20 -> 30 -> 40 -> 50";
        System.out.println("Expected: " + expected2);
        System.out.println("Actual:   " + actual2);
        if (!expected2.equals(actual2)) {
            throw new AssertionError("Problem 1 Sample 2 failed! Got: " + actual2);
        }

        // Single element removal: k = 1
        OrderCancellation.Node orders3 = OrderCancellation.buildList(99);
        OrderCancellation.Node res3 = OrderCancellation.removeKthFromEnd(orders3, 1);
        System.out.println("Single node removal k=1: " + OrderCancellation.toListString(res3) + " (Expected: (empty))");
        if (res3 != null) {
            throw new AssertionError("Single element removal failed!");
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem2BrowserHistory() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: Campus Portal Browser History (Doubly Linked List)");
        System.out.println("---------------------------------------------------------------");

        // Action sequence from assignment:
        // home = srm.edu
        // visit a.com, visit b.com, visit c.com
        // back 1, back 1, forward 1, visit d.com, forward 2, back 2, back 7
        // Expected Output: b.com, a.com, b.com, d.com, a.com, srm.edu

        BrowserHistory browser = new BrowserHistory("srm.edu");
        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        List<String> actualOutputs = new ArrayList<>();
        actualOutputs.add(browser.back(1));     // b.com
        actualOutputs.add(browser.back(1));     // a.com
        actualOutputs.add(browser.forward(1));  // b.com
        browser.visit("d.com");
        actualOutputs.add(browser.forward(2));  // d.com
        actualOutputs.add(browser.back(2));     // a.com
        actualOutputs.add(browser.back(7));     // srm.edu

        List<String> expectedOutputs = Arrays.asList("b.com", "a.com", "b.com", "d.com", "a.com", "srm.edu");
        System.out.println("Actions: back(1), back(1), forward(1), visit(d.com), forward(2), back(2), back(7)");
        System.out.println("Expected: " + String.join(", ", expectedOutputs));
        System.out.println("Actual:   " + String.join(", ", actualOutputs));

        if (!expectedOutputs.equals(actualOutputs)) {
            throw new AssertionError("Problem 2 failed! Expected " + expectedOutputs + " but got " + actualOutputs);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem3TicketCounterMerger() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: Merging Two Ticket Counters (In-Place Sorted Merge)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: a = 3 -> 8 -> 15, b = 5 -> 8 -> 12 -> 20
        TicketCounterMerger.Node a1 = TicketCounterMerger.buildList(3, 8, 15);
        TicketCounterMerger.Node b1 = TicketCounterMerger.buildList(5, 8, 12, 20);
        System.out.println("List A: " + TicketCounterMerger.toListString(a1));
        System.out.println("List B: " + TicketCounterMerger.toListString(b1));

        TicketCounterMerger.Node merged1 = TicketCounterMerger.mergeSorted(a1, b1);
        String actual1 = TicketCounterMerger.toListString(merged1);
        String expected1 = "3 -> 5 -> 8 -> 8 -> 12 -> 15 -> 20";
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        if (!expected1.equals(actual1)) {
            throw new AssertionError("Problem 3 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2: a = (empty), b = 4
        TicketCounterMerger.Node a2 = null;
        TicketCounterMerger.Node b2 = TicketCounterMerger.buildList(4);
        TicketCounterMerger.Node merged2 = TicketCounterMerger.mergeSorted(a2, b2);
        String actual2 = TicketCounterMerger.toListString(merged2);
        String expected2 = "4";
        System.out.println("Empty A with B=[4] -> Actual: " + actual2 + " (Expected: " + expected2 + ")");
        if (!expected2.equals(actual2)) {
            throw new AssertionError("Problem 3 Sample 2 failed! Got: " + actual2);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem4PalindromeTrainCode() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: The Palindrome Train Code (In-Place Check & Restore)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: code = 1 -> 2 -> 3 -> 2 -> 1
        PalindromeTrainCode.Node code1 = PalindromeTrainCode.buildList(1, 2, 3, 2, 1);
        System.out.println("Code 1: " + PalindromeTrainCode.toListString(code1));
        boolean pal1 = PalindromeTrainCode.isPalindrome(code1);
        String restored1 = PalindromeTrainCode.toListString(code1);
        System.out.println("Expected: true | Actual: " + pal1);
        System.out.println("Restored list: " + restored1);
        if (!pal1 || !restored1.equals("1 -> 2 -> 3 -> 2 -> 1")) {
            throw new AssertionError("Problem 4 Sample 1 failed!");
        }

        // Sample 2: code = 1 -> 2 -> 3 -> 2
        PalindromeTrainCode.Node code2 = PalindromeTrainCode.buildList(1, 2, 3, 2);
        System.out.println("Code 2: " + PalindromeTrainCode.toListString(code2));
        boolean pal2 = PalindromeTrainCode.isPalindrome(code2);
        String restored2 = PalindromeTrainCode.toListString(code2);
        System.out.println("Expected: false | Actual: " + pal2);
        System.out.println("Restored list: " + restored2);
        if (pal2 || !restored2.equals("1 -> 2 -> 3 -> 2")) {
            throw new AssertionError("Problem 4 Sample 2 failed!");
        }

        // Sample 3: code = 1 -> 2 -> 2 -> 1
        PalindromeTrainCode.Node code3 = PalindromeTrainCode.buildList(1, 2, 2, 1);
        System.out.println("Code 3: " + PalindromeTrainCode.toListString(code3));
        boolean pal3 = PalindromeTrainCode.isPalindrome(code3);
        String restored3 = PalindromeTrainCode.toListString(code3);
        System.out.println("Expected: true | Actual: " + pal3);
        System.out.println("Restored list: " + restored3);
        if (!pal3 || !restored3.equals("1 -> 2 -> 2 -> 1")) {
            throw new AssertionError("Problem 4 Sample 3 failed!");
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem5PolynomialCalculator() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Polynomial Calculator (Two-Way Merge & Standard Form)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1:
        // p = 5x^3 + 4x^2 + 2
        // q = 5x^2 - 3x + 1
        // Expected Output: 5x^3 + 9x^2 - 3x + 3
        PolynomialCalculator.Node p1 = PolynomialCalculator.buildPolynomial(5, 3, 4, 2, 2, 0);
        PolynomialCalculator.Node q1 = PolynomialCalculator.buildPolynomial(5, 2, -3, 1, 1, 0);
        System.out.println("p1: " + PolynomialCalculator.toStandardForm(p1));
        System.out.println("q1: " + PolynomialCalculator.toStandardForm(q1));

        PolynomialCalculator.Node sum1 = PolynomialCalculator.addPolynomials(p1, q1);
        String actual1 = PolynomialCalculator.toStandardForm(sum1);
        String expected1 = "5x^3 + 9x^2 - 3x + 3";
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        if (!expected1.equals(actual1)) {
            throw new AssertionError("Problem 5 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2:
        // p = 3x^2 + 1
        // q = -3x^2 + 4x
        // Expected Output: 4x + 1
        PolynomialCalculator.Node p2 = PolynomialCalculator.buildPolynomial(3, 2, 1, 0);
        PolynomialCalculator.Node q2 = PolynomialCalculator.buildPolynomial(-3, 2, 4, 1);
        System.out.println("p2: " + PolynomialCalculator.toStandardForm(p2));
        System.out.println("q2: " + PolynomialCalculator.toStandardForm(q2));

        PolynomialCalculator.Node sum2 = PolynomialCalculator.addPolynomials(p2, q2);
        String actual2 = PolynomialCalculator.toStandardForm(sum2);
        String expected2 = "4x + 1";
        System.out.println("Expected: " + expected2);
        System.out.println("Actual:   " + actual2);
        if (!expected2.equals(actual2)) {
            throw new AssertionError("Problem 5 Sample 2 failed! Got: " + actual2);
        }

        // Edge case: complete cancellation
        PolynomialCalculator.Node p3 = PolynomialCalculator.buildPolynomial(4, 2, 5, 0);
        PolynomialCalculator.Node q3 = PolynomialCalculator.buildPolynomial(-4, 2, -5, 0);
        PolynomialCalculator.Node sum3 = PolynomialCalculator.addPolynomials(p3, q3);
        String actual3 = PolynomialCalculator.toStandardForm(sum3);
        System.out.println("Complete cancellation -> Actual: " + actual3 + " (Expected: 0)");
        if (!"0".equals(actual3)) {
            throw new AssertionError("Complete cancellation test failed!");
        }

        System.out.println("Status: PASS\n");
    }
}
