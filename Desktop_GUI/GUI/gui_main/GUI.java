package gui_main;

import java.awt.Color;
import gui_codebehind.GUI_BoardController;
import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;


/**
 * Base class for using the GUI library. It initializes the GUI window, and sets up the board (fields).
 * If no custom field parameter is given, the regular Monopoly fields are used.
 * The windows stays open (and thus your program will stay alive), until the GUI is closed
 * using close() method.
 *
 * @author Ronnie Dalsgaard (s093487) with input and adjustments by
 *         Daniel Rubin-Grøn (daniel@koru.dk)
 *         Version 3.1 updates by Christian Budtz (chbu@dtu.dk)
 *         Malte Bryndum Pedersen (s185139)
 */
public final class GUI {

    private GUI_BoardController boardController;
    private static boolean null_fields_allowed = false;


    /**
     *  Starts the GUI, opening a GUI window, with the default Monpoly fields.
     *  Use {@link GUI#GUI(GUI_Field[])} constructor if you want to use custom fields.
     */
    public GUI(){
        GUI_Field[] fields = GUI_FieldFactory.makeFields();
        for(int i = 0; i < fields.length; i++){
            fields[i] = fields[i];
        }
        boardController = new GUI_BoardController(fields);
    }


    /**
     *  Constructor for GUI. Accepts an an array of {@link GUI_Field}. Order of fields in array determine order of fields.
     *  Call any function on gui to show gui (ie. gui.showMessage). Board will try to resize to accommodate fields.
     *  Invoke GUI.set_null_fields_allowed prior to allow null fields (not recommendable)
     *
     * @param fields The fields to display.
     *
     * @throws NullPointerException  If {@link GUI#null_fields_allowed} is false (hasn't be set to true using {@link GUI#setNull_fields_allowed(boolean)}
     *                                  and the 'fields' param contains null values.
     */
    public GUI(GUI_Field[] fields) {
        checkNullArray(fields);
        if(!GUI.null_fields_allowed){
            check_for_null_fields(fields);
        }


        boardController = new GUI_BoardController(fields);
    }


    /**
     *  Constructor for GUI. Accepts an an array of GUI fields. Order of fields in array determine order of fields. 
     *  Call any function on gui to show gui (ie. gui.showMessage). Board will try to resize to accommodate fields. 
     *  Invoke GUI.set_null_fields_allowed prior to allow null fields (not recommendable)
     *
     * @param fields The fields to display.
     * @param backGroundColor Color for background.
     *
     * @throws NullPointerException  If {@link GUI#null_fields_allowed} is false (hasn't be set to true using {@link GUI#setNull_fields_allowed(boolean)}
     *                                  and the 'fields' param contains null values.
     */
    public GUI(GUI_Field[] fields, Color backGroundColor){
        checkNullArray(fields);
        if(!GUI.null_fields_allowed){
            check_for_null_fields(fields);
        }
        this.boardController = new GUI_BoardController(fields, backGroundColor);
    }




    private void checkNullArray(GUI_Field[] fields) {
        if (fields==null){
            throw new NullPointerException("GUI_Field[] fields is null - pass array with fields or use default constructor");
        }
    }




    private void check_for_null_fields(GUI_Field[] fields) {
        String msg = "Null fields!\nNull fields are not recommended! the following indices are null: ";
        String str = "{";
        String howTo = "Disable this Exception by calling the static method GUI.setNull_fields_allowed(true);";
        for(int i = 0; i < fields.length; i++){
            GUI_Field f = fields[i];
            if(f == null){
                str += i+", ";
            }
        }
        str += "}";
        str = str.replace(", }", "}");
        if(str.length() > 2) throw new NullPointerException(msg+str+"\n"+howTo);
    }    


    /**
     * Displays a message to the user, along with an 'OK'-button.
     * The program stops/hangs at the method call until the button
     * is pressed.
     *
     * @param msg The message to print
     */
    public void showMessage(String msg) {
        boardController.showMessage(msg);
    }


