import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AITests {

    @Test
    void centerIsGoodOpeningForO() {
        // X plays corner (0). Best reply for O is center (4).
        Board b = new Board();
        b.makeMove(0, 'X');
        int move = AI.bestMove(b, 'O');
        assertEquals(4, move, "O should take the center");
    }

    @Test
    void blocksImmediateWin() {
        // X at 0 and 1, O must block at 2
        Board b = new Board();
        b.makeMove(0, 'X');
        b.makeMove(1, 'X');
        b.makeMove(4, 'O'); // arbitrary
        int move = AI.bestMove(b, 'O');
        assertEquals(2, move, "O must block at 2");
    }

    @Test
    void takesImmediateWin() {
        // O can win at 8 (6,7 already O)
        Board b = new Board();
        b.makeMove(6, 'O');
        b.makeMove(7, 'O');
        b.makeMove(0, 'X'); // filler
        int move = AI.bestMove(b, 'O');
        assertEquals(8, move, "O should complete the row at 8");
    }
}