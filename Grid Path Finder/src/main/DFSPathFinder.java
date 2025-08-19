package main;

import java.util.List;
import java.util.ArrayList;

public final class DFSPathFinder implements PathFinder{
    @Override
    public List<int[]> findPath(int[][] grid, int startRow, int startCol, int goalRow, int goalCol){
        int height = grid.length, width = grid[0].length;
        boolean[][] visited = new boolean[height][width];
        List<int[]> path = new ArrayList<>();
        boolean found = dfs(grid, startRow, startCol, goalRow, goalCol, visited, path);
        return found ? path : null;
    }

    private boolean dfs(int[][] grid, int row, int col, int goalRow, int goalCol, boolean[][] visited, List<int[]> path){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 1 ||visited[row][col]) return false;

        visited[row][col] = true;
        path.add(new int[] {row, col});
        if(row == goalRow && col == goalCol) return true;

        int[] deltaRow = {1, -1, 0, 0};
        int[] deltaCol = {0, 0, 1, -1};

        for(int i = 0; i < 4; i++){
            if (dfs(grid, row + deltaRow[i], col + deltaCol[i], goalRow, goalCol, visited, path)) return true;
        }

        path.remove(path.size() - 1); // backtrack for deadend;
        return false;
    }


}
