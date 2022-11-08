package gui.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import gui.util.Observable;
import gui.main.GUI;
import gui.util.Attrs;


/**
 * Class which represents the player object, and the information displayed on the
 * GUI regarding the player. This includes:
 *
 *  - Name (displayed on the board)
 *  - Balance (displayed on the board)
 *  - Car (position and color is determined by the car object, not the player)
 *
 * Once an object of this class is constructed (a player is created), it should be
 * added to the GUI using the {@link GUI#addPlayer(Player)} method.
 *
 * Updating the Player object using the set methods, will also update the information
 * displayed on within the GUI.
 *
 * The position of the player (the player's car to be exact) is set  using the
 * {@link Field#setCar(Player, boolean)} method on the particular Field object.
 *
 * @author Ronnie
 */
public class Player extends Observable {
	private int number = -1;
	private String name;
	private int balance;
	private final Car car;
	private static int nextId = 0;
    private final int id;
	
	public static final int ICON_WIDTH = 41;
	public static final int ICON_HEIGHT = 22;


	// TODO: Remove this constructor, as it implements game logic
	/**
	 * Constructs a new Player with a given name,
	 * default balance of 1000 and a car with a random color.
	 *
	 * @param name Name of the player to be displayed on the board.
	 */
	public Player(String name){
	    this(name, 1000, new Car());
	}


	/**
	 * Constructs a new Player with a given name and balance,
	 * and a car with a random color.
	 *
	 * @param name Name of the player to be displayed on the board
	 * @param balance Balance of the player to be displayed on the board
	 */
	public Player(String name, int balance){
	    this(name, balance, new Car());
	}


	/**
	 * Constructs a new Player with a given name, balance and
	 * custom car object.
	 *
	 * @param name Name of the player to be displayed on the board
	 * @param balance Balance of the player to be displayed on the board
	 * @param car Car object for to use
	 */
	public Player(String name, int balance, Car car){
		this.name = name;
		this.balance = balance;
		this.car = car;
		this.id = nextId++;
	}


	//Getters
	public int getNumber(){ return this.number; }
	public String getName(){ return this.name; }
	public int getBalance(){ return this.balance; }
	public Color getPrimaryColor(){ return this.car.getPrimaryColor(); }
	public Color getSecondaryColor(){ return this.car.getSecondaryColor(); }
	public BufferedImage getImage() { return this.car.getImage(); }
    public Car getCar() { return car; }
    public int getId(){ return id; }
	
	//Setters
    protected void setNumber(int number) { this.number = number; }
    public boolean setName(String name){
        if(validator == null) return false;
        if(!validator.checkName(name)) {
            System.err.println(Attrs.getString("Error.Conflict.PlayerName", name));
            return false;
        }
        this.name = name;
        notifyObservers();
        return true;
    }
	public void setBalance(int balance){ 
	    this.balance = balance;
	    notifyObservers();
	}
	
	
	// Mandatory
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		if (this.name == null) {
			if(other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}


	public interface IPlayerNameValidator {
		boolean checkName(String name);
	}

	private IPlayerNameValidator validator = null;

	protected void setValidator(IPlayerNameValidator validator){
		this.validator = validator;
	}
    
	
	@Override
    public String toString() {
        return "Player [number=" + number + ", name=" + name + ", balance="
            + balance + ", car=" + car + "]";
    }

}