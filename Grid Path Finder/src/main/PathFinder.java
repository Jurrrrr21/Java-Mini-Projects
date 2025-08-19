package main;
import java.util.*;

/*
 * Grid path finder interface
 * return path as list of {row, col} from start to goal or null if none
 */


public interface PathFinder {
    List<int[]> findPath(int[][] grid, int startRow, int startCol, int goalRow, int goalCol);
}
