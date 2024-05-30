package assignments;

import javax.swing.ImageIcon;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MoneyGame extends JPanel implements Runnable {

	public void run1() {
		
	}
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// Variables
public static int totalMoney = 0;
public static int timeBetweenTicks = 10000;
public static int amountOfAscensions = 1;
int amountPerClick = 1;
int buttonImage1 = 2;
int upgradeMenu = 2;
int reduceTime = 200;
int playingGame = 1;
int reduceTimeCost = 50;
int checkIfMaxUpgrade = 1;
double displayTimeBetweenTicks = 10;

public static JLabel lblNewLabel3 = new JLabel("$0");

// Array 1
public static int[] amountPerTick = {0, 0, 0, 0, 0};

// Array 2
int[] upgradeCost = {25, 50, 40, 500, 250, 5000, 2500, 50000, 25000, 500000, 250000, 5000000};

// Array 3
int[] upgradeCostIncrease = {15, 25, 20, 100, 50, 1000, 500, 10000, 5000, 100000, 50000, 1000000};

// Array 4
int[] upgradeMoneyIncrease = {1, 10, 100, 1000, 10000, 100000};

// Variables for images
ImageIcon background1 = new ImageIcon(getClass().getResource("/resources/Background (1).jpg"));
ImageIcon background2 = new ImageIcon(getClass().getResource("/resources/MoneyBackground (1).jpg"));
ImageIcon button1 = new ImageIcon(getClass().getResource("/resources/MoneyButton.png"));
ImageIcon button2 = new ImageIcon(getClass().getResource("/resources/MoneyButton1.png"));
ImageIcon upgradeIcon = new ImageIcon(getClass().getResource("/resources/UpgradesIcon.png"));
ImageIcon woodenPickaxe = new ImageIcon(getClass().getResource("/resources/WoodenPickaxe (1).png"));
ImageIcon stonePickaxe = new ImageIcon(getClass().getResource("/resources/StonePickaxe (1).png"));
ImageIcon ironPickaxe = new ImageIcon(getClass().getResource("/resources/IronPickaxe (1).png"));
ImageIcon goldenPickaxe = new ImageIcon(getClass().getResource("/resources/GoldenPickaxe (1).png"));
ImageIcon diamondPickaxe = new ImageIcon(getClass().getResource("/resources/DiamondPickaxe (1).png"));
ImageIcon mouseIcon = new ImageIcon(getClass().getResource("/resources/MouseIcon (1).png"));
ImageIcon woodenPickaxe1 = new ImageIcon(getClass().getResource("/resources/WoodenPickaxe1.png"));
ImageIcon stonePickaxe1 = new ImageIcon(getClass().getResource("/resources/StonePickaxe1.png"));
ImageIcon ironPickaxe1 = new ImageIcon(getClass().getResource("/resources/IronPickaxe1 (1).png"));
ImageIcon goldenPickaxe1 = new ImageIcon(getClass().getResource("/resources/GoldenPickaxe1 (1).png"));
ImageIcon diamondPickaxe1 = new ImageIcon(getClass().getResource("/resources/DiamondPickaxe1 (1).png"));
ImageIcon ascension = new ImageIcon(getClass().getResource("/resources/Ascension.png"));
ImageIcon timerIcon = new ImageIcon(getClass().getResource("/resources/ReduceTimerIcon.png"));

public JFrame frame;


/**
* Launch the application.
*/
public static void main(String[] args) {

	
// Thread
MoneyGame obj = new MoneyGame();
Thread foobar = new Thread(obj);

foobar.start();

EventQueue.invokeLater(new Runnable() {
public void run() {
try {
MoneyGame window = new MoneyGame();
window.frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

/**
* Create the application.
*/
public MoneyGame() {
initialize();
}

/**
* Initialize the contents of the frame.
*/
public void initialize() {
frame = new JFrame();
frame.getContentPane().setBackground(new Color(211, 211, 211));
frame.setBounds(100, 100, 789, 604);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(null);
frame.setVisible(true);

JButton btnNewButton4 = new JButton("EXIT GAME");
btnNewButton4.setForeground(Color.WHITE);
btnNewButton4.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
// Exit button code goes here
System.exit(0);
}
});
btnNewButton4.setBackground(new Color(255, 0, 0));
btnNewButton4.setFont(new Font("Trebuchet MS", Font.BOLD, 10));
btnNewButton4.setBounds(684, 0, 89, 59);
frame.getContentPane().add(btnNewButton4);

//JLabel lblNewLabel3 = new JLabel("$0");
lblNewLabel3.setFont(new Font("Malgun Gothic", Font.BOLD, 48));
lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel3.setForeground(Color.WHITE);
lblNewLabel3.setBounds(0, -3, 685, 61);
frame.getContentPane().add(lblNewLabel3);

// Money button code
JButton btnNewButton2 = new JButton("");
btnNewButton2.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (buttonImage1 == 1) {
btnNewButton2.setIcon(button1);
buttonImage1 = 2;
}

else if (buttonImage1 == 2) {
btnNewButton2.setIcon(button2);
buttonImage1 = 1;
}
totalMoney = addAmountPerClickToTotalMoney(totalMoney, amountPerClick, amountOfAscensions);
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
}
});

//Display picture upon launch for Money Button
btnNewButton2.setIcon(button1);

// Hide Money Button off of launch
btnNewButton2.hide();

btnNewButton2.setBounds(180, 387, 460, 160);
frame.getContentPane().add(btnNewButton2);

JPanel panel2 = new JPanel();
panel2.setBackground(new Color(192, 192, 192));
panel2.setBounds(10, 59, 637, 503);
frame.getContentPane().add(panel2);
panel2.setLayout(null);

JLabel lblNewLabel2_21 = new JLabel("Not enough money for upgrade!");
lblNewLabel2_21.setForeground(Color.RED);
lblNewLabel2_21.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel2_21.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_21.setBounds(0, 472, 637, 31);
panel2.add(lblNewLabel2_21);

// Hid this label off of launch
lblNewLabel2_21.hide();

// Hide upgrade panel off of launch
panel2.hide();

JButton btnNewButton3 = new JButton("");
btnNewButton3.setBackground(new Color(222, 184, 135));
btnNewButton3.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (upgradeMenu == 1) {
panel2.hide();
upgradeMenu = 2;
btnNewButton2.setVisible(true);
}

else if (upgradeMenu == 2) {
panel2.setVisible(true);
upgradeMenu = 1;
btnNewButton2.hide();
lblNewLabel2_21.setVisible(false);
}
}
});

