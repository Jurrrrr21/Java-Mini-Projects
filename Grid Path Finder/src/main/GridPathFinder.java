package main;

import java.util.*;

public class GridPathFinder {
    public static void main(String[] args){
        int height = 10;
        int width = 15;
        double wallDensity = 0.3;
        long seed = -1;

        int[][] grid = RandomGrid.generate(height, width, wallDensity, seed);

        int startRow = 0, startCol = 0;
        int goalRow = height - 1, goalCol = width - 1;
        
        PathFinder dfsFinder = new DFSPathFinder();
        PathFinder bfsFinder = new BFSPathFinder();

        List<int[]> dfsPath = dfsFinder.findPath(grid, startRow, startCol, goalRow, goalCol);
        List<int[]> bfsPath = bfsFinder.findPath(grid, startRow, startCol, goalRow, goalCol);
    
        System.out.println("Generated grid (S = start, G = goal):");
        System.out.println();
        GridRender.printGrid(grid, startRow, startCol, goalRow, goalCol, null, null);

        System.out.println();
        System.out.println("DFS path length = " + GridRender.pathLength(dfsPath));
        GridRender.printGrid(grid, startRow, startCol, goalRow, goalCol, GridRender.toCellSet(dfsPath), null);

        System.out.println();
        System.out.println("BFS path length = " + GridRender.pathLength(bfsPath));
        GridRender.printGrid(grid, startRow, startCol, goalRow, goalCol, null, GridRender.toCellSet(bfsPath));

        System.out.println();
        System.out.println("Overlay Comparison");
        GridRender.printOverlay(grid, startRow, startCol, goalRow, goalCol, GridRender.toCellSet(dfsPath), GridRender.toCellSet(bfsPath));
    }
}
