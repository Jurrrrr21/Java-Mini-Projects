package main;
import java.util.*;

public final class GridRender {
    private GridRender(){}


    public static void printGrid(int[][] grid, int startRow, int startCol, int goalRow, int goalCol, Set<Long> dfsPathCells, Set<Long> bfsPathCells){
        int height = grid.length, width = grid[0].length;

        for(int row = 0; row < height; row++){
            StringBuilder line = new StringBuilder();
            for(int col = 0; col < width; col++){
                char c;
                if (row == startRow && col == startCol) c = 'S';
                else if (row == goalRow && col == goalCol) c = 'G';
                else if (grid[row][col] == 1) c = '#';
                else c = ' ';

                long key = encodeKey(row, col);
                if(dfsPathCells != null && dfsPathCells.contains(key) && c == ' ') c = 'd';
                if(bfsPathCells != null && bfsPathCells.contains(key) && c == ' ') c = 'b';
                
                line.append(c).append(' ');
            }
            System.out.println(line.toString());
        }
    }

    public static void printOverlay(int[][] grid, int startRow, int startCol, int goalRow, int goalCol, Set<Long> dfsPathCells, Set<Long> bfsPathCells){
        int height = grid.length, width = grid[0].length;

        for(int row = 0; row < height; row++){
            StringBuilder line = new StringBuilder();
            for(int col = 0; col < width; col++){
                char c = (grid[row][col] == 1) ? '#' : ' ';
                long key = encodeKey(row, col);

                boolean onDFS = dfsPathCells != null && dfsPathCells.contains(key);
                boolean onBFS = bfsPathCells != null && bfsPathCells.contains(key);

                if (row == startRow && col == startCol) c = 'S';
                else if (row == goalRow && col == goalCol) c = 'G';
                else if (onDFS && onBFS) c = '*';
                else if(onDFS) c = 'd';
                else if (onBFS) c = 'b';

                line.append(c).append(' ');
            }
            System.out.println(line.toString());
        }
    }
    
    public static int pathLength(List<int[]> path){
        return (path == null) ? -1 : path.size() -1;
    }

    public static Set<Long> toCellSet(List<int[]> path){
        if (path == null) return null;
        Set<Long> set = new HashSet<>();
        for(int[] cell : path){
            set.add(encodeKey(cell[0], cell[1]));
        }
        return set;
    }
    
    public static long encodeKey(int row, int col){
        return (((long) row) << 32) | (col & 0xffffffffL);
    }



}