// Display picture upon launch for Upgrade Icon
btnNewButton3.setIcon(upgradeIcon);

// Hide Upgrade Menu off of launch
btnNewButton3.hide();

JPanel panel1 = new JPanel();
panel1.setBackground(new Color(152, 251, 152));
panel1.setBounds(0, 69, 773, 485);
frame.getContentPane().add(panel1);
panel1.setLayout(null);

JLabel lblNewLabel1_1 = new JLabel("Welcome to a Simple Clicker game!");
lblNewLabel1_1.setFont(new Font("Malgun Gothic", Font.BOLD, 32));
lblNewLabel1_1.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel1_1.setBounds(88, 11, 594, 64);
panel1.add(lblNewLabel1_1);

JLabel lblNewLabel1_2 = new JLabel("How to play:");
lblNewLabel1_2.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel1_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
lblNewLabel1_2.setBounds(88, 75, 594, 64);
panel1.add(lblNewLabel1_2);

JLabel lblNewLabel1_3 = new JLabel("Click! Earn money! Buy upgrades! Ascend! And make more money!");
lblNewLabel1_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
lblNewLabel1_3.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel1_3.setBounds(88, 107, 594, 58);
panel1.add(lblNewLabel1_3);

JLabel lblNewLabel1_4 = new JLabel("And most of all...");
lblNewLabel1_4.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel1_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
lblNewLabel1_4.setBounds(98, 150, 584, 27);
panel1.add(lblNewLabel1_4);

JLabel lblNewLabel1_5 = new JLabel("CLICK! CLICK! CLICK!");
lblNewLabel1_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 48));
lblNewLabel1_5.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel1_5.setBounds(88, 176, 594, 47);
panel1.add(lblNewLabel1_5);

JButton btnNewButton1 = new JButton("Close tutorial and start clicking!");
btnNewButton1.addActionListener(new ActionListener() {
@SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e) {
panel1.hide();
btnNewButton2.setVisible(true);
btnNewButton3.setVisible(true);
}
});

