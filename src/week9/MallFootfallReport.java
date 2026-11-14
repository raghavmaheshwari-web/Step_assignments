package week9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Problem 1: Mall Footfall Range Report
 *
 * <p>A shopping mall records hourly visitor counts. The operations team repeatedly
 * queries the total footfall between two hours [start, end] inclusive.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses a 1D Prefix Sum array of size (N + 1) where {@code prefix[i]} stores the sum
 * of visitors from hour 0 up to hour (i - 1). The total footfall for range [start, end]
 * is computed in O(1) time as: {@code prefix[end + 1] - prefix[start]}.
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Naive Approach:</b> Answering each query by iterating from {@code start} to {@code end}
 *       takes O(N) per query, leading to O(Q * N) total time for Q queries and O(1) auxiliary space.</li>
 *   <li><b>Optimized Prefix Sum Approach:</b>
 *       <ul>
 *         <li>Preprocessing Time: O(N) to construct the prefix sum array.</li>
 *         <li>Query Time: O(1) per query, leading to O(Q) total query time.</li>
 *         <li>Total Time Complexity: O(N + Q).</li>
 *         <li>Additional Space Complexity: O(N) to store the prefix sum array.</li>
 *       </ul>
 *   </li>
 *   <li><b>When is preprocessing worth paying?</b>
 *       The O(N) precomputation cost is worth paying whenever Q >= 1 in repeated-query environments
 *       (e.g., Q up to 10^5 queries against 10^5 hours), dropping query time from hours/minutes to
 *       instantaneous O(1) memory lookups.
 *   </li>
 * </ul>
 */
public class MallFootfallReport {

    /**
     * Immutable representation of a range query [start, end] inclusive.
     */
    public static class Query {
        private final int start;
        private final int end;

        public Query(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Query query = (Query) o;
            return start == query.start && end == query.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }
    }

    /**
     * Precomputed footfall tracker supporting rapid O(1) range queries.
     */
    public static class FootfallTracker {
        private final long[] prefixSums;
        private final int numHours;

        public FootfallTracker(int[] visitors) {
            if (visitors == null) {
                throw new IllegalArgumentException("Visitors array cannot be null.");
            }
            if (visitors.length == 0) {
                throw new IllegalArgumentException("Visitors array must contain at least one hour of data.");
            }

            this.numHours = visitors.length;
            this.prefixSums = new long[numHours + 1];

            for (int i = 0; i < numHours; i++) {
                if (visitors[i] < 0) {
                    throw new IllegalArgumentException("Hourly footfall cannot be negative: " + visitors[i]);
                }
                prefixSums[i + 1] = prefixSums[i] + visitors[i];
            }
        }

        /**
         * Answers a single range query [start, end] inclusive in O(1) time.
         *
         * @param start start hour index (0-based)
         * @param end end hour index (0-based)
         * @return total footfall between start and end inclusive
         */
        public long query(int start, int end) {
            if (start < 0 || end >= numHours || start > end) {
                throw new IllegalArgumentException(String.format(
                        "Invalid query range [%d, %d] for data of length %d.", start, end, numHours));
            }
            return prefixSums[end + 1] - prefixSums[start];
        }

        public int getNumHours() {
            return numHours;
        }
    }

    /**
     * Evaluates a list of range queries against visitor data without re-adding range values.
     *
     * @param visitors hourly footfall counts
     * @param queries list of range queries [start, end]
     * @return list of total visitors for each query in the original order
     */
    public static List<Long> footfallReport(int[] visitors, List<Query> queries) {
        if (queries == null) {
            throw new IllegalArgumentException("Queries list cannot be null.");
        }

        FootfallTracker tracker = new FootfallTracker(visitors);
        List<Long> results = new ArrayList<>(queries.size());

        for (Query q : queries) {
            if (q == null) {
                throw new IllegalArgumentException("Query element cannot be null.");
            }
            results.add(tracker.query(q.getStart(), q.getEnd()));
        }

        return results;
    }

    /**
     * Overload accepting 2D queries array [[start, end], ...].
     */
    public static long[] footfallReport(int[] visitors, int[][] queries) {
        if (queries == null) {
            throw new IllegalArgumentException("Queries array cannot be null.");
        }

        FootfallTracker tracker = new FootfallTracker(visitors);
        long[] results = new long[queries.length];

        for (int i = 0; i < queries.length; i++) {
            if (queries[i] == null || queries[i].length != 2) {
                throw new IllegalArgumentException("Each query must be an array of [start, end].");
            }
            results[i] = tracker.query(queries[i][0], queries[i][1]);
        }

        return results;
    }
}
