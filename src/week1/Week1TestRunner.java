package week1;

/**
 * Week1TestRunner
 *
 * Unified test suite that executes and verifies all 5 problems
 * from the Week 1 STEP Assignment using sample inputs and edge cases.
 */
public class Week1TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("            STEP SEM-3 WEEK 1 ASSIGNMENT TEST SUITE            ");
        System.out.println("===============================================================\n");

        testProblem1();
        testProblem2();
        testProblem3();
        testProblem4();
        testProblem5();

        System.out.println("\n===============================================================");
        System.out.println("                 ALL ASSIGNMENT TESTS COMPLETED                ");
        System.out.println("===============================================================");
    }

    private static void testProblem1() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 1: The Exam Hall Seat Duplication Checker");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: {101, 102, 103, 102, 105}\nExpected: Duplicate Seat Number Found: 102\nActual:   ");
        SeatDuplicationChecker.checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});

        System.out.print("\nInput: {101, 102, 103, 104, 105}\nExpected: No Duplicate Seats Found\nActual:   ");
        SeatDuplicationChecker.checkDuplicateSeats(new int[]{101, 102, 103, 104, 105});

        System.out.print("\nInput: {50, 60, 50, 70, 60}\nExpected: Duplicate Seat Number Found: 50 \\n Duplicate Seat Number Found: 60\nActual:   ");
        SeatDuplicationChecker.checkDuplicateSeats(new int[]{50, 60, 50, 70, 60});
        System.out.println();
    }

    private static void testProblem2() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: The Typing Speed Test Accuracy Checker");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: original=\"hello world\", typed=\"hello worlt\"\n" +
                "Expected: Matched: 10/11 | Accuracy: 90.91% | First Mismatch at position 11 ('d' vs 't')\nActual:   ");
        TypingAccuracyChecker.checkTypingAccuracy("hello world", "hello worlt");

        System.out.print("\nInput: original=\"coding\", typed=\"coding\"\n" +
                "Expected: Matched: 6/6 | Accuracy: 100.00% | No Mismatches\nActual:   ");
        TypingAccuracyChecker.checkTypingAccuracy("coding", "coding");
        System.out.println();
    }

    private static void testProblem3() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: The Traffic Signal Streak Analyzer");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: \"RRGGGYRR\"\nExpected: Longest Streak: 'G' repeated 3 times\nActual:   ");
        TrafficSignalStreakAnalyzer.findLongestStreak("RRGGGYRR");

        System.out.print("\nInput: \"RRRRYYGG\"\nExpected: Longest Streak: 'R' repeated 4 times\nActual:   ");
        TrafficSignalStreakAnalyzer.findLongestStreak("RRRRYYGG");
        System.out.println();
    }

    private static void testProblem4() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: The Warehouse Inventory Balancer");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: sectionA={20,15,30}, sectionB={25,10,30}\n" +
                "Expected: Section A Total: 65 | Section B Total: 65 | Status: Balanced | Highest Quantity: 30 (Section A, Item 3)\nActual:   ");
        WarehouseInventoryBalancer.analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});
        System.out.println();
    }

    private static void testProblem5() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: The Movie Review Word Length Profiler");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: \"This movie was absolutely fantastic and thrilling\"\n" +
                "Expected: Short: 3 | Medium: 1 | Long: 3\nActual:   ");
        MovieReviewWordLengthProfiler.classifyWordLengths("This movie was absolutely fantastic and thrilling");
        System.out.println();
    }
}
