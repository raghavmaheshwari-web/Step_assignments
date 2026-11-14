package week9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Problem 3: Net-Balance Period Counter
 *
 * <p>Counts how many contiguous periods (runs of one or more consecutive days)
 * in a wallet app have a net total transaction amount exactly equal to a target amount k.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses the Prefix Sum + Hash Map technique.
 * If the cumulative sum up to day {@code j} is {@code S}, any contiguous period from day
 * {@code i} to day {@code j} sums to {@code k} if and only if:
 * <pre>
 *   S_j - S_(i-1) = k  ===>  S_(i-1) = S_j - k
 * </pre>
 * By recording the frequency of each prefix sum in a hash map as we iterate, we can count
 * all valid subarrays ending at the current index in O(1) average time.
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Brute-force Approach:</b> Checking all subarray pairs (i, j) and computing their sums
 *       takes O(N^2) time and O(1) auxiliary space.</li>
 *   <li><b>Optimized Prefix Sum + Hash Map Approach:</b>
 *       <ul>
 *         <li>Time Complexity: O(N) average time, as each element is visited once and hash map
 *             lookups/insertions run in O(1) average time.</li>
 *         <li>Additional Space Complexity: O(N) to store frequencies of distinct prefix sums in the hash map.</li>
 *       </ul>
 *   </li>
 *   <li><b>Why doesn't a sliding window work?</b>
 *       A two-pointer sliding window relies on monotonicity (non-negative numbers where expanding
 *       always increases or preserves the sum, and shrinking always decreases or preserves it).
 *       Because wallet transactions include negative numbers (debits), expanding a window may decrease
 *       the sum and shrinking it may increase the sum, breaking the monotonic invariant.
 *   </li>
 * </ul>
 */
public class NetBalancePeriodCounter {

    /**
     * Represents a contiguous period with its start index, end index, and net sum.
     */
    public static class PeriodRange {
        private final int startIndex;
        private final int endIndex;

        public PeriodRange(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PeriodRange that = (PeriodRange) o;
            return startIndex == that.startIndex && endIndex == that.endIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startIndex, endIndex);
        }

        @Override
        public String toString() {
            return "[" + startIndex + ", " + endIndex + "]";
        }
    }

    /**
     * Counts the number of contiguous periods whose net transaction sum equals k.
     *
     * @param transactions array of daily net transactions (positive credits, negative debits)
     * @param k target net balance
     * @return total count of matching contiguous periods
     */
    public static long countPeriods(int[] transactions, long k) {
        if (transactions == null) {
            throw new IllegalArgumentException("Transactions array cannot be null.");
        }
        if (transactions.length == 0) {
            return 0;
        }

        // Map prefix sums to their frequencies
        Map<Long, Long> prefixSumFreq = new HashMap<>();
        // Base case: prefix sum of 0 has occurred once before index 0 (empty prefix)
        prefixSumFreq.put(0L, 1L);

        long currentSum = 0;
        long periodCount = 0;

        for (int tx : transactions) {
            currentSum += tx;
            long requiredPrefix = currentSum - k;

            if (prefixSumFreq.containsKey(requiredPrefix)) {
                periodCount += prefixSumFreq.get(requiredPrefix);
            }

            prefixSumFreq.put(currentSum, prefixSumFreq.getOrDefault(currentSum, 0L) + 1L);
        }

        return periodCount;
    }

    /**
     * Identifies the exact index ranges [start, end] of all contiguous periods summing to k.
     * Useful for diagnostics, auditing, and sample verification.
     *
     * @param transactions array of daily net transactions
     * @param k target net balance
     * @return list of PeriodRange instances
     */
    public static List<PeriodRange> findPeriodRanges(int[] transactions, long k) {
        if (transactions == null) {
            throw new IllegalArgumentException("Transactions array cannot be null.");
        }

        List<PeriodRange> periods = new ArrayList<>();
        // Map prefix sum to list of end indices where that sum occurred
        Map<Long, List<Integer>> prefixIndices = new HashMap<>();

        // Base case: prefix sum of 0 at pseudo-index -1
        List<Integer> baseList = new ArrayList<>();
        baseList.add(-1);
        prefixIndices.put(0L, baseList);

        long currentSum = 0;

        for (int j = 0; j < transactions.length; j++) {
            currentSum += transactions[j];
            long requiredPrefix = currentSum - k;

            if (prefixIndices.containsKey(requiredPrefix)) {
                for (int prevIndex : prefixIndices.get(requiredPrefix)) {
                    periods.add(new PeriodRange(prevIndex + 1, j));
                }
            }

            prefixIndices.computeIfAbsent(currentSum, key -> new ArrayList<>()).add(j);
        }

        return periods;
    }
}
