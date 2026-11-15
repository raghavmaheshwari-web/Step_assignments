package week11;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem 1: The Canteen Price Streak
 *
 * <p>The canteen manager records the price of a samosa plate every day. For each day,
 * computes the price streak: the number of consecutive days ending today (including today)
 * on which the price was less than or equal to today's price.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses a Monotonic Stack (storing day indices in decreasing order of prices):
 * <ol>
 *   <li>Maintain a stack of day indices.</li>
 *   <li>Before processing day {@code i}, pop all indices from the stack whose prices
 *       are {@code <= price[i]}. These days are covered by today's price and can never block
 *       any future day whose price exceeds or equals today's price.</li>
 *   <li>If the stack is empty, every preceding day had a price {@code <= price[i]},
 *       so the streak is {@code i + 1}.</li>
 *   <li>If the stack is not empty, the index on top represents the nearest preceding day
 *       with a strictly greater price, so the streak is {@code i - stack.peek()}.</li>
 *   <li>Push the current index {@code i} onto the stack.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Naive Approach:</b> A nested loop comparing day {@code i} with previous days takes
 *       O(N^2) time in the worst case (e.g. non-decreasing prices).</li>
 *   <li><b>Optimized Monotonic Stack Approach:</b>
 *       <ul>
 *         <li><b>Time Complexity:</b> O(N), because every index is pushed onto the stack exactly once
 *             and popped at most once across the entire run.</li>
 *         <li><b>Space Complexity:</b> O(N) auxiliary space to store indices on the stack.</li>
 *       </ul>
 *   </li>
 * </ul>
 */
public class CanteenPriceStreak {

    /**
     * Computes the price streak for every day in O(N) time.
     *
     * @param price array of daily prices
     * @return array of streaks for each day
     */
    public static int[] priceStreak(int[] price) {
        if (price == null) {
            throw new IllegalArgumentException("Price array cannot be null.");
        }
        if (price.length == 0) {
            return new int[0];
        }

        int n = price.length;
        int[] streaks = new int[n];
        // Stack stores indices of days with strictly decreasing prices
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Pop days whose prices are less than or equal to today's price
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            // If empty, all previous days had price <= today's price
            streaks[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());

            // Push current day index onto stack
            stack.push(i);
        }

        return streaks;
    }
}
