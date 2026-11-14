package week9;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 5: Spiral Stock Audit Route
 *
 * <p>Simulates an inventory robot auditing warehouse bins arranged in a 2D rectangular grid.
 * The robot starts at (0, 0) and travels in a clockwise spiral inward until all bins are visited.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Maintains four moving boundaries: {@code top}, {@code bottom}, {@code left}, and {@code right}.
 * In each outer cycle:
 * <ol>
 *   <li>Traverse from {@code left} to {@code right} along the {@code top} row, then increment {@code top}.</li>
 *   <li>Traverse from {@code top} to {@code bottom} along the {@code right} column, then decrement {@code right}.</li>
 *   <li>If {@code top <= bottom}, traverse from {@code right} to {@code left} along the {@code bottom} row, then decrement {@code bottom}.</li>
 *   <li>If {@code left <= right}, traverse from {@code bottom} to {@code top} along the {@code left} column, then increment {@code left}.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(m * n), where m is the number of rows and n is the number of columns,
 *       since every cell is visited exactly once.</li>
 *   <li><b>Additional Space Complexity:</b> O(1) auxiliary space (excluding the output list of size m * n).</li>
 * </ul>
 *
 * <p><b>Boundary Protection:</b>
 * Rectangular matrices can end with a single row or single column remnant. Checking {@code top <= bottom}
 * before traversing right-to-left, and {@code left <= right} before traversing bottom-to-top, prevents
 * visited cells from being processed twice.
 */
public class SpiralStockAuditRoute {

    /**
     * Computes the clockwise spiral audit path through the 2D grid of bin IDs.
     *
     * @param grid 2D rectangular matrix of bin IDs
     * @return list of bin IDs in the order visited by the robot
     */
    public static List<Integer> auditRoute(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null.");
        }
        if (grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid cannot be empty.");
        }

        int rows = grid.length;
        int cols = grid[0].length;

        // Validate uniform column dimensions (no jagged arrays)
        for (int r = 1; r < rows; r++) {
            if (grid[r] == null || grid[r].length != cols) {
                throw new IllegalArgumentException("Grid must be rectangular with uniform row lengths.");
            }
        }

        List<Integer> route = new ArrayList<>(rows * cols);

        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (top <= bottom && left <= right) {
            // 1. Traverse top row from left to right
            for (int col = left; col <= right; col++) {
                route.add(grid[top][col]);
            }
            top++;

            // 2. Traverse right column from top to bottom
            for (int row = top; row <= bottom; row++) {
                route.add(grid[row][right]);
            }
            right--;

            // 3. Traverse bottom row from right to left (check boundary hasn't crossed)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    route.add(grid[bottom][col]);
                }
                bottom--;
            }

            // 4. Traverse left column from bottom to top (check boundary hasn't crossed)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    route.add(grid[row][left]);
                }
                left++;
            }
        }

        return route;
    }
}
