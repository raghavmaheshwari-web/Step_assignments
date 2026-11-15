package week11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unified Test Runner for Week 11 Assignment:
 * Stacks, Queues, Circular Buffer & Multi-Source Breadth-First Search.
 */
public class Week11TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================================");
        System.out.println("      STEP SEM-3 · Week 11 · Stacks, Queues & BFS Test Suite                   ");
        System.out.println("===============================================================================");
        System.out.println();

        testProblem1CanteenPriceStreak();
        testProblem2HackathonBannerPrinter();
        testProblem3QuizScoringFormula();
        testProblem4BusBayRing();
        testProblem5FireDrillExits();

        System.out.println("===============================================================================");
        System.out.println("                 All Week 11 Problems Verified Successfully!                   ");
        System.out.println("===============================================================================");
    }

    private static void testProblem1CanteenPriceStreak() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 1: The Canteen Price Streak (Monotonic Stack)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: price = [100, 80, 60, 70, 60, 75, 85]
        int[] price1 = {100, 80, 60, 70, 60, 75, 85};
        int[] actual1 = CanteenPriceStreak.priceStreak(price1);
        int[] expected1 = {1, 1, 1, 2, 1, 4, 6};
        System.out.println("Prices 1: " + Arrays.toString(price1));
        System.out.println("Expected: " + Arrays.toString(expected1));
        System.out.println("Actual:   " + Arrays.toString(actual1));
        if (!Arrays.equals(expected1, actual1)) {
            throw new AssertionError("Problem 1 Sample 1 failed! Got: " + Arrays.toString(actual1));
        }

        // Sample 2: price = [10, 20, 30]
        int[] price2 = {10, 20, 30};
        int[] actual2 = CanteenPriceStreak.priceStreak(price2);
        int[] expected2 = {1, 2, 3};
        System.out.println("Prices 2: " + Arrays.toString(price2));
        System.out.println("Expected: " + Arrays.toString(expected2));
        System.out.println("Actual:   " + Arrays.toString(actual2));
        if (!Arrays.equals(expected2, actual2)) {
            throw new AssertionError("Problem 1 Sample 2 failed! Got: " + Arrays.toString(actual2));
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem2HackathonBannerPrinter() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: The Hackathon Banner Printer (Two-Stack Decoding)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: code = "3[ab]2[c]"
        String code1 = "3[ab]2[c]";
        String actual1 = HackathonBannerPrinter.expandBanner(code1);
        String expected1 = "abababcc";
        System.out.println("Code 1:   " + code1);
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        if (!expected1.equals(actual1)) {
            throw new AssertionError("Problem 2 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2: code = "2[x3[y]]z"
        String code2 = "2[x3[y]]z";
        String actual2 = HackathonBannerPrinter.expandBanner(code2);
        String expected2 = "xyyyxyyyz";
        System.out.println("Code 2:   " + code2);
        System.out.println("Expected: " + expected2);
        System.out.println("Actual:   " + actual2);
        if (!expected2.equals(actual2)) {
            throw new AssertionError("Problem 2 Sample 2 failed! Got: " + actual2);
        }

        // Multi-digit repeat count: 10[a]
        String code3 = "10[a]";
        String actual3 = HackathonBannerPrinter.expandBanner(code3);
        String expected3 = "aaaaaaaaaa";
        System.out.println("Code 3:   " + code3);
        System.out.println("Expected: " + expected3);
        System.out.println("Actual:   " + actual3);
        if (!expected3.equals(actual3)) {
            throw new AssertionError("Problem 2 Sample 3 failed! Got: " + actual3);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem3QuizScoringFormula() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: The Quiz Scoring Formula (Two-Stack Direct Evaluation)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: expr = "2 * (3 + 4) - 10 / 5"
        String expr1 = "2 * (3 + 4) - 10 / 5";
        int actual1 = QuizScoringFormula.evaluate(expr1);
        int expected1 = 12;
        System.out.println("Expression 1: " + expr1);
        System.out.println("Expected:     " + expected1);
        System.out.println("Actual:       " + actual1);
        if (expected1 != actual1) {
            throw new AssertionError("Problem 3 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2: expr = "(8 - 2) * (1 + 2) / 4"
        String expr2 = "(8 - 2) * (1 + 2) / 4";
        int actual2 = QuizScoringFormula.evaluate(expr2);
        int expected2 = 4;
        System.out.println("Expression 2: " + expr2);
        System.out.println("Expected:     " + expected2 + " (18 / 4 integer division)");
        System.out.println("Actual:       " + actual2);
        if (expected2 != actual2) {
            throw new AssertionError("Problem 3 Sample 2 failed! Got: " + actual2);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem4BusBayRing() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: The Bus Bay Ring (Circular Deque Ring Buffer)");
        System.out.println("---------------------------------------------------------------");

        // Sequence from assignment:
        // BusBay b = new BusBay(3);
        // insertLast(101), insertLast(102), insertFront(100),
        // insertFront(99), getRear(), isFull(), deleteLast(),
        // insertFront(98), getFront()
        // Expected Output: true, true, true, false, 102, true, true, true, 98

        BusBay b = new BusBay(3);
        List<Object> actualOutputs = new ArrayList<>();

        actualOutputs.add(b.insertLast(101));   // true
        actualOutputs.add(b.insertLast(102));   // true
        actualOutputs.add(b.insertFront(100));  // true
        actualOutputs.add(b.insertFront(99));   // false (full)
        actualOutputs.add(b.getRear());         // 102
        actualOutputs.add(b.isFull());          // true
        actualOutputs.add(b.deleteLast());      // true
        actualOutputs.add(b.insertFront(98));   // true
        actualOutputs.add(b.getFront());        // 98

        List<Object> expectedOutputs = Arrays.asList(true, true, true, false, 102, true, true, true, 98);
        System.out.println("Expected: " + expectedOutputs);
        System.out.println("Actual:   " + actualOutputs);

        if (!expectedOutputs.equals(actualOutputs)) {
            throw new AssertionError("Problem 4 failed! Expected " + expectedOutputs + " but got " + actualOutputs);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem5FireDrillExits() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: The Fire Drill Exits (Multi-Source BFS)");
        System.out.println("---------------------------------------------------------------");

        char[][] plan = {
                {'.', '#', '.', 'E'},
                {'.', '.', '.', '#'},
                {'E', '#', '.', '.'}
        };

        int[][] actual = FireDrillExits.exitDistance(plan);
        int[][] expected = {
                {2, -1, 1, 0},
                {1, 2, 2, -1},
                {0, -1, 3, 4}
        };

        System.out.println("Floor Plan:");
        for (char[] row : plan) {
            System.out.println("  " + Arrays.toString(row));
        }

        System.out.println("Expected Distances:");
        for (int[] row : expected) {
            System.out.println("  " + Arrays.toString(row));
        }

        System.out.println("Actual Distances:");
        for (int[] row : actual) {
            System.out.println("  " + Arrays.toString(row));
        }

        for (int r = 0; r < plan.length; r++) {
            if (!Arrays.equals(expected[r], actual[r])) {
                throw new AssertionError("Problem 5 failed at row " + r);
            }
        }

        System.out.println("Status: PASS\n");
    }
}
