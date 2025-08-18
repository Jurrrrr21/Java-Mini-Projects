public class AI {

    public static int bestMove(Board board, char aiPlayer) {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;
        int depth = 0;
        for (int m : board.availableMoves()) {
            board.makeMove(m, aiPlayer);
            int score = minimax(board, depth + 1, false, aiPlayer, other(aiPlayer),
                                Integer.MIN_VALUE, Integer.MAX_VALUE);
            board.undoMove(m);
            if (score > bestScore) {
                bestScore = score;
                move = m;
            }
        }
        return move;
    }

    private static int minimax(Board board, int depth, boolean isMaximizing,
                               char maxPlayer, char minPlayer, int alpha, int beta) {
        char result = board.winner();
        if (result == maxPlayer) return 10 - depth; // prefer faster wins
        if (result == minPlayer) return depth - 10; // prefer slower losses
        if (result == 'D') return 0;                // draw

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int move : board.availableMoves()) {
                board.makeMove(move, maxPlayer);
                int val = minimax(board, depth + 1, false, maxPlayer, minPlayer, alpha, beta);
                board.undoMove(move);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);
                if (beta <= alpha) break; // beta cut-off
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int move : board.availableMoves()) {
                board.makeMove(move, minPlayer);
                int val = minimax(board, depth + 1, true, maxPlayer, minPlayer, alpha, beta);
                board.undoMove(move);
                best = Math.min(best, val);
                beta = Math.min(beta, best);
                if (beta <= alpha) break; // alpha cut-off
            }
            return best;
        }
    }

    private static char other(char p) {
        return p == 'X' ? 'O' : 'X';
    }
}