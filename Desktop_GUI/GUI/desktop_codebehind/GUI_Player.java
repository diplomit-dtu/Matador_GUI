package desktop_codebehind;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Player entity
 * @author Ronnie
 */
public class GUI_Player{
	private int number;
	private String name;
	private int balance;
	
	private GUI_Car car;
	public static final int ICON_WIDTH = 41;
	public static final int ICON_HEIGHT = 22;

	public GUI_Player(int number, String name, int balance, GUI_Car car){
		this.number = number;
		this.name = name;
		this.balance = balance;
		this.car = car;
		
	}
	public int getNumber(){
		return this.number;
	}
	public String getName(){
		return this.name;
	}
	public int getBalance(){
		return this.balance;
	}
	public void setBalance(int balance){
		this.balance = balance;
	}
	public Color getPrimaryColor(){
		return this.car.getPrimaryColor();
	}
	public Color getSecondaryColor(){
		return this.car.getSecondaryColor();
	}
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
		if (!(obj instanceof GUI_Player)) {
			return false;
		}
		GUI_Player other = (GUI_Player) obj;
		if (this.name == null) {
			if(other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}
	public BufferedImage getImage() {
		return this.car.getImage();
	}
}