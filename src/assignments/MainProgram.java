package assignments;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;

public class MainProgram {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Arcade");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());

                JButton moneyGameButton = new JButton("Money Game");
                moneyGameButton.setBounds(50, 162, 141, 36);
                moneyGameButton.addActionListener(e -> {
                    MoneyGame moneyGame = new MoneyGame();
                    Thread moneyGameThread = new Thread(moneyGame);
                    moneyGameThread.start();
                });

                JButton galacticJumpersButton = new JButton("Galactic Jumpers");
                galacticJumpersButton.setBounds(50, 209, 141, 36);
                galacticJumpersButton.addActionListener(e -> {
                    JFrame gameFrame = new JFrame("Galactic Jumpers");
                    GalacticE_Jumpers game = new GalacticE_Jumpers();
                    gameFrame.getContentPane().add(game);
                    gameFrame.pack();
                    gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    gameFrame.setLocationRelativeTo(null);
                    gameFrame.setVisible(true);
                    game.startGame();

                    Timer obstacleTimer = new Timer(3000, event -> {
                        if (game.isRunning() && game.getObstacles().size() < 5) {
                            game.spawnObstacle();
                        }
                    });
                    obstacleTimer.start();
                });

                JButton hangmanButton = new JButton("Hangman");
                hangmanButton.setBounds(50, 256, 141, 36);
                hangmanButton.addActionListener(e -> {
                    JFrame gameFrame = new JFrame("Hangman Game");
                    HangmanGameGUI game = new HangmanGameGUI();
                    gameFrame.getContentPane().add(game);
                    gameFrame.pack();
                    gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    gameFrame.setLocationRelativeTo(null);
                    gameFrame.setVisible(true);
                });

                JButton chessButton = new JButton("Simple Chess");
                chessButton.setBounds(50, 68, 141, 36);
                chessButton.addActionListener(e -> {
                    ChessGameFrame chessGame = new ChessGameFrame();
                    chessGame.setVisible(true);
                });

                JButton ticTacToeButton = new JButton("Tic Tac Toe");
                ticTacToeButton.setBounds(50, 115, 141, 36);
                ticTacToeButton.addActionListener(e -> {
                    TicTacToe ticTacToe = new TicTacToe();
                    ticTacToe.setVisible(true);
                });

                JPanel panel = new JPanel();
                panel.setBackground(new Color(0, 0, 0));
                panel.setLayout(null);
                panel.add(galacticJumpersButton);
                panel.add(hangmanButton);
                panel.add(moneyGameButton);
                panel.add(chessButton);
                panel.add(ticTacToeButton);

                frame.getContentPane().add(panel, BorderLayout.CENTER);
                frame.setSize(550, 410);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