btnNewButton1.setBackground(new Color(50, 205, 50));
btnNewButton1.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
btnNewButton1.setBounds(88, 234, 594, 209);
panel1.add(btnNewButton1);


JLabel lblNewLabel2_1 = new JLabel("UPGRADES");
lblNewLabel2_1.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel2_1.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
lblNewLabel2_1.setBounds(0, 0, 637, 41);
panel2.add(lblNewLabel2_1);

JLabel lblNewLabel2_2 = new JLabel("");
lblNewLabel2_2.setBounds(10, 42, 49, 41);
panel2.add(lblNewLabel2_2);

//Display picture upon launch for cursor Icon in upgrades menu
lblNewLabel2_2.setIcon(mouseIcon);

JLabel lblNewLabel2_3 = new JLabel("");
lblNewLabel2_3.setBounds(10, 101, 49, 41);
panel2.add(lblNewLabel2_3);

//Display picture upon launch for wooden pickaxe Icon in upgrades menu
lblNewLabel2_3.setIcon(woodenPickaxe1);

JLabel lblNewLabel2_4 = new JLabel("");
lblNewLabel2_4.setBounds(10, 163, 49, 41);
panel2.add(lblNewLabel2_4);

//Display picture upon launch for stone pickaxe Icon in upgrades menu
lblNewLabel2_4.setIcon(stonePickaxe1);

JLabel lblNewLabel2_5 = new JLabel("");
lblNewLabel2_5.setBounds(10, 233, 49, 41);
panel2.add(lblNewLabel2_5);

//Display picture upon launch for iron pickaxe Icon in upgrades menu
lblNewLabel2_5.setIcon(ironPickaxe1);

JLabel lblNewLabel2_6 = new JLabel("");
lblNewLabel2_6.setBounds(10, 302, 49, 41);
panel2.add(lblNewLabel2_6);

//Display picture upon launch for golden pickaxe Icon in upgrades menu
lblNewLabel2_6.setIcon(goldenPickaxe1);

JLabel lblNewLabel2_7 = new JLabel("");
lblNewLabel2_7.setBounds(10, 370, 49, 41);
panel2.add(lblNewLabel2_7);

//Display picture upon launch for diamond pickaxe Icon in upgrades menu
lblNewLabel2_7.setIcon(diamondPickaxe1);

JLabel lblNewLabel2_8 = new JLabel("");
lblNewLabel2_8.setBounds(10, 433, 49, 41);
panel2.add(lblNewLabel2_8);

//Display picture upon launch for Ascension Icon in upgrades menu
lblNewLabel2_8.setIcon(ascension);

JLabel lblNewLabel2_9 = new JLabel("- Increase amount per c lick");
lblNewLabel2_9.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_9.setBounds(193, 52, 271, 31);
panel2.add(lblNewLabel2_9);

JLabel lblNewLabel2_10 = new JLabel("- Increases amount per tick (wooden pick)");
lblNewLabel2_10.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_10.setBounds(193, 111, 303, 31);
panel2.add(lblNewLabel2_10);

JLabel lblNewLabel2_12 = new JLabel("- Increases amount per tick (stone pick)");
lblNewLabel2_12.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_12.setBounds(193, 173, 271, 31);
panel2.add(lblNewLabel2_12);

JLabel lblNewLabel2_14 = new JLabel("- Increases amount per tick (iron pick)");
lblNewLabel2_14.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_14.setBounds(193, 243, 271, 31);
panel2.add(lblNewLabel2_14);

JLabel lblNewLabel2_16 = new JLabel("- Increases amount per tick (golden pick)");
lblNewLabel2_16.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_16.setBounds(193, 312, 271, 31);
panel2.add(lblNewLabel2_16);

JLabel lblNewLabel2_18 = new JLabel("- Increases amount per tick (diamond pick)");
lblNewLabel2_18.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_18.setBounds(193, 380, 281, 31);
panel2.add(lblNewLabel2_18);

JLabel lblNewLabel2_20 = new JLabel("- Resets total money and all upgrades but doubles all income");
lblNewLabel2_20.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
lblNewLabel2_20.setBounds(193, 443, 415, 31);
panel2.add(lblNewLabel2_20);

