package desktop_board;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import desktop_codebehind.Car;
import desktop_codebehind.FieldFactory;
import desktop_codebehind.Player;
import desktop_fields.Field;
import desktop_fields.Ownable;
import desktop_fields.Street;

/**
 * Provides access to GUI
 * @author Ronnie
 */
public final class BoardController {
	static String userInput = null;
	Board board;
	private static volatile Random rand = null;
	// private BufferedImage[] diceImages = new BufferedImage[6];
	
	public static Random rand() {
		if(rand == null) {
		    synchronized (BoardController.class) {
		        if(rand == null) rand = new Random();
            }
		}
		return rand;
	}
	/**
	 * Contains service methods for board for controlling the board.
	 */
	public BoardController() {
		this.board = Board.getInstance();
	}
	/**
	 * Displays a message for the user. The user presses OK when the message is read Is a breaking
	 * call.<br>
	 * @param msg The message for the user.
	 */
	public void showMessage(String msg) {
		final CountDownLatch latch = new CountDownLatch(1);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(@SuppressWarnings("unused") ActionEvent e) {
				BoardController.this.board.clearInputPanel();
				latch.countDown();
			}
		});
		this.board.getUserInput(msg, okButton);
		try {
			latch.await();
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Displays a message for the user and a textfield for the user to fill out.<br>
	 * Is a breaking call.<br>
	 * @param msg The message for the user.
	 * @return The text entered by the user.
	 */
	public String getUserString(String msg) {
		final CountDownLatch latch = new CountDownLatch(1);
		final JTextField tf = new JTextField(20);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(@SuppressWarnings("unused") ActionEvent e) {
				BoardController.userInput = tf.getText();
				BoardController.this.board.clearInputPanel();
				latch.countDown();
			}
		});
		this.board.getUserInput(msg, tf, okButton);
		
		try {
			latch.await();
			return BoardController.userInput;
		} catch(InterruptedException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * Displays a message for the user and a textfield for the user to fill out. Only numbers are
	 * allowed if the number isn't in range the user can't press OK.<br>
	 * Is a breaking call.<br>
	 * @param msg The message for the user.
	 * @param min The low end of the valid range (inclusive).
	 * @param max The high end of the valid range (inclusive).
	 * @return The number entered by the user.
	 */
	public int getUserInteger(String msg, final int min, final int max) {
		if((min < 0) || (max < 1) || (max <= min)) {
			return -1;
		}
		if("".equals(msg)) {
			return -1;
		}
		final CountDownLatch latch = new CountDownLatch(1);
		final JButton okButton = new JButton("OK");
		okButton.setEnabled(false);
		
		final JTextField tf = new JTextField(20);
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		tf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(@SuppressWarnings("unused") KeyEvent ke) {}
			@Override
			public void keyReleased(KeyEvent ke) {
				String input = tf.getText() + ke.getKeyChar();
				String output = "";
				for(int i = 0; i < input.toCharArray().length - 1; i++) {
					char c = input.toCharArray()[i];
					if(c >= '0' && c <= '9' && i < 9) {
						output += c;
					}
				}
				tf.setText(output);
				int val = -1;
				try {
					val = Integer.parseInt(output);
					if((min <= val) && (val <= max)) {
						tf.setForeground(Color.BLACK);
					} else {
						tf.setForeground(Color.RED);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
					tf.setForeground(Color.RED);
				}
				
				okButton.setEnabled(tf.getForeground().equals(Color.BLACK));
			}
			@Override
			public void keyPressed(@SuppressWarnings("unused") KeyEvent ke) {}
		});
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(@SuppressWarnings("unused") ActionEvent e) {
				BoardController.userInput = tf.getText();
				BoardController.this.board.clearInputPanel();
				latch.countDown();
			}
		});
		this.board.getUserInput(msg, tf, okButton);
		
		try {
			latch.await();
			return Integer.parseInt(BoardController.userInput);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
			return -1;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	/**
	 * Displays a message for the user along with a row of buttons.<br>
	 * Is a breaking call.<br>
	 * @param msg The message for the user.
	 * @param buttonTexts The labels on the buttons.
	 * @return The label of the button pressed by the user.
	 */
	public String getUserButtonPressed(String msg, String... buttonTexts) {
		if((buttonTexts == null) || (buttonTexts.length < 1)) {
			return null;
		}
		final CountDownLatch latch = new CountDownLatch(1);
		JButton[] buttons = new JButton[buttonTexts.length];
		for(int i = 0; i < buttonTexts.length; i++) {
			String string = buttonTexts[i];
			if("".equals(string)) {
				return null;
			}
			JButton btn = new JButton(string);
			btn.setName(string);
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					BoardController.userInput
					= ((JButton) e.getSource()).getName();
					BoardController.this.board.clearInputPanel();
					latch.countDown();
				}
			});
			buttons[i] = btn;
		}
		this.board.getUserInput(msg, buttons);
		
		try {
			latch.await();
			return BoardController.userInput;
		} catch(InterruptedException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * Displays a message and a drop-down menu with option for the user.<br>
	 * Is a breaking call.<br>
	 * @param msg The message for the user
	 * @param options The options the user can choose from
	 * @return The selected option
	 */
	public String getUserSelection(String msg, String... options) {
		if((options == null) || (options.length < 1)) {
			return null;
		}
		final CountDownLatch latch = new CountDownLatch(1);
		for(int i = 0; i < options.length; i++) {
			String string = options[i];
			if("".equals(string)) {
				return null;
			}
		}
		final JComboBox dropdown = new JComboBox(options);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(@SuppressWarnings("unused") ActionEvent e) {
				BoardController.userInput = dropdown.getSelectedItem().toString();
				BoardController.this.board.clearInputPanel();
				latch.countDown();
			}
		});
		
		this.board.getUserInput(msg, dropdown, okButton);
		try {
			latch.await();
			return BoardController.userInput;
		} catch(InterruptedException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * Closes the GUI, so you can start a new one.
	 */
	public void closeGUI() {
		Board.destroy();
	}
	/**
	 * Sets the title of a field on the board.<br>
	 * @param fieldNumber : int [1:40]
	 * @param title : String (Mind the length!)
	 */
	public void setTitleText(int fieldNumber, String title) {
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		f.setTitle(title);
	}
	/**
	 * Sets the subText of a field on the board.<br>
	 * @param fieldNumber : int [1:40]
	 * @param subText : String (Mind the length!)
	 */
	public void setSubText(int fieldNumber, String subText) {
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		f.setSubText(subText);
	}
	/**
	 * Sets the Description (The text shown in the center when mouse hovers) of a field on the
	 * board.<br>
	 * @param fieldNumber : int [1:40]
	 * @param description : String (Mind the length!)
	 */
	public void setDescriptionText(int fieldNumber, String description) {
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		f.setDescription(description);
	}
	public void addPlayer(String name, int balance, Car car) {
		this.board.addPlayer(name, balance, car);
	}
	/**
	 * Adds a player to the board.<br>
	 * A new player with the same color will replace the old.<br>
	 * Max. 6 players.<br>
	 * @param name : String (Mind the length!)
	 * @param balance : int<br>
	 *        Automatic coloring
	 */
	public void addPlayer(String name, int balance) {
		this.board.addPlayer(name, balance);
	}
	/**
	 * Sets the balance of a player if the player has been added.<br>
	 * @param name The name of the player
	 * @param newBalance : int
	 */
	public void setBalance(String name, int newBalance) {
		Player p = this.board.getPlayer(name);
		if(p != null) {
			p.setBalance(newBalance);
			this.board.updatePlayers();
		}
	}
	/**
	 * Shows two dice on the board. The dice will have the specified values, but the placement is
	 * random.<br>
	 * @param faceValue1 : int [1:6]
	 * @param faceValue2 : int [1:6]<br>
	 *        (If a faceValue is out of bounds nothing will happen!)
	 */
	public void setDice(int faceValue1, int faceValue2) {
		int rotation1 = rand().nextInt(360);
		int rotation2 = rand().nextInt(360);
		setDice(faceValue1, rotation1, faceValue2, rotation2);
	}
	/**
	 * Shows two dice on the board. The dice will have the specified values and location.<br>
	 * @param faceValue1 : int [1:6]
	 * @param x1 : int [0:10]
	 * @param y1 : int [0:10]
	 * @param faceValue2 : int [1:6]
	 * @param x2 : int [0:10]
	 * @param y2 : int [0:10]
	 */
	public void setDice(int faceValue1, int x1, int y1, int faceValue2, int x2, int y2) {
		int rotation1 = rand().nextInt(360);
		int rotation2 = rand().nextInt(360);
		setDice(faceValue1, rotation1, x1, y1, faceValue2, rotation2, x2, y2);
	}
	/**
	 * Shows two dice on the board. The dice will have the specified values, but the placement is
	 * random.<br>
	 * @param faceValue1 : int [1:6]
	 * @param rotation1 : int [0:2]
	 * @param faceValue2 : int [1:6]
	 * @param rotation2 : int [0:2]<br>
	 *        (If a faceValue is out of bounds nothing will happen!)
	 */
	public void setDice(int faceValue1, int rotation1, int faceValue2, int rotation2) {
		// Make an accepted locations for a dice
		List<Point> dicePlaces = new ArrayList<Point>();
		for(int x = 1; x < 10; x++) {
			for(int y = 1; y < 10; y++) {
				if(x >= 4 && x <= 6 && y >= 4 && y <= 6) {
					continue;
				} // Do not add the points in the center.
				if(x > 6 && y > (9 - this.board.getPlayerCount())) {
					continue;
				} // Do not add the points used for the players names and balances.
				dicePlaces.add(new Point(x, y));
			}
		}
		
		// Randomly choose a location for die1
		int index1 = 0;
		index1 = (int) (Math.random() * dicePlaces.size());
		Point dice1Position = dicePlaces.remove(index1);
		
		// Remove all locations "far" away from die1
		final int MAX_DISTANCE = 2;
		ArrayList<Point> toBeRemoved = new ArrayList<Point>();
		for(Point p : dicePlaces) {
			if(p.x < dice1Position.x - MAX_DISTANCE || p.x > dice1Position.x + MAX_DISTANCE
				|| p.y < dice1Position.y - MAX_DISTANCE || p.y > dice1Position.y + MAX_DISTANCE) {
				toBeRemoved.add(p);
			}
		}
		dicePlaces.removeAll(toBeRemoved);
		
		// Randomly choose a location for die2
		int index2 = 0;
		index2 = (int) (Math.random() * dicePlaces.size());
		Point dice2Position = dicePlaces.get(index2);
		
		setDice(faceValue1, rotation1, dice1Position.x, dice1Position.y, faceValue2, rotation2,
			dice2Position.x, dice2Position.y);
	}
	/**
	 * Shows two dice on the board. The dice will have the specified values, location and rotation.<br>
	 * @param faceValue1 : int [1:6]
	 * @param rotation1 : int [0:2]
	 * @param x1 : int [0:10]
	 * @param y1 : int [0:10]
	 * @param faceValue2 : int [1:6]
	 * @param rotation2 : int [0:2]
	 * @param x2 : int [0:10]
	 * @param y2 : int [0:10]
	 */
	public void setDice(int faceValue1, int rotation1, int x1, int y1, int faceValue2,
		int rotation2, int x2, int y2) {
	    boolean faceValuesAreValid = areFaceValuesValid(faceValue1, faceValue2);
	    boolean rotationsAreValid = areRotationsValid(rotation1, rotation2);
	    boolean positionsAreValid = arePositionsValid(x1, y1, x2, y2);
		if(faceValuesAreValid && rotationsAreValid && positionsAreValid) {
			this.board.setDice(x1, y1, faceValue1, rotation1, x2, y2, faceValue2, rotation2);
		}
	}
	private boolean arePositionsValid(int x1, int y1, int x2, int y2) {
	    return x1 >= 0 && x1 <= 10
            && y1 >= 0 && y1 <= 10
            && x2 >= 0 && x2 <= 10
            && y2 >= 0 && y2 <= 10;
	}
	private boolean areRotationsValid(int rotation1, int rotation2) {
	    return rotation1 >= 0 && rotation1 <= 359
            && rotation2 >= 0 && rotation2 <= 359;
	}
    private boolean areFaceValuesValid(int faceValue1, int faceValue2) {
        return faceValue1 >= 1 && faceValue1 <= 6
			&& faceValue2 >= 1 && faceValue2 <= 6;
    }
    public void displayChanceCard(String txt) {
        Center.getInstance().setChanceCard(txt);
        Center.getInstance().displayChanceCard();
    }
    public void setChanceCard(String txt) {
        Center.getInstance().setChanceCard(txt);
    }
    public void displayChanceCard() {
        Center.getInstance().displayChanceCard();
    }
	/**
	 * Places a car on the field.<br>
	 * All cars can be placed on the same field.<br>
	 * A car can only be placed if the corresponding player has been added.<br>
	 * If a car is placed on the same field multiple times, nothing more happens.<br>
	 * A car can not be placed on multiple fields simultaneously.
	 * @param fieldNumber : int [1:40]
	 * @param name The name of the player
	 */
	public void setCar(int fieldNumber, String name) {
		// removeCar(name);
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		if(this.board.getPlayer(name) != null) {
			f.setCar(name, true);
		}
	}
	/**
	 * Removes a car from the board.<br>
	 * If the car is not on the board, nothing happens.<br>
	 * @param fieldNumber : int [1:40]
	 * @param name The name of the player
	 */
	public void removeCar(int fieldNumber, String name) {
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		f.setCar(name, false);
		Center.getInstance().displayDefault();
	}
	/**
	 * Removes all the players cars from the board.<br>
	 * If the car is not on the board, nothing happens.<br>
	 * @param name The name of the player
	 */
	public void removeAllCars(String name) {
		for(Field f : FieldFactory.fields) {
			f.setCar(name, false);
		}
		Center.getInstance().displayDefault();
	}
	/**
	 * Sets an owner of a field.<br>
	 * The field border will have the same color as the player. The owners name will be printed in
	 * the subText. If the field is not a street, shipping or a brewery nothing happens.<br>
	 * If the owner hasn't been added to the board, nothing happens.
	 * @param fieldNumber : int [1:40]
	 * @param name The name of the player
	 */
	public void setOwner(int fieldNumber, String name) {
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		Player p = this.board.getPlayer(name);
		if((f instanceof Ownable) && p != null) {
			((Ownable) f).setOwner(p);
		}
	}
	/**
	 * Removes an owner from the field.<br>
	 * If the field has no owner, nothing happens.
	 * @param fieldNumber : int [1:40]
	 */
	public void removeOwner(int fieldNumber) {
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		if(f instanceof Ownable) {
			((Ownable) f).setOwner(null);
		}
	}
	/**
	 * Sets houses from the street, and removes the hotel if one is present.<br>
	 * If houseCount is out of bounds, nothing happens.<br>
	 * If the field is not a street, nothing happens.<br>
	 * @param fieldNumber : int [1:40]
	 * @param houseCount : int [0:4]
	 */
	public void setHouses(int fieldNumber, int houseCount) {
		if(houseCount >= 0 && houseCount < 5) {
			Field f = FieldFactory.fields.get(fieldNumber - 1);
			if(f instanceof Street) {
				Street s = ((Street) f);
				s.setHouses(houseCount);
			}
		}
	}
	/**
	 * Sets whether or not a hotel should be on the street and removes all houses if any is present.<br>
	 * @param fieldNumber : int [1:40]
	 * @param hasHotel : boolean
	 */
	public void setHotel(int fieldNumber, boolean hasHotel) {
		Field f = FieldFactory.fields.get(fieldNumber - 1);
		if(f instanceof Street) {
			Street s = ((Street) f);
			s.setHotel(hasHotel);
		}
	}
	
}