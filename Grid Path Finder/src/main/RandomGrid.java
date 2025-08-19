package main;
import java.util.Random;

public final class RandomGrid {
    private RandomGrid(){}
    
    /*
     * height = number of rows
     * width = number of cols
     * wallDensity = probability that a given cell is a wall
     * see = random seed, -1 for randomness, if controlled, change seed in GridPathFinder to controlled num
     * 1 = Wall, 0 = path
     */
    public static int[][] generate(int height, int width, double wallDensity, long seed){
        Random random = (seed != -1) ? new Random(seed) : new Random();
        int[][] grid = new int[height][width];

        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                if(random.nextDouble() < wallDensity){
                    grid[r][c] = 1;
                } else {
                    grid[r][c] = 0;
                }
            }
        }

        grid[0][0] = 0; //Starting position
        grid[height - 1][width - 1] = 0; // End goal
        
        return grid;
    }
}