JLabel lblNewLabel9 = new JLabel("");
lblNewLabel9.setBounds(602, 104, 159, 167);
frame.getContentPane().add(lblNewLabel9);

//Display picture upon launch for Diamond Pickaxe Icon
lblNewLabel9.setIcon(diamondPickaxe);
lblNewLabel9.hide();

JLabel lblNewLabel8 = new JLabel("");
lblNewLabel8.setBounds(420, 104, 159, 167);
frame.getContentPane().add(lblNewLabel8);

//Display picture upon launch for Golden Pickaxe Icon
lblNewLabel8.setIcon(goldenPickaxe);
lblNewLabel8.hide();

JLabel lblNewLabel7 = new JLabel("");
lblNewLabel7.setBounds(225, 104, 159, 167);
frame.getContentPane().add(lblNewLabel7);

//Display picture upon launch for Iron Pickaxe Icon
lblNewLabel7.setIcon(ironPickaxe);
lblNewLabel7.hide();

JLabel lblNewLabel6 = new JLabel("");
lblNewLabel6.setBounds(10, 104, 159, 168);
frame.getContentPane().add(lblNewLabel6);

//Display picture upon launch for Stone Pickaxe Icon
lblNewLabel6.setIcon(stonePickaxe);
lblNewLabel6.hide();

JLabel lblNewLabel5 = new JLabel("");
lblNewLabel5.setBounds(10, 364, 159, 168);
frame.getContentPane().add(lblNewLabel5);
btnNewButton3.setBounds(657, 449, 116, 116);
frame.getContentPane().add(btnNewButton3);

//Display picture upon launch for Wooden Pickaxe Icon
lblNewLabel5.setIcon(woodenPickaxe);
lblNewLabel5.hide();

// Upgrade 1
JButton btnNewButton4_1 = new JButton("");
btnNewButton4_1.setBackground(new Color(50, 205, 50));
btnNewButton4_1.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (totalMoney >= upgradeCost[0]) {

// Cost increase for upgrade
totalMoney = totalMoney - upgradeCost[0];
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
upgradeCost[0] = increaseUpgradeCost(upgradeCost[0], upgradeCostIncrease[0]);
upgradeCostIncrease[0] = upgradeCostIncrease[0] + 20;
btnNewButton4_1.setText("$" + Integer.toString(upgradeCost[0]));

// Bonus for upgrade
amountPerClick = upgradeBonus(amountPerClick, upgradeMoneyIncrease[0]);
upgradeMoneyIncrease[0] = upgradeMoneyIncrease[0] + 1;
}
// If player cannot afford upgrade
else if (totalMoney < upgradeCost[0]) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_1.setBounds(67, 52, 124, 31);
panel2.add(btnNewButton4_1);
btnNewButton4_1.setText("$" + Integer.toString(upgradeCost[0]));

JButton btnNewButton4_2 = new JButton("");
btnNewButton4_2.setBackground(new Color(50, 205, 50));
btnNewButton4_2.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (totalMoney >= upgradeCost[1]) {

// Cost increase for upgrade
lblNewLabel5.setVisible(true);
totalMoney = totalMoney - upgradeCost[1];
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
upgradeCost[1] = increaseUpgradeCost(upgradeCost[1], upgradeCostIncrease[1]);
upgradeCostIncrease[1] = upgradeCostIncrease[1] + 40;
btnNewButton4_2.setText("$" + Integer.toString(upgradeCost[1]));

// Bonus for upgrade
amountPerTick[0] = upgradeBonus(amountPerTick[0], upgradeMoneyIncrease[1]);
upgradeMoneyIncrease[1] = upgradeMoneyIncrease[1] + 5;
}
// If player cannot afford upgrade
else if (totalMoney < upgradeCost[1]) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_2.setBounds(66, 101, 124, 41);
panel2.add(btnNewButton4_2);
btnNewButton4_2.setText("$" + Integer.toString(upgradeCost[1]));

