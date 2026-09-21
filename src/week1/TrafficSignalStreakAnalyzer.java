package week1;

/**
 * Problem 3: The Traffic Signal Streak Analyzer
 *
 * Analyzes traffic signal color logs ('R', 'Y', 'G') to find the longest
 * continuous streak of consecutive identical signal colors.
 */
public class TrafficSignalStreakAnalyzer {

    /**
     * Finds and prints the longest continuous streak of the same signal color.
     *
     * @param signalLog string containing consecutive signal readings (e.g., "RRGGGYRR")
     * @throws IllegalArgumentException if signalLog is null, empty, or contains invalid signal characters
     */
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            throw new IllegalArgumentException("Signal log cannot be null or empty.");
        }

        // Validate signal characters
        for (int i = 0; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c != 'R' && c != 'Y' && c != 'G') {
                throw new IllegalArgumentException("Invalid signal color '" + c + "' at index " + i +
                        ". Allowed colors are 'R', 'Y', and 'G'.");
            }
        }

        char longestColor = signalLog.charAt(0);
        int maxStreak = 1;

        char currentColor = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c == currentColor) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    longestColor = currentColor;
                }
                currentColor = c;
                currentStreak = 1;
            }
        }

        // Final check for the streak at the end of the string
        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            longestColor = currentColor;
        }

        System.out.println("Longest Streak: '" + longestColor + "' repeated " + maxStreak + " times");
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: 'G' streak of 3 ---");
        String signalLog1 = "RRGGGYRR";
        findLongestStreak(signalLog1);

        System.out.println("\n--- Test Case 2: 'R' streak of 4 ---");
        String signalLog2 = "RRRRYYGG";
        findLongestStreak(signalLog2);

        System.out.println("\n--- Test Case 3: Single character ---");
        String signalLog3 = "Y";
        findLongestStreak(signalLog3);

        System.out.println("\n--- Test Case 4: Long trailing streak ---");
        String signalLog4 = "RYGYYYYY";
        findLongestStreak(signalLog4);
    }
}
