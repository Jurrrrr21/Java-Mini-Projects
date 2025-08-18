import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tic-Tac-Toe (Minimax + Alpha-Beta) ===");
        Scanner sc = new Scanner(System.in);

        char human = choosePiece(sc);
        char ai = (human == 'X') ? 'O' : 'X';
        System.out.printf("You are %c, AI is %c.%n", human, ai);

        Board board = new Board();
        char current = 'X'; // X always starts

        while (true) {
            printBoard(board, true);
            char result = board.winner();
            if (result != ' ') {
                System.out.println("\nGame over!");
                printBoard(board, false);
                if (result == 'D') System.out.println("It's a draw.");
                else if (result == human) System.out.println("🎉 You win!");
                else System.out.println("🤖 AI wins!");
                break;
            }

            if (current == human) {
                int move = askHumanMove(sc, board);
                board.makeMove(move, human);
            } else {
                int move = AI.bestMove(board, ai);
                board.makeMove(move, ai);
                System.out.printf("%nAI plays at %d%n", move + 1);
            }
            current = (current == 'X') ? 'O' : 'X';
        }
    }

    private static char choosePiece(Scanner sc) {
        while (true) {
            System.out.print("Choose your piece (X goes first) [X/O]: ");
            String in = sc.nextLine().trim().toUpperCase();
            if (in.equals("X") || in.equals("O")) {
                return in.charAt(0);
            }
            System.out.println("Please type X or O.");
        }
    }

    private static int askHumanMove(Scanner sc, Board board) {
        while (true) {
            try {
                System.out.print("Your move (1-9): ");
                String raw = sc.nextLine().trim();
                int pos = Integer.parseInt(raw) - 1;
                if (pos >= 0 && pos < 9 && board.getCell(pos) == ' ') {
                    return pos;
                } else {
                    System.out.println("Invalid move. Pick an empty cell number 1-9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number 1-9.");
            }
        }
    }

    private static void printBoard(Board board, boolean showNumbersForEmpty) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            char c = board.getCell(i);
            if (c == ' ' && showNumbersForEmpty) {
                sb.append(' ').append(i + 1).append(' ');
            } else {
                sb.append(' ').append(c == ' ' ? ' ' : c).append(' ');
            }
            if (i % 3 != 2) sb.append("|");
            if (i % 3 == 2 && i != 8) sb.append("\n---+---+---\n");
        }
        System.out.println("\n" + sb);
    }
}