JButton btnNewButton4_3 = new JButton("");
btnNewButton4_3.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (totalMoney >= upgradeCost[3]) {

// Cost increase for upgrade
lblNewLabel6.setVisible(true);
totalMoney = totalMoney - upgradeCost[3];
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
upgradeCost[3] = increaseUpgradeCost(upgradeCost[3], upgradeCostIncrease[3]);
upgradeCostIncrease[3] = upgradeCostIncrease[3] + 400;
btnNewButton4_3.setText("$" + Integer.toString(upgradeCost[3]));

// Bonus for upgrade
amountPerTick[1] = upgradeBonus(amountPerTick[1], upgradeMoneyIncrease[2]);
upgradeMoneyIncrease[2] = upgradeMoneyIncrease[2] + 50;
}
// If player cannot afford upgrade
else if (totalMoney < upgradeCost[3]) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_3.setBackground(new Color(50, 205, 50));
btnNewButton4_3.setBounds(66, 167, 124, 37);
panel2.add(btnNewButton4_3);
btnNewButton4_3.setText("$" + Integer.toString(upgradeCost[3]));

JButton btnNewButton4_4 = new JButton("");
btnNewButton4_4.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (totalMoney >= upgradeCost[5]) {

// Cost increase for upgrade
lblNewLabel7.setVisible(true);
totalMoney = totalMoney - upgradeCost[5];
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
upgradeCost[5] = increaseUpgradeCost(upgradeCost[5], upgradeCostIncrease[5]);
upgradeCostIncrease[5] = upgradeCostIncrease[5] + 4000;
btnNewButton4_4.setText("$" + Integer.toString(upgradeCost[5]));

// Bonus for upgrade
amountPerTick[2] = upgradeBonus(amountPerTick[2], upgradeMoneyIncrease[3]);
upgradeMoneyIncrease[3] = upgradeMoneyIncrease[3] + 500;
}
// If player cannot afford upgrade
else if (totalMoney < upgradeCost[5]) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_4.setBackground(new Color(50, 205, 50));
btnNewButton4_4.setBounds(68, 235, 124, 39);
panel2.add(btnNewButton4_4);
btnNewButton4_4.setText("$" + Integer.toString(upgradeCost[5]));

JButton btnNewButton4_5 = new JButton("");
btnNewButton4_5.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (totalMoney >= upgradeCost[7]) {

// Cost increase for upgrade
lblNewLabel8.setVisible(true);
totalMoney = totalMoney - upgradeCost[7];
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
upgradeCost[7] = increaseUpgradeCost(upgradeCost[7], upgradeCostIncrease[7]);
upgradeCostIncrease[7] = upgradeCostIncrease[7] + 40000;
btnNewButton4_5.setText("$" + Integer.toString(upgradeCost[7]));

// Bonus for upgrade
amountPerTick[3] = upgradeBonus(amountPerTick[3], upgradeMoneyIncrease[4]);
upgradeMoneyIncrease[4] = upgradeMoneyIncrease[4] + 5000;
}
// If player cannot afford upgrade
else if (totalMoney < upgradeCost[7]) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_5.setBackground(new Color(50, 205, 50));
btnNewButton4_5.setBounds(68, 310, 124, 33);
panel2.add(btnNewButton4_5);
btnNewButton4_5.setText("$" + Integer.toString(upgradeCost[7]));

JButton btnNewButton4_6 = new JButton("");
btnNewButton4_6.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (totalMoney >= upgradeCost[9]) {

// Cost increase for upgrade
lblNewLabel9.setVisible(true);
totalMoney = totalMoney - upgradeCost[9];
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
upgradeCost[9] = increaseUpgradeCost(upgradeCost[9], upgradeCostIncrease[9]);
upgradeCostIncrease[9] = upgradeCostIncrease[9] + 400000;
btnNewButton4_6.setText("$" + Integer.toString(upgradeCost[9]));

// Bonus for upgrade
amountPerTick[4] = upgradeBonus(amountPerTick[4], upgradeMoneyIncrease[5]);
upgradeMoneyIncrease[5] = upgradeMoneyIncrease[5] + 50000;
}
// If player cannot afford upgrade
else if (totalMoney < upgradeCost[9]) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_6.setBackground(new Color(50, 205, 50));
btnNewButton4_6.setBounds(68, 378, 124, 33);
panel2.add(btnNewButton4_6);
btnNewButton4_6.setText("$" + Integer.toString(upgradeCost[9]));

