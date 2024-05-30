package assignments;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessGameFrame extends JFrame {

    private JPanel chessboardPanel;
    private JLabel[][] chessboardSquares;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JPanel capturedPiecesPanel;
    private JLabel[] whiteCapturedPieces;
    private JLabel[] blackCapturedPieces;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public ChessGameFrame() {
        setTitle("Simple Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void initComponents() {
        JPanel contentPane = new JPanel(new BorderLayout(5, 5));
        setContentPane(contentPane);

        chessboardPanel = new JPanel(new GridLayout(8, 8));
        chessboardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.add(chessboardPanel, BorderLayout.CENTER);

        statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        contentPane.add(statusPanel, BorderLayout.SOUTH);

        statusLabel = new JLabel("Welcome to Simple Chess Game");
        statusPanel.add(statusLabel);

        capturedPiecesPanel = new JPanel(new GridLayout(2, 8));
        capturedPiecesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Captured Pieces"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        contentPane.add(capturedPiecesPanel, BorderLayout.NORTH);

        whiteCapturedPieces = new JLabel[8];
        blackCapturedPieces = new JLabel[8];
        for (int i = 0; i < 8; i++) {
            whiteCapturedPieces[i] = new JLabel();
            whiteCapturedPieces[i].setFont(new Font("Arial", Font.PLAIN, 30));
            capturedPiecesPanel.add(whiteCapturedPieces[i]);

            blackCapturedPieces[i] = new JLabel();
            blackCapturedPieces[i].setFont(new Font("Arial", Font.PLAIN, 30));
            capturedPiecesPanel.add(blackCapturedPieces[i]);
        }

        initializeChessboard();
    }

    private void initializeChessboard() {
        chessboardSquares = new JLabel[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JLabel square = new JLabel();
                square.setOpaque(true);
                square.setPreferredSize(new Dimension(60, 60));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                chessboardSquares[row][col] = square;
                square.addMouseListener(new SquareClickListener(row, col));
                chessboardPanel.add(square);

                // Alternate square colors
                if ((row + col) % 2 == 0) {
                    square.setBackground(new Color(240, 217, 181)); // Light square
                } else {
                    square.setBackground(new Color(181, 136, 99)); // Dark square
                }
            }
        }

        // Add chess pieces using Unicode characters
        String[][] pieces = {
                {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"},
                {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"},
                {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"}
        };

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (!pieces[row][col].isEmpty()) {
                    chessboardSquares[row][col].setText(pieces[row][col]);
                }
            }
        }
    }

    private class SquareClickListener extends MouseAdapter {
        private int row;
        private int col;

        public SquareClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (selectedRow == -1 && selectedCol == -1) {
                // No piece selected, select the clicked square if it has a piece
                if (!chessboardSquares[row][col].getText().isEmpty()) {
                    selectedRow = row;
                    selectedCol = col;
                    chessboardSquares[row][col].setBackground(Color.YELLOW);
                }
            } else {
                // Move the selected piece to the clicked square
                movePiece(selectedRow, selectedCol, row, col);
                selectedRow = -1;
                selectedCol = -1;
                resetSquareColors();
            }
        }
    }

    private void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        String piece = chessboardSquares[fromRow][fromCol].getText();
        chessboardSquares[toRow][toCol].setText(piece);
        chessboardSquares[fromRow][fromCol].setText("");
    }

    private void resetSquareColors() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    chessboardSquares[row][col].setBackground(new Color(240, 217, 181)); // Light square
                } else {
                    chessboardSquares[row][col].setBackground(new Color(181, 136, 99)); // Dark square
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame frame = new ChessGameFrame();
            frame.setVisible(true);
        });
    }
}
