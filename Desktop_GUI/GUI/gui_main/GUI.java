package gui_main;

import java.awt.Color;
import gui_codebehind.GUI_BoardController;
import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

/**
 * Provides easy access to the GUI features. Se javadoc at: 
 * <a href="http://htmlpreview.github.io/?https://raw.githubusercontent.com/diplomit-dtu/Matador_GUI/repository/Desktop_GUI/doc/index.html">github</>
 * @author Ronnie Dalsgaard (s093487) with input and adjustments by
 *         Daniel Rubin-Grøn (daniel@koru.dk)
 *         Version 3.1 updates by Christian Budtz (chbu@dtu.dk)
 */
public final class GUI {
    private final Color BASECOLOR = GUI_Board.BASECOLOR;
    private GUI_BoardController bc;
    private static boolean null_fields_allowed = false;
    
    public static void main(String[] args) {
        new GUI();
    }
    /**
     *  Constructor for GUI. Accepts an an array of GUI fields. Order of fields in array determine order of fields. 
     *  Call any function on gui to show gui (ie. gui.showMessage). Board will try to resize to accommodate fields. 
     *  Invoke GUI.set_null_fields_allowed prior to allow null fields (not recommendable)
     * @param fields
     */
    public GUI(GUI_Field[] fields, Color backGroundColor){
        if(!GUI.null_fields_allowed){
            check_for_null_fields(fields);
        }
        //Pad array to 16
        if (fields.length<16){
            GUI_Field[] tempFields = new GUI_Field[16];
            for (int i = 0; i < fields.length; i++) {
                tempFields[i] = fields[i];
                
            }
            fields = tempFields;
        }
        this.bc= new GUI_BoardController(fields, backGroundColor);
        
    }
    
    public GUI(GUI_Field[] fields) {
        if(!GUI.null_fields_allowed){
            check_for_null_fields(fields);
        }
        //Pad array to 16
        if (fields.length<16){
            GUI_Field[] tempFields = new GUI_Field[16];
            for (int i = 0; i < fields.length; i++) {
                tempFields[i] = fields[i];
                
            }
            fields = tempFields;
        }
        
        bc = new GUI_BoardController(fields); 
    }
    /**
     *  Constructor for GUI. Creates default Matador board
     *  Call any function on gui to show gui (ie. gui.showMessage).
     * @param fields
     */
    public GUI(){
        GUI_Field[] fields = GUI_FieldFactory.makeFields();
        for(int i = 0; i < fields.length; i++){
            fields[i] = fields[i];
        }
        bc = new GUI_BoardController(fields);        
    }

