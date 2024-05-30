package assignments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
	private static final int SIZE = 3;
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board = new char[SIZE][SIZE];
    private char currentPlayer = PLAYER_X;
    private JButton[][] buttons;
    private JButton singlePlayerButton;
    private JButton twoPlayerButton;
    private boolean isSinglePlayerMode = false;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        singlePlayerButton = new JButton("Single Player");
        singlePlayerButton.addActionListener(this);
        add(singlePlayerButton);

        twoPlayerButton = new JButton("Two Player");
        twoPlayerButton.addActionListener(this);
        add(twoPlayerButton);

        setVisible(true);
    }

    private void startGame(boolean singlePlayerMode) {
        getContentPane().removeAll();
        setLayout(new GridLayout(SIZE, SIZE));

        buttons = new JButton[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        initializeBoard();
        updateButtons();

        setVisible(true);
        isSinglePlayerMode = singlePlayerMode;
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void updateButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText(Character.toString(board[i][j]));
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Check rows
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Check columns
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Check diagonal \
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Check diagonal /
        }
        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button == singlePlayerButton) {
            startGame(true);
        } else if (button == twoPlayerButton) {
            startGame(false);
        } else {
            int row = -1, col = -1;

            // Player's move
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (button.equals(buttons[i][j])) {
                        row = i;
                        col = j;
                        break;
                    }
                }
            }

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                updateButtons();
                if (checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(this, "It's a draw!");
                    resetGame();
                } else if (!isSinglePlayerMode) {
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                } else {
                    computerMove();
                    updateButtons();
                    if (checkWin(PLAYER_O)) {
                        JOptionPane.showMessageDialog(this, "Computer wins!");
                        resetGame();
                    } else if (checkDraw()) {
                        JOptionPane.showMessageDialog(this, "It's a draw!");
                        resetGame();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid move, please try again.");
            }
        }
    }

    private void computerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(SIZE);
            col = random.nextInt(SIZE);
        } while (!isValidMove(row, col));
        board[row][col] = PLAYER_O;
    }

    private void resetGame() {
        initializeBoard();
        updateButtons();
        currentPlayer = PLAYER_X;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
