package assignments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GalacticE_Jumpers extends JPanel implements KeyListener {
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SPACESHIP_SIZE = 40;
    public static final int OBSTACLE_SIZE = 20;
    public static final int INITIAL_TIMER_DELAY = 10;
    public static final int MAX_LEVEL = 5;
    public static final int[] LEVEL_SPEEDS = {2, 3, 4, 5, 6, 100};
    public static final int[] SPAWN_INTERVALS = {3000, 3500, 4000, 4500, 5000, 1000};

    private int spaceshipX = WIDTH / 2 - SPACESHIP_SIZE / 2;
    private int spaceshipY = HEIGHT / 2 - SPACESHIP_SIZE / 2;
    private int score = 0;
    private int level = 1;
    private boolean isRunning = false;
    private Timer timer;
    private List<Point> obstacles;
    private List<Point> stars;
    private Random random;
    private JButton startButton;
    private JButton retryButton;

    public GalacticE_Jumpers() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initialize();
        createButtons();
    }

    private void initialize() {
        obstacles = new ArrayList<>();
        stars = new ArrayList<>();
        random = new Random();
        generateStars();
        timer = new Timer(INITIAL_TIMER_DELAY, e -> {
            moveObstacles();
            checkCollisions();
            repaint();
        });
    }

    private void createButtons() {
        startButton = new JButton("Start");
        startButton.setBounds(WIDTH / 2 - 50, HEIGHT / 2 - 15, 100, 30);
        startButton.addActionListener(e -> startGame());
        add(startButton);

        retryButton = new JButton("Retry");
        retryButton.setBounds(WIDTH / 2 - 50, HEIGHT / 2 - 15, 100, 30);
        retryButton.addActionListener(e -> retryGame());
        retryButton.setVisible(false);
        add(retryButton);
    }

    public void startGame() {
        isRunning = true;
        obstacles.clear();
        score = 0;
        level = 1;
        spaceshipX = WIDTH / 2 - SPACESHIP_SIZE / 2;
        spaceshipY = HEIGHT / 2 - SPACESHIP_SIZE / 2;
        timer.setDelay(INITIAL_TIMER_DELAY);
        timer.start();
        startButton.setVisible(false);
        retryButton.setVisible(false);
        requestFocusInWindow();
    }

    private void endGame() {
        isRunning = false;
        timer.stop();
        retryButton.setVisible(true);
    }

    private void retryGame() {
        startGame();
    }

    private void generateStars() {
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            stars.add(new Point(x, y));
        }
    }

    private void moveObstacles() {
        for (int i = 0; i < obstacles.size(); i++) {
            Point obstacle = obstacles.get(i);
            obstacle.y += LEVEL_SPEEDS[level - 1];
            if (obstacle.y > HEIGHT) {
                obstacles.remove(i);
                i--;
                score += 10;
            }
        }

        for (int i = 0; i < stars.size(); i++) {
            Point star = stars.get(i);
            star.y += LEVEL_SPEEDS[level - 1];
            if (star.y > HEIGHT) {
                star.y = 0;
                star.x = random.nextInt(WIDTH);
            }
        }

        if (score >= level * 100 && level < MAX_LEVEL) {
            level++;
            timer.setDelay(timer.getDelay() - 1);
        }
    }

    private void checkCollisions() {
        Rectangle spaceshipBounds = new Rectangle(spaceshipX, spaceshipY, SPACESHIP_SIZE, SPACESHIP_SIZE);
        for (Point obstacle : obstacles) {
            Rectangle obstacleBounds = new Rectangle(obstacle.x, obstacle.y, OBSTACLE_SIZE, OBSTACLE_SIZE);
            if (spaceshipBounds.intersects(obstacleBounds)) {
                endGame();
                JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
                return;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        if (isRunning) {
            drawSpaceship(g);
            drawObstacles(g);
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        for (Point star : stars) {
            g.fillRect(star.x, star.y, 2, 2);
        }
    }

    private void drawSpaceship(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(spaceshipX, spaceshipY, SPACESHIP_SIZE, SPACESHIP_SIZE);
    }

    private void drawObstacles(Graphics g) {
        g.setColor(Color.RED);
        for (Point obstacle : obstacles) {
            g.fillRect(obstacle.x, obstacle.y, OBSTACLE_SIZE, OBSTACLE_SIZE);
        }
    }

    public void spawnObstacle() {
        obstacles.add(new Point(random.nextInt(WIDTH - OBSTACLE_SIZE), 0));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            spaceshipX -= 5;
            if (spaceshipX < 0) {
                spaceshipX = 0;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            spaceshipX += 5;
            if (spaceshipX + SPACESHIP_SIZE > WIDTH) {
                spaceshipX = WIDTH - SPACESHIP_SIZE;
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            spaceshipY -= 5;
            if (spaceshipY < 0) {
                spaceshipY = 0;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            spaceshipY += 5;
            if (spaceshipY + SPACESHIP_SIZE > HEIGHT) {
                spaceshipY = HEIGHT - SPACESHIP_SIZE;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public List<Point> getObstacles() {
        return obstacles;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
