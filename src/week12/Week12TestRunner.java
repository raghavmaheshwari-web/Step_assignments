package week12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unified Test Runner for Week 12 Assignment:
 * Advanced Linked Lists (Floyd's Cycle Detection, Group Reversal, LRU Cache, Merge Sort, In-Place Palindrome).
 */
public class Week12TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================================");
        System.out.println("       STEP SEM-3 · Week 12 · Advanced Linked Lists Test Suite                 ");
        System.out.println("===============================================================================");
        System.out.println();

        testProblem1DeliveryRouteLoopDetector();
        testProblem2ExamScriptBatches();
        testProblem3RecentPagesCache();
        testProblem4LibraryReturnsByDueDay();
        testProblem5TokenMirrorCheck();

        System.out.println("===============================================================================");
        System.out.println("                 All Week 12 Problems Verified Successfully!                   ");
        System.out.println("===============================================================================");
    }

    private static void testProblem1DeliveryRouteLoopDetector() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 1: Delivery Route Loop Detector (Floyd's Algorithm)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: stops = 10 -> 20 -> 30 -> 40 -> 50, pos = 2
        int[] stops1 = {10, 20, 30, 40, 50};
        DeliveryRouteLoopDetector.Node r1 = DeliveryRouteLoopDetector.buildRoute(stops1, 2);
        String actual1 = DeliveryRouteLoopDetector.detectLoop(r1);
        String expected1 = "Loop: yes, starts at stop 30, length 3";
        System.out.println("Input: stops = 10 -> 20 -> 30 -> 40 -> 50, pos = 2");
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        if (!expected1.equals(actual1)) {
            throw new AssertionError("Problem 1 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2: stops = 10 -> 20 -> 30, pos = -1
        int[] stops2 = {10, 20, 30};
        DeliveryRouteLoopDetector.Node r2 = DeliveryRouteLoopDetector.buildRoute(stops2, -1);
        String actual2 = DeliveryRouteLoopDetector.detectLoop(r2);
        String expected2 = "Loop: no";
        System.out.println("Input: stops = 10 -> 20 -> 30, pos = -1");
        System.out.println("Expected: " + expected2);
        System.out.println("Actual:   " + actual2);
        if (!expected2.equals(actual2)) {
            throw new AssertionError("Problem 1 Sample 2 failed! Got: " + actual2);
        }

        // Sample 3: stops = 7, pos = 0
        int[] stops3 = {7};
        DeliveryRouteLoopDetector.Node r3 = DeliveryRouteLoopDetector.buildRoute(stops3, 0);
        String actual3 = DeliveryRouteLoopDetector.detectLoop(r3);
        String expected3 = "Loop: yes, starts at stop 7, length 1";
        System.out.println("Input: stops = 7, pos = 0");
        System.out.println("Expected: " + expected3);
        System.out.println("Actual:   " + actual3);
        if (!expected3.equals(actual3)) {
            throw new AssertionError("Problem 1 Sample 3 failed! Got: " + actual3);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem2ExamScriptBatches() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: Exam Script Batches (k-Group In-Place Reversal)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: scripts = 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8, k = 3
        ExamScriptBatches.Node s1 = ExamScriptBatches.buildList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("Input:    " + ExamScriptBatches.toListString(s1) + ", k = 3");
        ExamScriptBatches.Node res1 = ExamScriptBatches.reverseInGroups(s1, 3);
        String actual1 = ExamScriptBatches.toListString(res1);
        String expected1 = "3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8 -> null";
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        if (!expected1.equals(actual1)) {
            throw new AssertionError("Problem 2 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2: scripts = 11 -> 12, k = 3
        ExamScriptBatches.Node s2 = ExamScriptBatches.buildList(11, 12);
        System.out.println("Input:    " + ExamScriptBatches.toListString(s2) + ", k = 3");
        ExamScriptBatches.Node res2 = ExamScriptBatches.reverseInGroups(s2, 3);
        String actual2 = ExamScriptBatches.toListString(res2);
        String expected2 = "11 -> 12 -> null";
        System.out.println("Expected: " + expected2);
        System.out.println("Actual:   " + actual2);
        if (!expected2.equals(actual2)) {
            throw new AssertionError("Problem 2 Sample 2 failed! Got: " + actual2);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem3RecentPagesCache() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: Recent Pages Cache (LRU Cache with Doubly Linked List)");
        System.out.println("---------------------------------------------------------------");

        RecentPagesCache cache = new RecentPagesCache(2);
        List<String> actualLines = new ArrayList<>();

        actualLines.add(cache.logPut(1, "Home"));
        actualLines.add(cache.logPut(2, "Notes"));
        actualLines.add(cache.logGet(1));
        actualLines.add(cache.logPut(3, "Quiz"));
        actualLines.add(cache.logGet(2));
        actualLines.add(cache.logPut(1, "Home2"));
        actualLines.add(cache.logPut(4, "Mail"));
        actualLines.add(cache.logGet(3));
        actualLines.add(cache.logGet(1));
        actualLines.add(cache.getCacheState());

        List<String> expectedLines = Arrays.asList(
                "put(1,Home)",
                "put(2,Notes)",
                "get(1) -> Home",
                "put(3,Quiz) evicted 2",
                "get(2) -> -1",
                "put(1,Home2) updated",
                "put(4,Mail) evicted 3",
                "get(3) -> -1",
                "get(1) -> Home2",
                "Cache (most recent first): 1=Home2, 4=Mail"
        );

        System.out.println("Expected Output:");
        for (String line : expectedLines) {
            System.out.println("  " + line);
        }

        System.out.println("Actual Output:");
        for (String line : actualLines) {
            System.out.println("  " + line);
        }

        if (!expectedLines.equals(actualLines)) {
            throw new AssertionError("Problem 3 failed! Expected " + expectedLines + " but got " + actualLines);
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem4LibraryReturnsByDueDay() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: Library Returns by Due Day (Stable Linked List Merge Sort)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: returns = Java:15 -> DSA:3 -> OS:9 -> DBMS:3 -> CN:12 -> AI:1
        LibraryReturnsByDueDay.Node r1 = LibraryReturnsByDueDay.buildList(
                "Java:15", "DSA:3", "OS:9", "DBMS:3", "CN:12", "AI:1");
        System.out.println("Input:    " + LibraryReturnsByDueDay.toListString(r1));
        LibraryReturnsByDueDay.Node sorted1 = LibraryReturnsByDueDay.sortByDueDay(r1);
        String actual1 = LibraryReturnsByDueDay.toListString(sorted1);
        String expected1 = "AI:1 -> DSA:3 -> DBMS:3 -> OS:9 -> CN:12 -> Java:15 -> null";
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        if (!expected1.equals(actual1)) {
            throw new AssertionError("Problem 4 Sample 1 failed! Got: " + actual1);
        }

        // Sample 2: returns = (empty)
        LibraryReturnsByDueDay.Node r2 = null;
        LibraryReturnsByDueDay.Node sorted2 = LibraryReturnsByDueDay.sortByDueDay(r2);
        String actual2 = LibraryReturnsByDueDay.toListString(sorted2);
        String expected2 = "null";
        System.out.println("Empty input -> Actual: " + actual2 + " (Expected: " + expected2 + ")");
        if (!expected2.equals(actual2)) {
            throw new AssertionError("Problem 4 Sample 2 failed!");
        }

        System.out.println("Status: PASS\n");
    }

    private static void testProblem5TokenMirrorCheck() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Token Mirror Check (In-Place Check & Restore)");
        System.out.println("---------------------------------------------------------------");

        // Sample 1: tokens = 4 -> 7 -> 9 -> 7 -> 4
        TokenMirrorCheck.Node t1 = TokenMirrorCheck.buildList(4, 7, 9, 7, 4);
        System.out.println("Tokens 1: " + TokenMirrorCheck.toListString(t1));
        boolean pal1 = TokenMirrorCheck.isPalindrome(t1);
        String listAfter1 = TokenMirrorCheck.toListString(t1);
        System.out.println("Palindrome: " + pal1 + " (Expected: true)");
        System.out.println("List after check: " + listAfter1);
        if (!pal1 || !listAfter1.equals("4 -> 7 -> 9 -> 7 -> 4 -> null")) {
            throw new AssertionError("Problem 5 Sample 1 failed!");
        }

        // Sample 2: tokens = 4 -> 7 -> 7 -> 9
        TokenMirrorCheck.Node t2 = TokenMirrorCheck.buildList(4, 7, 7, 9);
        System.out.println("Tokens 2: " + TokenMirrorCheck.toListString(t2));
        boolean pal2 = TokenMirrorCheck.isPalindrome(t2);
        String listAfter2 = TokenMirrorCheck.toListString(t2);
        System.out.println("Palindrome: " + pal2 + " (Expected: false)");
        System.out.println("List after check: " + listAfter2);
        if (pal2 || !listAfter2.equals("4 -> 7 -> 7 -> 9 -> null")) {
            throw new AssertionError("Problem 5 Sample 2 failed!");
        }

        System.out.println("Status: PASS\n");
    }
}