    private void check_for_null_fields(GUI_Field[] fields) {
        String msg = "Null fields!\nNull fields are not recommended! the following indecies are null: ";
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
     * Displays a message to the user.<br>
     * Breaks the system untill "OK" is pressed.<br>
     * @param msg The message to print.
     */
    public void showMessage(String msg) {
//        msg = msg.replace("\n", "<BR>");
        bc.showMessage(msg);
    }
    /**
     * Displays a message to the user and awaits the response.<br>
     * @param msg The message that promts the user.
     * @return The string that the user has entered.
     */
    public String getUserString(String msg) {
//        msg = msg.replace("\n", "<BR>");
        return bc.getUserString(msg);
    }
    /**
     * Displays a message to the user and awaits the integer response. Only
     * values between min and max are allowed.<br>
     * @param msg The message that promts the user.
     * @param min The minimum value the user is allowed to enter.
     * @param max The maximum value the user is allowed to enter.
     * @return The integer that the user selected.
     */
    public int getUserInteger(String msg, int min, int max) {
//        msg = msg.replace("\n", "<BR>");
        return bc.getUserInteger(msg, min, max);
    }
    /**
     * Displays a message to the user and awaits the integer response.<br>
     * @param msg The message that promts the user.
     * @return The integer that the user selected.
     */
    public int getUserInteger(String msg) {
//        msg = msg.replace("\n", "<BR>");
        return bc.getUserInteger(msg, 0, 999999999);
    }
    /**
     * Displays a message to the user and awaits the button pressed response.<br>
     * @param msg The message that promts the user.
     * @param buttons A number of strings that should be printed on the buttons
     *        the user can press.
     * @return The string from the button that the user pressed.
     */
    public String getUserButtonPressed(String msg, String... buttons) {
//        msg = msg.replace("\n", "<BR>");
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = buttons[i].replace("\n", "<BR>");
        }
        return bc.getUserButtonPressed(msg, buttons).toString();
    }
    /**
     * Displays a message to the user and awaits the drop-down response.<br>
     * @param msg The message that promts the user.
     * @param options A number of strings with the texts that the user should
     *        choose from.
     * @return The string that the user selected.
     */
    public String getUserSelection(String msg, String... options) {
        msg = msg.replace("\n", "<BR>");
        for(int i = 0; i < options.length; i++){
            options[i] = options[i].replace("\n", "<BR>");
        }
        return bc.getUserSelection(msg, options).toString();
    }
    /**
     * Displays a message to the user and awaits the boolean response.<br>
     * @param msg The message that promts the user.
     * @param trueButton The text that should appear on the left button.
     * @param falseButton The text that should appear on the right button.
     * @return True if the left button is pressed by the user. False otherwise.
     */
    public boolean getUserLeftButtonPressed(String msg, String trueButton, String falseButton) {
        msg = msg.replace("\n", "<BR>");
        trueButton = trueButton.replace("\n", "<BR>");
        falseButton = falseButton.replace("\n", "<BR>");
        return bc.getUserButtonPressed(msg, trueButton, falseButton).equals(trueButton);
    }
    /**
     * Adds a player to the board.<br>
     * Max. 6 players.<br>
     * @param name : String (Mind the length!) (Unique identifier of the player - no duplicates)
     * @param balance : int
     * @param car : Car
     * Cars are created this way:<br>
     *   Car car = new Car.Builder()<br>
     *     .primaryColor(Color.MAGENTA)<br>
     *     .secondaryColor(Color.BLUE)<br>
     *     .typeTractor()<br>
     *     .patternDotted()<br>
     *     .Build();<br>
     * @return true if player is added, otherwise false
     */
    public boolean addPlayer(GUI_Player player) {
        return bc.addPlayer(player);
    }
    /**
     * Shows two dice on the board. The dice will have the specified values, but
     * the placement is random.<br>
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
        bc.setDice(faceValue1, rotation1, x1, y1, faceValue2, rotation2,
            x2, y2);
    }
    /**
     * Shows two dice on the board. The dice will have the specified values, but
     * the placement is random.<br>
     * @param faceValue1 : int [1:6]
     * @param faceValue2 : int [1:6]
     * @param x1 : int [0:11]
     * @param y1 : int [0:11]
     * @param x2 : int [0:11]
     * @param y2 : int [0:11]<br>
     *        (If a parameter is out of bounds nothing will happen!)
     */
    public void setDice(int faceValue1, int x1, int y1, int faceValue2,
        int x2, int y2) {
        bc.setDice(faceValue1, x1, y1, faceValue2, x2, y2);
    }
    /**
     * Shows two dice on the board. The dice will have the specified values, but
     * the placement is random.<br>
     * @param faceValue1 : int [1:6]
     * @param rotation1 : int [0:360]
     * @param faceValue2 : int [1:6]
     * @param rotation2 : int [0:360[<br>
     *        (If a parameter is out of bounds nothing will happen!)
     */
    public void setDice(int faceValue1, int rotation1, int faceValue2, int rotation2) {
        bc.setDice(faceValue1, rotation1, faceValue2, rotation2);
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
        bc.setDice(faceValue1, faceValue2);
    }
    /**
     * Method for showing one die at a random location
     * @param faceValue
     */
    public void setDie(int faceValue) {
        bc.setDie(faceValue);
    }
    /**
     * Sets the text to appear in the center and displays it.
     * @param txt : String (Mind the length!)<br>
     */
    public void displayChanceCard(String txt) {
        txt = txt.replace("\n", "<BR>");
        bc.displayChanceCard(txt);
    }
    /**
     * Sets the text to appear in the center when calling displayChanceCard() and when the deck is pressed.
     * @param txt : String (Mind the length!)<br>
     */
    public void setChanceCard(String txt) {
        txt = txt.replace("\n", "<BR>");
        bc.setChanceCard(txt);
    }
    /**
     * Displays the current chance card text in the center.
     */
    public void displayChanceCard() {
        bc.displayChanceCard();
    }


    public GUI_Field[] getFields(){ return bc.getFields(); }

    /**
     *  Closes gui.
     */
    public void close(){
        this.bc.close();
    }
    
    public static boolean isNull_fields_allowed() {
        return null_fields_allowed;
    }
    public static void setNull_fields_allowed(boolean allowed) {
        GUI.null_fields_allowed = allowed;
    }

}