JLabel lblNewLabel2_24 = new JLabel("10 seconds");
lblNewLabel2_24.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel2_24.setBounds(472, 204, 155, 14);
panel2.add(lblNewLabel2_24);

JButton btnNewButton4_8 = new JButton("");
btnNewButton4_8.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if ((totalMoney >= reduceTimeCost) && (checkIfMaxUpgrade == 1)) {

// Cost increase for upgrade
totalMoney = totalMoney - reduceTimeCost;
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
reduceTimeCost = increaseUpgradeCost(reduceTimeCost, upgradeCostIncrease[1]);
upgradeCostIncrease[1] = upgradeCostIncrease[1] + 25;
btnNewButton4_8.setText("$" + Integer.toString(reduceTimeCost));

// Bonus for upgrade
timeBetweenTicks = timeBetweenTicks - reduceTime;

// Show stats
displayTimeBetweenTicks = timeBetweenTicks;
displayTimeBetweenTicks = displayTimeBetweenTicks / 1000;
lblNewLabel2_24.setText(Double.toString(displayTimeBetweenTicks) + " seconds");

// If upgrade is maxed out
if (timeBetweenTicks <= 500) {
btnNewButton4_8.setText("Maxed!");
checkIfMaxUpgrade = 2;
}
}

// If player cannot afford upgrade
else if ((totalMoney < reduceTimeCost) && (checkIfMaxUpgrade == 1)) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_8.setBackground(new Color(50, 205, 50));
btnNewButton4_8.setBounds(495, 134, 100, 41);
panel2.add(btnNewButton4_8);
btnNewButton4_8.setText("$" + Integer.toString(reduceTimeCost));

JButton btnNewButton4_7 = new JButton("");
btnNewButton4_7.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (totalMoney >= upgradeCost[11]) {

// Cost increase for upgrade
totalMoney = totalMoney - upgradeCost[11];
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
upgradeCost[11] = increaseUpgradeCost(upgradeCost[11], upgradeCostIncrease[11]);
upgradeCostIncrease[11] = upgradeCostIncrease[11] + 2500000;
btnNewButton4_7.setText("$" + Integer.toString(upgradeCost[11]));

// Bonus for upgrade
amountOfAscensions = amountOfAscensions * 2;

// Reset all upgrades
totalMoney = 0;
upgradeCost[0] = 25;
upgradeCost[1] = 50;
upgradeCost[2] = 40;
upgradeCost[3] = 500;
upgradeCost[4] = 250;
upgradeCost[5] = 5000;
upgradeCost[6] = 2500;
upgradeCost[7] = 50000;
upgradeCost[8] = 25000;
upgradeCost[9] = 500000;
upgradeCost[10] = 250000;
upgradeCostIncrease[0] = 15;
upgradeCostIncrease[1] = 25;
upgradeCostIncrease[2] = 20;
upgradeCostIncrease[3] = 100;
upgradeCostIncrease[4] = 50;
upgradeCostIncrease[5] = 1000;
upgradeCostIncrease[6] = 500;
upgradeCostIncrease[7] = 10000;
upgradeCostIncrease[8] = 5000;
upgradeCostIncrease[9] = 100000;
upgradeCostIncrease[10] = 50000;
upgradeMoneyIncrease[0] = 1;
upgradeMoneyIncrease[1] = 10;
upgradeMoneyIncrease[2] = 100;
upgradeMoneyIncrease[3] = 1000;
upgradeMoneyIncrease[4] = 10000;
upgradeMoneyIncrease[5] = 100000;
amountPerTick[0] = 0;
amountPerTick[1] = 0;
amountPerTick[2] = 0;
amountPerTick[3] = 0;
amountPerTick[4] = 0;
checkIfMaxUpgrade = 1;
amountPerClick = 1;
reduceTimeCost = 50;
timeBetweenTicks = 10000;
displayTimeBetweenTicks = 10;
btnNewButton4_1.setText("$" + Integer.toString(upgradeCost[0]));
btnNewButton4_2.setText("$" + Integer.toString(upgradeCost[1]));
btnNewButton4_3.setText("$" + Integer.toString(upgradeCost[3]));
btnNewButton4_4.setText("$" + Integer.toString(upgradeCost[5]));
btnNewButton4_5.setText("$" + Integer.toString(upgradeCost[7]));
btnNewButton4_6.setText("$" + Integer.toString(upgradeCost[9]));
btnNewButton4_8.setText("$" + Integer.toString(reduceTimeCost));
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
lblNewLabel2_24.setText(Double.toString(displayTimeBetweenTicks) + " seconds");
lblNewLabel9.hide();
lblNewLabel8.hide();
lblNewLabel7.hide();
lblNewLabel6.hide();
lblNewLabel5.hide();
}
// If player cannot afford upgrade
else if (totalMoney < upgradeCost[11]) {
lblNewLabel2_21.setVisible(true);
}
}
});
btnNewButton4_7.setBackground(new Color(50, 205, 50));
btnNewButton4_7.setBounds(68, 443, 124, 31);
panel2.add(btnNewButton4_7);
btnNewButton4_7.setText("$" + Integer.toString(upgradeCost[11]));

