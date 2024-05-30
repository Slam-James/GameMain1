package assignments;

import javax.swing.JPanel;
import java.util.Scanner;
public class SimpleChessGame extends JPanel {
    // Your SimpleChessGame class implementation


    private static final long serialVersionUID = 1L;
	private static final int BOARD_SIZE = 8;
    private static final char EMPTY_SQUARE = '-';
    private static final char WHITE_PAWN = 'P';
    private static final char BLACK_PAWN = 'p';
    private static final char WHITE_ROOK = 'R';
    private static final char BLACK_ROOK = 'r';
    private static final char WHITE_KNIGHT = 'N';
    private static final char BLACK_KNIGHT = 'n';
    private static final char WHITE_BISHOP = 'B';
    private static final char BLACK_BISHOP = 'b';
    private static final char WHITE_QUEEN = 'Q';
    private static final char BLACK_QUEEN = 'q';
    private static final char WHITE_KING = 'K';
    private static final char BLACK_KING = 'k';

    private char[][] board;

    public SimpleChessGame() {
        initializeBoard();
    }

    private void initializeBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];

        // Place pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[1][i] = WHITE_PAWN;
            board[6][i] = BLACK_PAWN;
        }

        // Place rooks
        board[0][0] = WHITE_ROOK;
        board[0][7] = WHITE_ROOK;
        board[7][0] = BLACK_ROOK;
        board[7][7] = BLACK_ROOK;

        // Place knights
        board[0][1] = WHITE_KNIGHT;
        board[0][6] = WHITE_KNIGHT;
        board[7][1] = BLACK_KNIGHT;
        board[7][6] = BLACK_KNIGHT;

        // Place bishops
        board[0][2] = WHITE_BISHOP;
        board[0][5] = WHITE_BISHOP;
        board[7][2] = BLACK_BISHOP;
        board[7][5] = BLACK_BISHOP;

        // Place queens
        board[0][3] = WHITE_QUEEN;
        board[7][3] = BLACK_QUEEN;

        // Place kings
        board[0][4] = WHITE_KING;
        board[7][4] = BLACK_KING;

        // Initialize empty squares
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_SQUARE;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int startX, int startY, int endX, int endY) {
        // Implement your move validation logic here
        // For now, we'll just return true for any move
        return true;
    }

    private void movePiece(int startX, int startY, int endX, int endY) {
        char piece = board[startY][startX];
        board[startY][startX] = EMPTY_SQUARE;
        board[endY][endX] = piece;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Simple Chess!");

        while (true) {
            System.out.println("Current Board:");
            printBoard();

            System.out.println("Enter your move (e.g., a2a4): ");
            String move = scanner.nextLine();

            // Convert user input to board indices
            int startX = move.charAt(0) - 'a';
            int startY = BOARD_SIZE - Character.getNumericValue(move.charAt(1));
            int endX = move.charAt(2) - 'a';
            int endY = BOARD_SIZE - Character.getNumericValue(move.charAt(3));

            if (isValidMove(startX, startY, endX, endY)) {
                movePiece(startX, startY, endX, endY);
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        SimpleChessGame game = new SimpleChessGame();
        game.play();
    }
}
