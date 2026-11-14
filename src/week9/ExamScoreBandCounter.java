package week9;

/**
 * Problem 4: Exam Score Band Counter
 *
 * <p>Given an ascending sorted array of student exam marks (which can contain duplicates),
 * counts how many students scored within a score band [low, high] inclusive.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses modified Binary Search to locate boundary indices in logarithmic time:
 * <ul>
 *   <li><b>Lower Bound:</b> Finds the first index {@code i} such that {@code scores[i] >= low}.</li>
 *   <li><b>Upper Bound:</b> Finds the first index {@code j} such that {@code scores[j] > high}.</li>
 * </ul>
 * The count of students in the band is simply {@code j - i}.
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Linear Scan:</b> Checking each element takes O(N) time per query. With N = 10^6 and
 *       frequent queries, linear scanning causes severe performance bottlenecks. Auxiliary space: O(1).</li>
 *   <li><b>Optimized Binary Search Approach:</b>
 *       <ul>
 *         <li>Time Complexity: O(log N) per query, requiring at most ~20 comparisons for 10^6 scores.</li>
 *         <li>Additional Space Complexity: O(1) auxiliary memory.</li>
 *       </ul>
 *   </li>
 *   <li><b>How duplicates affect the search:</b>
 *       A standard binary search terminates as soon as any match is found, which fails when duplicate
 *       scores span across an interval. For instance, if {@code low = 42} appears at indices 1 and 2,
 *       standard binary search might stop at index 2, omitting index 1.
 *       By modifying the search to find the lower bound (first index with value >= low) and the upper bound
 *       (first index with value > high), all duplicate boundary occurrences are captured accurately.
 *   </li>
 * </ul>
 */
public class ExamScoreBandCounter {

    /**
     * Counts the number of scores falling in [low, high] inclusive.
     *
     * @param scores sorted array of exam scores in ascending order
     * @param low lower band limit (inclusive)
     * @param high upper band limit (inclusive)
     * @return count of scores s such that low <= s <= high
     */
    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null) {
            throw new IllegalArgumentException("Scores array cannot be null.");
        }
        if (low > high) {
            throw new IllegalArgumentException(String.format("Invalid band: low (%d) > high (%d).", low, high));
        }
        if (scores.length == 0) {
            return 0;
        }

        int lowerIndex = findLowerBound(scores, low);
        int upperIndex = findUpperBound(scores, high);

        return Math.max(0, upperIndex - lowerIndex);
    }

    /**
     * Finds the first index with score >= target.
     * If all elements are smaller than target, returns scores.length.
     *
     * @param scores sorted array of exam scores
     * @param target target value
     * @return index of first element >= target
     */
    public static int findLowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Finds the first index with score > target.
     * If all elements are <= target, returns scores.length.
     *
     * @param scores sorted array of exam scores
     * @param target target value
     * @return index of first element > target
     */
    public static int findUpperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