JLabel lblNewLabel2_22 = new JLabel("\r\n");
lblNewLabel2_22.setBounds(495, 23, 100, 100);
panel2.add(lblNewLabel2_22);

//Display picture upon launch for Diamond reduce timer upgrade icon
lblNewLabel2_22.setIcon(timerIcon);

JLabel lblNewLabel2_23 = new JLabel("Reduces time between ticks:");
lblNewLabel2_23.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
lblNewLabel2_23.setBounds(462, 182, 165, 22);
panel2.add(lblNewLabel2_23);

//Display picture upon launch for Wooden Pickaxe Icon
lblNewLabel5.setIcon(woodenPickaxe);
lblNewLabel5.hide();

JLabel lblNewLabel4 = new JLabel("Upgrades");
lblNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel4.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
lblNewLabel4.setBounds(657, 411, 116, 35);
frame.getContentPane().add(lblNewLabel4);

JLabel lblNewLabel1 = new JLabel("");
lblNewLabel1.setBounds(0, 59, 773, 506);
frame.getContentPane().add(lblNewLabel1);

//Display picture upon launch for background1
lblNewLabel1.setIcon(background1);

JLabel lblNewLabel2 = new JLabel("");
lblNewLabel2.setBounds(0, 0, 773, 78);
frame.getContentPane().add(lblNewLabel2);

//Display picture upon launch for background2
lblNewLabel2.setIcon(background2);

JLabel label = new JLabel("New label");
label.setBounds(637, 34, 46, 14);
frame.getContentPane().add(label);

}

// Code for thread
public void run() {
while (playingGame == 1) {
try {
Thread.sleep(timeBetweenTicks);
}
catch (InterruptedException ex) {

}
totalMoney = totalMoney + amountPerTick[0] * amountOfAscensions;
totalMoney = totalMoney + amountPerTick[1] * amountOfAscensions;
totalMoney = totalMoney + amountPerTick[2] * amountOfAscensions;
totalMoney = totalMoney + amountPerTick[3] * amountOfAscensions;
totalMoney = totalMoney + amountPerTick[4] * amountOfAscensions;
lblNewLabel3.setText("$" + Integer.toString(totalMoney));
}
}

// DESCRIPTION - Returns the added value of amount per click to the total amount of money.
// PARAMETERS - int x, int y, int z
// RETURN TYPE - int
public static int addAmountPerClickToTotalMoney(int x, int y, int z) {
x = x + y * z;
return (x);
}

// DESCRIPTION - Returns the new increased upgrade cost.
// PARAMETERS - int x, int y
// RETURN TYPE - int
public static int increaseUpgradeCost(int x, int y) {
x = x + y;
return (x);
}

// DESCRIPTION - Returns the upgrade bonus from buying the upgrade.
// PARAMETERS - int x, int y
// RETURN TYPE - int
public static int upgradeBonus(int x, int y) {
x = x + y;
return (x);
}

// DESCRIPTION - Returns the upgrade bonus from buying the upgrade.
// PARAMETERS - int x, int y
// RETURN TYPE - int
public static int reduceTimeBetweenTicks(int x, int y) {
x = x - y;
return (x);
}

// DESCRIPTION - Runs the automated upgrades in the background
// PARAMETERS - void
// RETURN TYPE - none
public static void incomePerTick(int x, int y) {

}

public void startGame() {
	// TODO Auto-generated method stub
	
}


}

