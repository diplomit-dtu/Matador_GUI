package desktop_resources;

import java.awt.Color;
import java.util.ArrayList;
import desktop_board.Board;
import desktop_board.BoardController;
import desktop_codebehind.Car;
import desktop_codebehind.FieldFactory;
import desktop_fields.Field;

/**
 * Provides easy access to the GUI features.
 * @author Ronnie Dalsgaard (s093487) with input and adjustments by Daniel
 *         Rubin-Grøn (daniel@koru.dk)
 */
public final class GUI {
    public final Color BASECOLOR = Board.BASECOLOR;
    private BoardController bc;
    
    public GUI(Field[] fields) {
        ArrayList<Field> list = new ArrayList<Field>();
        for(Field f : fields) list.add(f);
        FieldFactory.fields = list;
        bc = new BoardController(); 
    }
    
    public GUI(){
        bc = new BoardController();        
    }
    
    
   /**
     * Initializes the board using an array of fields.<br>
     * Doesn't show the GUI.<br>
     * If this method isn't the first method to be called it will
     * have no result.<br>
     * @param fields : Field[]<br>
     * Fields are created this way:<br>
     *   Field f = new Start.Builder().setTitle("Title").setBgColor(Color.RED).build()<br>
     *   Depending on the type of field, various attributes can be set.<br>
     *   The field types are as follows:<br>
     *      ... new Tax.Builder().build();<br>
     *      ... new Street.Builder().build();<br>
     *      ... new Shipping.Builder().build();<br>
     *      ... new Refuge.Builder().build();<br>
     *      ... new Jail.Builder().build();<br>
     *      ... new Chance.Builder().build();<br>
     *      ... new Brewery.Builder().build();<br>
     *      ... new Empty.Builder().build();<br>
     */


    /**
     * Displays a message to the user.<br>
     * Breaks the system untill "OK" is pressed.<br>
     * @param msg The message to print.
     */
    public void showMessage(String msg) {
        bc.showMessage(msg);
    }
    /**
     * Displays a message to the user and awaits the response.<br>
     * @param msg The message that promts the user.
     * @return The string that the user has entered.
     */
    public String getUserString(String msg) {
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
        return bc.getUserInteger(msg, min, max);
    }
    /**
     * Displays a message to the user and awaits the integer response.<br>
     * @param msg The message that promts the user.
     * @return The integer that the user selected.
     */
    public int getUserInteger(String msg) {
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
        return bc.getUserSelection(msg, options).toString();
    }
    /**
     * Displays a message to the user and awaits the boolean response.<br>
     * @param msg The message that promts the user.
     * @param trueButton The text that should appear on the left button.
     * @param falseButton The text that should appear on the right button.
     * @return True if the left button is pressed by the user. False otherwise.
     */
    public boolean getUserLeftButtonPressed(String msg, 
        String trueButton, String falseButton) {
        return bc.getUserButtonPressed(msg, trueButton, 
            falseButton).equals(trueButton);
    }
    /**
     * Sets the title of a field on the board.<br>
     * @param fieldNumber : int [1:40]
     * @param title : String (Mind the length!)
     */
    public void setTitleText(int fieldNumber, String title) {
        bc.setTitleText(fieldNumber, title);
    }
    /**
     * Sets the subText of a field on the board.<br>
     * @param fieldNumber : int [1:40]
     * @param subText : String (Mind the length!)
     */
    public void setSubText(int fieldNumber, String subText) {
        bc.setSubText(fieldNumber, subText);
    }
    /**
     * Sets the Description (The text shown in the center when mouse hovers) of
     * a field on the board.<br>
     * @param fieldNumber : int [1:40]
     * @param description : String (Mind the length!)
     */
    public void setDescriptionText(int fieldNumber, String description) {
        bc.setDescriptionText(fieldNumber, description);
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
     */
    public void addPlayer(String name, int balance, Car car) {
        bc.addPlayer(name, balance, car);
    }
    /**
     * Adds a player to the board.<br>
     * Max. 6 players.<br>
     * @param name : String (Mind the length!) (Unique identifier of the player - no duplicates)
     * @param balance : int
     */
    public void addPlayer(String name, int balance) {
        bc.addPlayer(name, balance);
    }
    /**
     * Sets the balance of a player if the player has been added.
     * @param name The name of the player
     * @param newBalance : int
     */
    public void setBalance(String name, int newBalance) {
        bc.setBalance(name, newBalance);
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
    public void setDice(int faceValue1, int rotation1, int faceValue2,
        int rotation2) {
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
     * Sets the text to appear in the center and displays it.
     * @param txt : String (Mind the length!)<br>
     */
    public void displayChanceCard(String txt) {
        bc.displayChanceCard(txt);
    }
    /**
     * Sets the text to appear in the center when calling displayChanceCard() and when the deck is pressed.
     * @param txt : String (Mind the length!)<br>
     */
    public void setChanceCard(String txt) {
        bc.setChanceCard(txt);
    }
    /**
     * Displays the current chance card text in the center.
     */
    public void displayChanceCard() {
        bc.displayChanceCard();
    }
    /**
     * Places a car on the field.<br>
     * All cars can be placed on the same field.<br>
     * A car can only be placed if the corresponding player has been added.<br>
     * If a car is placed on the same field multiple times, nothing more
     * happens.<br>
     * A car can not be placed on multiple fields simultaneously.
     * @param fieldNumber : int [1:40]
     * @param name The name of the player
     */
    public void setCar(int fieldNumber, String name) {
        bc.setCar(fieldNumber, name);
    }
    /**
     * Removes a car from the board.<br>
     * If the car is not on the board, nothing happens.
     * @param fieldNumber : int [1:40]
     * @param name The name of the player
     */
    public void removeCar(int fieldNumber, String name) {
        bc.removeCar(fieldNumber, name);
    }
    /**
     * Removes all cars belonging to this player.
     * @param name The name of the player.
     */
    public void removeAllCars(String name) {
        bc.removeAllCars(name);
    }
    /**
     * Sets an owner of a field.<br>
     * The field border will have the same color as the player. The owners name
     * will be printed in the subText. If the field is not a street, shipping or
     * a brewery nothing happens.<br>
     * If the owner hasn't been added to the board, nothing happens.
     * @param fieldNumber : int [1:40]
     * @param name The name of the player
     */
    public void setOwner(int fieldNumber, String name) {
        bc.setOwner(fieldNumber, name);
    }
    /**
     * Removes an owner from the field.<br>
     * If the field has no owner, nothing happens.
     * @param fieldNumber : int [1:40]
     */
    public void removeOwner(int fieldNumber) {
        bc.removeOwner(fieldNumber);
    }
    /**
     * Sets houses from the street, and removes the hotel if one is present.<br>
     * If houseCount is out of bounds, nothing happens.<br>
     * If field is not a street, nothing happens.<br>
     * @param fieldNumber : int [1:40]
     * @param houseCount : int [0:4]
     */
    public void setHouses(int fieldNumber, int houseCount) {
        bc.setHouses(fieldNumber, houseCount);
    }
    /**
     * Sets whether or not a hotel should be on the street and removes all
     * houses if any is present.<br>
     * @param fieldNumber : int [1:40]
     * @param hasHotel : boolean
     */
    public void setHotel(int fieldNumber, boolean hasHotel) {
        bc.setHotel(fieldNumber, hasHotel);
    }
}
