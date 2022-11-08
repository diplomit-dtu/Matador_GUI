package gui.core;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;

import gui.input.EnterButton;
import gui.input.IntegerInput;
import gui.input.StringInput;

/**
 * Provides access to GUI
 * @author Ronnie
 */
public final class BoardController {
    private String userInput = null;
    private final Board board;
    private static volatile Random rand = null;


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
    public BoardController(Field[] fields) {
        this.board = new Board(fields);
    }


    public BoardController(Field[] fields, Color backGroundColor) {
        this.board = new Board(fields, backGroundColor);
    }


    /**
     * Displays a message for the user. The user presses OK when the message is read Is a breaking
     * call.<br>
     * @param msg The message for the user.
     */
    public void showMessage(String msg) {
        board.clearInputPanel();
        final CountDownLatch latch = new CountDownLatch(1);

        EnterButton okButton = new EnterButton("OK", () -> {
            BoardController.this.board.clearInputPanel();
            latch.countDown();
        });

        
        this.board.getUserInput(msg, okButton.getJButton());
        okButton.focus();
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
     * @param minLength Minimum length of the input text (inclusive)
     * @param maxLength Maximum length of the input text (inclusive)
     * @return The text entered by the user.
     */
    public String getUserString(String msg, int minLength, int maxLength, boolean allowWhitespace) {
        StringInput input = new StringInput(board, msg, minLength, maxLength, allowWhitespace);
        return input.getResult();
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
    public int getUserInteger( String msg, final int min, final int max) {
        board.clearInputPanel();
        IntegerInput input = new IntegerInput(this.board, msg, min, max);
        return input.getResult();
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

            EnterButton button = new EnterButton(string, () -> {
                BoardController.this.userInput = string;
                BoardController.this.board.clearInputPanel();
                latch.countDown();
            });

            buttons[i] = button.getJButton();
            button.focus();
        }

        this.board.getUserInput(msg, buttons);
        
        try {
            latch.await();
            return this.userInput;
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
        final JComboBox<String> dropdown = new JComboBox<String>(options);

        EnterButton okButton = new EnterButton("OK", () -> {
            BoardController.this.userInput = dropdown.getSelectedItem().toString();
            BoardController.this.board.clearInputPanel();
            latch.countDown();
        });

        this.board.getUserInput(msg, dropdown, okButton.getJButton());
        try {
            latch.await();
            return this.userInput;
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Adds a player to the board.<br>
     * A new player with the same color will replace the old.<br>
     * Max. 6 players.<br>
     * @param player : The player must be created beforehand
     * @return true if player is added, otherwise false
     */
    public boolean addPlayer(Player player) {
        return this.board.addPlayer(player);
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
        if (getFields().length ==40) {       
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
        } else if (getFields().length <40 && getFields().length>=32){
            for (int x = 2; x <4; x++) {
                for (int y = 2; y<9; y++) {
                    dicePlaces.add(new Point(x, y));
                }
            }
            
        } else {
            for(int x=1; x<9; x++) {
                for (int y = 0; y<2; y++) {
                    dicePlaces.add(new Point(x, y));
                }
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
    
    public Field[] getFields() { return board.getFields(); }
    
    public void setDie(int faceValue) {
        int rotation1 = rand().nextInt(360);
        int x = rand().nextInt(9);
        setDice(faceValue, rotation1, x, 9, faceValue, rotation1, x, 9);
        
    }

    public void close() {
        this.board.dispose();
    }
}