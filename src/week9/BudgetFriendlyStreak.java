package week9;

import java.util.Objects;

/**
 * Problem 2: Longest Budget-Friendly Streak
 *
 * <p>Finds the longest contiguous run of study snack days whose total cost stays within
 * a given budget. If several runs have the same maximum length, the one starting first
 * is returned. If no single day fits within the budget, returns (0, -1).
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses a dynamic Sliding Window (Two Pointers) technique.
 * Both {@code left} and {@code right} traverse the array from index 0. As {@code right}
 * expands the window, daily snack costs are added to {@code currentSum}. When {@code currentSum}
 * exceeds {@code budget}, the {@code left} pointer contracts the window until {@code currentSum <= budget}.
 * We update the best streak only when {@code currentLength > bestLength}, strictly preserving
 * the earliest starting index on ties.
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Brute-force Approach:</b> Checking every possible contiguous subarray (i, j) requires
 *       testing O(N^2) windows, taking O(N^2) time with prefix sums (or O(N^3) naively) and O(1) space.</li>
 *   <li><b>Optimized Sliding Window:</b>
 *       <ul>
 *         <li>Time Complexity: O(N), as each pointer ({@code left} and {@code right}) traverses
 *             the array from 0 to N-1 at most once, performing amortized O(1) work per element.</li>
 *         <li>Additional Space Complexity: O(1) auxiliary memory.</li>
 *       </ul>
 *   </li>
 *   <li><b>Why is non-negativity essential?</b>
 *       Non-negative costs ensure the monotonicity of the window's total cost:
 *       expanding the right boundary strictly never decreases the sum, and advancing the left
 *       boundary strictly never increases the sum. If costs could be negative, adding an element
 *       could decrease the sum or shrinking the window could increase it, invalidating the greedy
 *       sliding window property and requiring a prefix-sum hash map or dynamic programming instead.
 *   </li>
 * </ul>
 */
public class BudgetFriendlyStreak {

    /**
     * Immutable result holding the length and start index of the longest streak.
     */
    public static class StreakResult {
        private final int length;
        private final int startIndex;

        public StreakResult(int length, int startIndex) {
            this.length = length;
            this.startIndex = startIndex;
        }

        public int getLength() {
            return length;
        }

        public int getStartIndex() {
            return startIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StreakResult that = (StreakResult) o;
            return length == that.length && startIndex == that.startIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(length, startIndex);
        }

        @Override
        public String toString() {
            return "(" + length + ", " + startIndex + ")";
        }
    }

    /**
     * Finds the longest contiguous streak of days whose sum <= budget.
     *
     * @param costs daily non-negative costs
     * @param budget non-negative budget limit
     * @return StreakResult representing (length, startIndex)
     */
    public static StreakResult longestStreak(int[] costs, long budget) {
        if (costs == null) {
            throw new IllegalArgumentException("Costs array cannot be null.");
        }
        if (costs.length == 0) {
            throw new IllegalArgumentException("Costs array cannot be empty.");
        }
        if (budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative: " + budget);
        }

        int n = costs.length;
        long currentSum = 0;
        int left = 0;
        int bestLength = 0;
        int bestStartIndex = -1;

        for (int right = 0; right < n; right++) {
            if (costs[right] < 0) {
                throw new IllegalArgumentException("Cost cannot be negative at index " + right + ": " + costs[right]);
            }

            currentSum += costs[right];

            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }

            if (currentSum <= budget && left <= right) {
                int currentLength = right - left + 1;
                // Strictly greater ensures the earliest run is retained on tie
                if (currentLength > bestLength) {
                    bestLength = currentLength;
                    bestStartIndex = left;
                }
            }
        }

        if (bestLength == 0) {
            return new StreakResult(0, -1);
        }

        return new StreakResult(bestLength, bestStartIndex);
    }
}