    /**
     * Displays a message to the user, and prompt the user for a text input.
     * Blocks/hangs until an input has been entered.
     *
     * @param msg The message that prompts the user.
     * @return The string that the user has entered.
     */
    public String getUserString(String msg) {
        return boardController.getUserString(msg);
    }



    /**
     * Displays a message to the user and awaits the integer response. Only
     * values between min and max are allowed.
     *
     * @param msg The message that promts the user.
     * @param min The minimum value the user is allowed to enter.
     * @param max The maximum value the user is allowed to enter.
     * @return The integer that the user selected.
     */
    public int getUserInteger(String msg, int min, int max) {
        return boardController.getUserInteger(msg, min, max);
    }


    /**
     * Displays a message to the user and awaits the integer response.<br>
     *
     * @param msg The message that promts the user.
     * @return The integer that the user selected.
     */
    public int getUserInteger(String msg) {
        return boardController.getUserInteger(msg, 0, 999999999);
    }


    /**
     * Displays a message and prompt the user for a button press of a series
     * of buttons. The buttons are defined by the number of strings passed
     * as the 'buttons' parameters.
     *
     * @param msg The message that prompts the user
     * @param buttons A number of strings that should be printed on the buttons
     *        the user can press
     * @return The string from the button that the user pressed.
     */
    public String getUserButtonPressed(String msg, String... buttons) {
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = buttons[i].replace("\n", "<BR>");
        }
        return boardController.getUserButtonPressed(msg, buttons);
    }


    /**
     * Displays a message to the user and awaits the drop-down response.<br>
     * @param msg The message that promts the user.
     * @param options A number of strings with the texts that the user should
     *        choose from.
     * @return The string that the user selected.
     */
    public String getUserSelection(String msg, String... options) {
        if (options==null || options.length==0){
            throw new NullPointerException("You must supply at least one option!");
        }
        msg = msg.replace("\n", "<BR>");
        for(int i = 0; i < options.length; i++){
            options[i] = options[i].replace("\n", "<BR>");
        }
        return boardController.getUserSelection(msg, options).toString();
    }


    /**
     * Displays a message to the user, along with two buttons. Awaits
     * a press on either button.
     *
     * @param msg The message that promts the user.
     * @param trueButton The text that should appear on the left button.
     * @param falseButton The text that should appear on the right button.
     * @return True if the left button is pressed by the user, false if right button
     */
    public boolean getUserLeftButtonPressed(String msg, String trueButton, String falseButton) {
        msg = msg.replace("\n", "<BR>");
        trueButton = trueButton.replace("\n", "<BR>");
        falseButton = falseButton.replace("\n", "<BR>");
        return boardController.getUserButtonPressed(msg, trueButton, falseButton).equals(trueButton);
    }


    /**
     * Adds a player to the board, using an object of {@link GUI_Player}.
     * The GUI may only have {@link gui_fields.GUI_Board#MAX_PLAYER_COUNT} number of players.
     *
     * @param player The player to add
     * @return True if the player is added, otherwise false (i.e. too many players)
     */
    public boolean addPlayer(GUI_Player player) {
        return boardController.addPlayer(player);
    }


    /**
     * Displays two dice on the board with given values and fixed positions,
     * and rotations.
     * The positions are grid fields within the GUI window. The grid is 12x12,
     * starting from index 0. Grid field 0,0 is upper right corner.
     *
     * @param faceValue1 : int [1:6]
     * @param rotation1 : int [0:360]
     * @param faceValue2 : int [1:6]
     * @param rotation2 : int [0:360[
     * @param x1 : int [0:11]
     * @param y1 : int [0:11]
     * @param x2 : int [0:11]
     * @param y2 : int [0:11]<br>
     *        (If a parameter is out of bounds nothing will happen!)
     */
    public void setDice(int faceValue1, int rotation1, int x1, int y1,
        int faceValue2, int rotation2, int x2, int y2) {
        boardController.setDice(faceValue1, rotation1, x1, y1, faceValue2, rotation2,
            x2, y2);
    }


    /**
     * Shows two dice on the board. The dice will have the specified values, but
     * the placement is random.<br>
     * @param faceValue1 : Value of the first dice (int value in interval [1:6]).
     * @param faceValue2 : Value of the second dice (int value in interval [1:6]).
     * @param x1 : int [0:11]
     * @param y1 : int [0:11]
     * @param x2 : int [0:11]
     * @param y2 : int [0:11]<br>
     *        (If a parameter is out of bounds nothing will happen!)
     */
    public void setDice(int faceValue1, int x1, int y1, int faceValue2,
        int x2, int y2) {
        boardController.setDice(faceValue1, x1, y1, faceValue2, x2, y2);
    }


    /**
     * Displays two dice on the board with the given values and a fixed rotation,
     * at a random position on the board.
     * If the dice value is not between 1-6, the dice won't be displayed.
     * The method replaces the existing die/dice.
     *
     * @param faceValue1 : Value of first dice int [1:6]
     * @param rotation1 : Rotation of the first die, in degrees int [0:360]
     * @param faceValue2 : Value of second dice int [1:6]
     * @param rotation2 :  Rotation of the first die, in degrees int [0:360]
     */
    public void setDice(int faceValue1, int rotation1, int faceValue2, int rotation2) {
        boardController.setDice(faceValue1, rotation1, faceValue2, rotation2);
    }


    /**
     * Shows two dice on the board. The dice will have the specified values, but
     * the placement is random.<br>
     * @param faceValue1 : int [1:6]
     * @param faceValue2 : int [1:6]<br>
     *        (If a parameter is out of bounds nothing will happen!) Uses random
     *        rotation.
     */
    public void setDice(int faceValue1, int faceValue2) {
        boardController.setDice(faceValue1, faceValue2);
    }


    /**
     * Displays one die with the given value, at a random position
     * on the board. If two dice were displayed
     * prior to calling the method, the other die will be hidden.
     *
     * @param faceValue The value of the die. If the value is not between
     *                  1-6, the die won't be updated.
     */
    public void setDie(int faceValue) {
        boardController.setDie(faceValue);
    }


    /**
     * Sets the current "chance card text", and automatically displays the text
     * in the center square.
     * Works as if you had called {@link GUI#setChanceCard(String)} and {@link GUI#displayChanceCard()}.
     *
     * @param txt : The chance card text to be displayed (mind the length)
     */
    public void displayChanceCard(String txt) {
        txt = txt.replace("\n", "<BR>");
        boardController.displayChanceCard(txt);
    }


    /**
     * Sets the current "chance card text". The "chance card text" is displayed when calling
     * {@link GUI#displayChanceCard()}, or the center square is clicked.
     * Note that the text is NOT displayed automatically when calling this method (instead
     * use the {@link GUI#displayChanceCard(String)} method).
     *
     * @param txt : The chance card text to be displayed (mind the length)
     */
    public void setChanceCard(String txt) {
        txt = txt.replace("\n", "<BR>");
        boardController.setChanceCard(txt);
    }


    /**
     * Displays the current chance card text in the center, set by the
     * {@link GUI#setChanceCard(String)} method.
     */
    public void displayChanceCard() {
        boardController.displayChanceCard();
    }


    public GUI_Field[] getFields(){
        return boardController.getFields();
    }


    /**
     *  Closes the GUI window, and the stops the thread it runs on.
     */
    public void close(){
        this.boardController.close();
    }


    /**
     * Checks wheter null fields are allowed, when creating
     * a new GUI with custom fields.
     *
     * @return True if null fields allowed, otherwise falls
     */
    public static boolean isNull_fields_allowed() {
        return null_fields_allowed;
    }


    /**
     * Toggle whether null fields should be allowed or not,
     * when creating a new GUI with custom fields (the value
     * null is allowed in the GUI_Field array passed to the
     * GUI object). Null fields will be displayed as empty
     * squares.<br>
     * Note that this has to be called BEFORE creating a GUI with null
     * fields.<br>
     *
     * However, it's NOT recommended to have null fields, since
     * moving accessing the fields (including moving a car to
     * a field will throw a null pointer exception.
     *
     * @param allowed True if null fields should be allowed, false if not
     */
    public static void setNull_fields_allowed(boolean allowed) {
        GUI.null_fields_allowed = allowed;
    }

}
