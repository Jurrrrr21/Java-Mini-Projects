import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Board {
    private final char[] cells;

    public Board() {
        this.cells = new char[9];
        Arrays.fill(this.cells, ' ');
    }

    public Board(char[] cells) {
        if (cells.length != 9) throw new IllegalArgumentException("Board must have 9 cells");
        this.cells = Arrays.copyOf(cells, 9);
    }

    public char getCell(int idx) {
        return cells[idx];
    }

    public boolean makeMove(int idx, char player) {
        if (idx < 0 || idx >= 9) return false;
        if (cells[idx] != ' ') return false;
        cells[idx] = player;
        return true;
    }

    public void undoMove(int idx) {
        cells[idx] = ' ';
    }

    public List<Integer> availableMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (cells[i] == ' ') moves.add(i);
        }
        return moves;
    }

    public boolean isFull() {
        for (char c : cells) if (c == ' ') return false;
        return true;
    }

    public char winner() {
        int[][] lines = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] line : lines) {
            char a = cells[line[0]];
            if (a != ' ' && a == cells[line[1]] && a == cells[line[2]]) {
                return a;
            }
        }
        if (isFull()) return 'D'; // Draw
        return ' ';
    }

    public Board copy() {
        return new Board(this.cells);
    }
}