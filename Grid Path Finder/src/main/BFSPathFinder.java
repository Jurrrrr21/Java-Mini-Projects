package main;

import java.util.*;

/**
 * Breadth-First Search (BFS) path finder.
 * Finds a shortest path (by steps) if one exists.
 * Grid semantics: 0 = open, 1 = wall. Movement: 4-directional.
 */
public final class BFSPathFinder implements PathFinder {

    private static final int[][] DIRS = { {1,0}, {-1,0}, {0,1}, {0,-1} };

    @Override
    public List<int[]> findPath(int[][] grid, int startRow, int startCol, int goalRow, int goalCol) {
        final int height = grid.length;
        final int width  = grid[0].length;

        boolean[][] visited = new boolean[height][width];
        int[][] parentRow = new int[height][width];
        int[][] parentCol = new int[height][width];
        for (int[] r : parentRow) Arrays.fill(r, -1);
        for (int[] r : parentCol) Arrays.fill(r, -1);

        if (!isOpen(grid, startRow, startCol)) return null;

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1];

            if (row == goalRow && col == goalCol) {                return reconstructPath(goalRow, goalCol, parentRow, parentCol);
            }

            for (int[] d : DIRS) {
                int nextRow = row + d[0], nextCol = col + d[1];

                if (!inBounds(nextRow, nextCol, height, width)) continue;

                if (!visited[nextRow][nextCol] && isOpen(grid, nextRow, nextCol)) {
                    visited[nextRow][nextCol] = true;
                    parentRow[nextRow][nextCol] = row;
                    parentCol[nextRow][nextCol] = col;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }

        // Goal was never reached
        return null;
    }

    private boolean inBounds(int r, int c, int height, int width) {
        // Use >= for upper bounds; never index arrays before this check
        return r >= 0 && c >= 0 && r < height && c < width;
    }

    private boolean isOpen(int[][] grid, int r, int c) {
        // 0 = open, 1 = wall
        return grid[r][c] == 0;
    }

    private List<int[]> reconstructPath(int goalRow, int goalCol, int[][] parentRow, int[][] parentCol) {
        List<int[]> path = new ArrayList<>();
        int r = goalRow, c = goalCol;

        // Walk back until we hit (-1, -1) parents (the start's parent is -1)
        while (r != -1 && c != -1) {
            path.add(new int[]{r, c});
            int pr = parentRow[r][c];
            int pc = parentCol[r][c];
            r = pr;
            c = pc;
        }

        Collections.reverse(path); // start -> goal
        return path;
    }
}
