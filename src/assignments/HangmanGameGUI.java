package assignments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGameGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private String[] words = {"hangman", "java", "programming", "computer", "coding"};
    private String word;
    private StringBuilder guessedWord;
    private int attemptsLeft;

    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JTextField inputField;
    private JButton guessButton;
    private JButton solveButton;

    public HangmanGameGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 200));

        // Select a random word from the list
        word = words[(int) (Math.random() * words.length)];
        guessedWord = new StringBuilder("-".repeat(word.length()));
        attemptsLeft = 10;

        // Word label setup
        wordLabel = new JLabel("Word: " + guessedWord.toString(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(wordLabel, BorderLayout.NORTH);

        // Attempts left label setup
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(attemptsLabel, BorderLayout.CENTER);

        // Input field setup
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(inputField, BorderLayout.SOUTH);

        // Guess button setup
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = inputField.getText();
                if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                    JOptionPane.showMessageDialog(null, "Please enter a single letter.");
                } else {
                    check(guess.charAt(0));
                    updateUIComponents();
                }
                inputField.setText("");
            }
        });
        add(guessButton, BorderLayout.EAST);

        // Solve button setup
        solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Arial", Font.BOLD, 16));
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String solve = inputField.getText();
                if (solve.equalsIgnoreCase(word)) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the word: " + word);
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry, that's not the correct word.");
                    attemptsLeft--;
                    updateUIComponents();
                    inputField.setText("");
                }
            }
        });
        add(solveButton, BorderLayout.WEST);

        // Make the panel visible
        setVisible(true);
    }

    // Update the UI components with the current game state
    private void updateUIComponents() {
        wordLabel.setText("Word: " + guessedWord.toString());
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
    }

    // Check if the guessed letter is correct and update the game state
    private void check(char guess) {
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord.setCharAt(i, guess);
                found = true;
            }
        }
        if (!found) {
            attemptsLeft--;
            if (attemptsLeft == 0) {
                JOptionPane.showMessageDialog(null, "Sorry, you've run out of attempts. The word was: " + word);
                System.exit(0);
            }
        }
        if (guessedWord.indexOf("-") == -1) {
            JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the word: " + word);
            System.exit(0);
        }
    }
}

