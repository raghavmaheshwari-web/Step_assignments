package week9;

import java.util.Arrays;
import java.util.List;

/**
 * Unified Test Runner for Week 9 Assignment:
 * Introduction to Data Structures (Arrays, Hashing, Two Pointers, Sliding Window, Prefix Sums, Binary Search).
 */
public class Week9TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================================");
        System.out.println("            STEP SEM-3 · Week 9 · Data Structures Test Suite                   ");
        System.out.println("===============================================================================");
        System.out.println();

        testProblem1MallFootfallReport();
        testProblem2BudgetFriendlyStreak();
        testProblem3NetBalancePeriodCounter();
        testProblem4ExamScoreBandCounter();
        testProblem5SpiralStockAuditRoute();

        System.out.println("===============================================================================");
        System.out.println("                  All Week 9 Problems Verified Successfully!                   ");
        System.out.println("===============================================================================");
    }

    private static void testProblem1MallFootfallReport() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 1: Mall Footfall Range Report (Prefix Sum)");
        System.out.println("---------------------------------------------------------------");

        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        List<MallFootfallReport.Query> queries = Arrays.asList(
                new MallFootfallReport.Query(0, 2),
                new MallFootfallReport.Query(2, 5),
                new MallFootfallReport.Query(4, 6),
                new MallFootfallReport.Query(3, 3)
        );

        List<Long> results = MallFootfallReport.footfallReport(visitors, queries);
        List<Long> expected = Arrays.asList(22L, 31L, 27L, 9L);

        System.out.println("Visitors array: " + Arrays.toString(visitors));
        System.out.println("Queries: " + queries);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + results);

        if (!results.equals(expected)) {
            throw new AssertionError("Problem 1 test failed! Expected " + expected + " but got " + results);
        }

        // Additional boundary check: entire day footfall
        MallFootfallReport.FootfallTracker tracker = new MallFootfallReport.FootfallTracker(visitors);
        long fullDay = tracker.query(0, visitors.length - 1);
        System.out.println("Full day footfall [0, 6]: " + fullDay + " (Expected: 58)");
        if (fullDay != 58L) {
            throw new AssertionError("Full day query failed!");
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem2BudgetFriendlyStreak() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: Longest Budget-Friendly Streak (Sliding Window)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1
        int[] costs1 = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        long budget1 = 8;
        BudgetFriendlyStreak.StreakResult result1 = BudgetFriendlyStreak.longestStreak(costs1, budget1);
        System.out.println("Costs 1: " + Arrays.toString(costs1) + ", Budget: " + budget1);
        System.out.println("Expected: (4, 4) | Actual: " + result1);
        if (result1.getLength() != 4 || result1.getStartIndex() != 4) {
            throw new AssertionError("Problem 2 Sample 1 failed! Got: " + result1);
        }

        // Sample 2
        int[] costs2 = {9, 10};
        long budget2 = 8;
        BudgetFriendlyStreak.StreakResult result2 = BudgetFriendlyStreak.longestStreak(costs2, budget2);
        System.out.println("Costs 2: " + Arrays.toString(costs2) + ", Budget: " + budget2);
        System.out.println("Expected: (0, -1) | Actual: " + result2);
        if (result2.getLength() != 0 || result2.getStartIndex() != -1) {
            throw new AssertionError("Problem 2 Sample 2 failed! Got: " + result2);
        }

        // Tie-breaking test: two runs of length 2, earliest should be selected
        int[] costs3 = {2, 2, 9, 2, 2};
        long budget3 = 4;
        BudgetFriendlyStreak.StreakResult result3 = BudgetFriendlyStreak.longestStreak(costs3, budget3);
        System.out.println("Costs 3: " + Arrays.toString(costs3) + ", Budget: " + budget3);
        System.out.println("Expected (earliest tie): (2, 0) | Actual: " + result3);
        if (result3.getLength() != 2 || result3.getStartIndex() != 0) {
            throw new AssertionError("Problem 2 Tie-break failed! Got: " + result3);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem3NetBalancePeriodCounter() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: Net-Balance Period Counter (Prefix Sum + HashMap)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1
        int[] transactions1 = {3, 4, -7, 1, 3, 3, 1, -4};
        long k1 = 7;
        long count1 = NetBalancePeriodCounter.countPeriods(transactions1, k1);
        List<NetBalancePeriodCounter.PeriodRange> ranges1 = NetBalancePeriodCounter.findPeriodRanges(transactions1, k1);

        System.out.println("Transactions 1: " + Arrays.toString(transactions1) + ", k = " + k1);
        System.out.println("Expected count: 4 | Actual count: " + count1);
        System.out.println("Matched period ranges: " + ranges1);

        if (count1 != 4L) {
            throw new AssertionError("Problem 3 Sample 1 failed! Expected 4 but got: " + count1);
        }

        // Sample 2
        int[] transactions2 = {1, 2, 3};
        long k2 = 10;
        long count2 = NetBalancePeriodCounter.countPeriods(transactions2, k2);
        System.out.println("Transactions 2: " + Arrays.toString(transactions2) + ", k = " + k2);
        System.out.println("Expected count: 0 | Actual count: " + count2);

        if (count2 != 0L) {
            throw new AssertionError("Problem 3 Sample 2 failed! Expected 0 but got: " + count2);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem4ExamScoreBandCounter() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: Exam Score Band Counter (Binary Search)");
        System.out.println("---------------------------------------------------------------");

        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        // Sample 1
        int low1 = 42, high1 = 58;
        int count1 = ExamScoreBandCounter.countInBand(scores, low1, high1);
        System.out.println("Scores: " + Arrays.toString(scores));
        System.out.println("Band [" + low1 + ", " + high1 + "] -> Expected: 6 | Actual: " + count1);
        if (count1 != 6) {
            throw new AssertionError("Problem 4 Sample 1 failed! Expected 6 but got: " + count1);
        }

        // Sample 2
        int low2 = 90, high2 = 100;
        int count2 = ExamScoreBandCounter.countInBand(scores, low2, high2);
        System.out.println("Band [" + low2 + ", " + high2 + "] -> Expected: 0 | Actual: " + count2);
        if (count2 != 0) {
            throw new AssertionError("Problem 4 Sample 2 failed! Expected 0 but got: " + count2);
        }

        // Boundary match: single score with duplicates (58)
        int low3 = 58, high3 = 58;
        int count3 = ExamScoreBandCounter.countInBand(scores, low3, high3);
        System.out.println("Band [" + low3 + ", " + high3 + "] -> Expected: 3 | Actual: " + count3);
        if (count3 != 3) {
            throw new AssertionError("Problem 4 Single-value duplicate failed! Expected 3 but got: " + count3);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem5SpiralStockAuditRoute() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Spiral Stock Audit Route (2D Clockwise Spiral)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1 (3x4 rectangular grid)
        int[][] grid1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        List<Integer> route1 = SpiralStockAuditRoute.auditRoute(grid1);
        List<Integer> expected1 = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);

        System.out.println("Grid 1 (3x4):");
        for (int[] row : grid1) {
            System.out.println("  " + Arrays.toString(row));
        }
        System.out.println("Expected route: " + expected1);
        System.out.println("Actual route:   " + route1);

        if (!route1.equals(expected1)) {
            throw new AssertionError("Problem 5 Sample 1 failed! Expected " + expected1 + " but got " + route1);
        }

        // Edge case 1: Single row (1x4)
        int[][] gridRow = {{10, 20, 30, 40}};
        List<Integer> routeRow = SpiralStockAuditRoute.auditRoute(gridRow);
        List<Integer> expectedRow = Arrays.asList(10, 20, 30, 40);
        System.out.println("Single row (1x4) -> Actual: " + routeRow + " (Expected: " + expectedRow + ")");
        if (!routeRow.equals(expectedRow)) {
            throw new AssertionError("Single row test failed!");
        }

        // Edge case 2: Single column (4x1)
        int[][] gridCol = {{10}, {20}, {30}, {40}};
        List<Integer> routeCol = SpiralStockAuditRoute.auditRoute(gridCol);
        List<Integer> expectedCol = Arrays.asList(10, 20, 30, 40);
        System.out.println("Single column (4x1) -> Actual: " + routeCol + " (Expected: " + expectedCol + ")");
        if (!routeCol.equals(expectedCol)) {
            throw new AssertionError("Single column test failed!");
        }

        // Edge case 3: 3x3 square matrix
        int[][] gridSquare = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> routeSquare = SpiralStockAuditRoute.auditRoute(gridSquare);
        List<Integer> expectedSquare = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        System.out.println("Square 3x3 -> Actual: " + routeSquare + " (Expected: " + expectedSquare + ")");
        if (!routeSquare.equals(expectedSquare)) {
            throw new AssertionError("Square matrix test failed!");
        }

        System.out.println("Status: PASS\n");
    }
}
