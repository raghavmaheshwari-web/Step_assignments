package week11;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Problem 5: The Fire Drill Exits
 *
 * <p>Computes the shortest evacuation distance from every room in a floor plan grid to the
 * nearest emergency exit using Multi-Source Breadth-First Search (BFS).
 *
 * <p><b>Grid Symbols:</b>
 * <ul>
 *   <li>{@code 'E'}: Emergency exit (distance = 0)</li>
 *   <li>{@code '#'}: Obstacle / wall (distance = -1)</li>
 *   <li>{@code '.'}: Open room (distance = minimum steps to nearest exit, or -1 if unreachable)</li>
 * </ul>
 *
 * <p><b>Algorithmic Strategy (Multi-Source BFS):</b>
 * Running a separate BFS from each open room would take O(R^2 * C^2), which is redundant and slow.
 * Instead, this solution initializes the search from all exits simultaneously:
 * <ol>
 *   <li>Initialize a distance matrix with {@code -1} for all cells.</li>
 *   <li>Find every exit cell ({@code 'E'}), set its distance to {@code 0}, and enqueue it into a BFS queue.</li>
 *   <li>Process cells level by level. For each valid neighbor in the 4 cardinal directions
 *       (up, down, left, right): if it is an open room ({@code '.'}) and has not been visited
 *       ({@code dist == -1}), set its distance to {@code current_dist + 1} and enqueue it.</li>
 *   <li>Because BFS explores nodes in increasing order of distance from all exits in parallel,
 *       the first time a room is discovered is guaranteed to be via the shortest possible path.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(R * C), where R is rows and C is columns. Each cell is enqueued
 *       and processed at most once.</li>
 *   <li><b>Space Complexity:</b> O(R * C) auxiliary space for the distance grid and BFS queue.</li>
 * </ul>
 */
public class FireDrillExits {

    private static final int[][] DIRECTIONS = {
            {-1, 0}, // Up
            {1, 0},  // Down
            {0, -1}, // Left
            {0, 1}   // Right
    };

    /**
     * Calculates the minimum number of steps to the nearest exit for every cell.
     *
     * @param plan 2D grid of characters ('E', '#', '.')
     * @return 2D grid of minimum distances (0 for exit, -1 for wall/unreachable)
     */
    public static int[][] exitDistance(char[][] plan) {
        if (plan == null) {
            throw new IllegalArgumentException("Floor plan cannot be null.");
        }
        if (plan.length == 0 || plan[0] == null || plan[0].length == 0) {
            throw new IllegalArgumentException("Floor plan cannot be empty.");
        }

        int rows = plan.length;
        int cols = plan[0].length;
        int[][] dist = new int[rows][cols];

        // Initialize all distances to -1
        for (int r = 0; r < rows; r++) {
            if (plan[r] == null || plan[r].length != cols) {
                throw new IllegalArgumentException("Floor plan must be a uniform rectangular grid.");
            }
            for (int c = 0; c < cols; c++) {
                dist[r][c] = -1;
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        // Multi-source initialization: enqueue all exits with distance 0
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        // Parallel BFS expansion
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int currentDist = dist[r][c];

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check bounds
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    // Only traverse unvisited open rooms
                    if (plan[nr][nc] == '.' && dist[nr][nc] == -1) {
                        dist[nr][nc] = currentDist + 1;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return dist;
    }
}
