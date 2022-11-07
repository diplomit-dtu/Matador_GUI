package gui.tests;

import java.awt.Color;

import gui.fields.*;
import gui.fields.Car;
import gui.fields.Car.Pattern;
import gui.fields.Car.Type;
import gui.main.GUI;

public class Test {
    private GUI gui = new GUI();
    private Player arthur, ford, zaphod, tricia, marvin, slart, dt;
	
	public static void main(String[] args) {
		Test test = new Test();
		test.test();
	}
	
	private void test(){
	    setDiceAngleAndCoordinates();
//	    testSetText();
//	    testSetTexts();
	    testAddPlayer();
	    testSetBalance();
	    testSetDice();
	    testInput();
	    testSetNextChanceCardText();
	    testSetCar();
	    testSetOwner();
	    testSetDiceAngleAndCoordinatesMultipleTimes();
	    testSetDiceAllAngles();
	    testRemoveCar();
	    testRemoveOwner();
	    testSetHouses();
	    testRemoveHouses();
	    	    
	}
	
	private void setDiceAngleAndCoordinates() {
		gui.setDice(6, 0, 1, 1, 6, 90, 2, 1);
	}
	private void testInput() {
		System.out.println("testInput:"
			+ gui.getUserSelection("Vælg en grund", "Hvidovrevej", "Rødovrevej", "Peters vej",
				"Oskars vej") + "");
		// System.out.println(gui.getUserLeftButtonPressed("Vil du gå fallit?", "Yes", "No"));
		// System.out.println("testInput:" + gui.getUserButtonPressed("Vælg en grund",
		// "Hvidovrevej", "Rødovrevej", "Peters vej", "Oskars vej") + ".");
		// int returInt = gui.getUserInteger("Indtast tal [0-999999999]");
		// int returInt = gui.getUserInteger("Indtast tal [2-22]", 2, 22);
		// String retur = gui.getUserString("222");
		// System.out.println("Retur fra getUserInteger: " + returInt);
		// gui.showMessage("HEJ");
	}
	
//	private void testSetText() {
//		gui.setTitleText(3, "Ocean Blv.");
//		gui.setSubText(3, "Price: 1 mio.");
//		gui.setDescriptionText(
//			3,
//			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ac libero lorem. Aliquam ligula mauris, luctus interdum malesuada non, adipiscing ut mauris.");
//		gui.setTitleText(34, "Start");
//		gui.setSubText(1, "Daniel2");
//		gui.setDescriptionText(
//			1,
//			"Daniel3Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ac libero lorem. Aliquam ligula mauris, luctus interdum malesuada non, adipiscing ut mauris.");
//	}
//	private void testSetTexts() {
//		int fieldNr = 0;
//		String desc = "description";
//		String sub = "subtext";
//		String title = "title";
//		// Start - 1
//		fieldNr = 34;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//		// Brewery - 13
//		fieldNr = 13;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//		// Chance - 3
//		fieldNr = 3;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//		// Jail - 11
//		fieldNr = 11;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//		// Refuge - 21
//		fieldNr = 21;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//		// Shipping - 6
//		fieldNr = 6;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//		// Street - 2
//		fieldNr = 2;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//		// Tax - 5
//		fieldNr = 5;
//		gui.setDescriptionText(fieldNr, desc + fieldNr);
//		gui.setSubText(fieldNr, sub + fieldNr);
//		gui.setTitleText(fieldNr, title + fieldNr);
//	}
	private void testAddPlayer() {
	    arthur = new Player("Arthur Dent", 1000);
		gui.addPlayer(arthur);
		
		Car car1 = new Car(Color.MAGENTA, Color.BLUE, Type.TRACTOR, Pattern.DOTTED);
		ford = new Player("Ford Prefect", 1000, car1);
		gui.addPlayer(ford);
		
		Car car2 = new Car(Color.BLACK, Color.RED, Type.UFO, Pattern.ZEBRA);
		zaphod = new Player("Zaphod Beeblebrox", 100000, car2);
		gui.addPlayer(zaphod);
		
		Car car3 = new Car(Color.DARK_GRAY, Color.CYAN, Type.RACECAR, Pattern.HORIZONTAL_LINE);
		tricia = new Player("Tricia McMillan", 100000, car3);
		gui.addPlayer(tricia);
		
		Car car4 = new Car(new Color(160, 32, 240), Color.YELLOW, Type.CAR, Pattern.CHECKERED);
		marvin = new Player("Marvin", 1000, car4);
		gui.addPlayer(marvin);
		
		Car car5 = new Car(Color.BLACK, Color.WHITE, Type.CAR, Pattern.DOTTED);
		slart = new Player("Slartibartfast", 100000, car5);
		gui.addPlayer(slart);
		
		dt = new Player("Deep Thought", 100000);
		gui.addPlayer(dt);
	}
	private void testSetBalance() {
		ford.setBalance(100);
	}
	private void testSetDice() {
		// int d1 = (int)(Math.random()*6+1);
		// int d2 = (int)(Math.random()*6+1);
		// gui.setDice(d1, d2);
		gui.setDice(2, 3);
	}
	private void testSetDiceAllAngles() {
		for(int a = 0; a <= 360; a++) {
			gui.setDice(5, a, 1, 1, 6, 359 - a, 2, 1);
			try {
				Thread.sleep(5);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	private void testSetDiceAngleAndCoordinatesMultipleTimes() {
		for(int y = -1; y <= 11; y++) {
			for(int x = -1; x <= 11; x++) {
				int d1 = (int) (Math.random() * 6 + 1);
				int a1 = (int) (Math.random() * 360);
				int d2 = (int) (Math.random() * 6 + 1);
				int a2 = (int) (Math.random() * 360);
				gui.setDice(d1, a1, 2, 2, d2, a2, x, y);
				try {
					Thread.sleep(25);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	private void testSetNextChanceCardText() {
		gui.displayChanceCard("De har modtaget Bjørne Bandit - legatet og fængsles!");
	}
	private void testSetCar() {
		for(int i = 0; i < 10; i++) {
			gui.getFields()[i].setCar(zaphod, true);
		}
		for(int i = 10; i < 20; i++) {
		    gui.getFields()[i].setCar(tricia, true);
		    gui.getFields()[i].setCar(ford, true);
		}
		for(int i = 20; i < 30; i++) {
		    gui.getFields()[i].setCar(arthur, true);
		    gui.getFields()[i].setCar(marvin, true);
		    gui.getFields()[i].setCar(slart, true);
		}
		for(int i = 30; i < 40; i++) {
		    gui.getFields()[i].setCar(tricia, true);
			gui.getFields()[i].setCar(zaphod, true);
			gui.getFields()[i].setCar(arthur, true);
			gui.getFields()[i].setCar(ford, true);
			gui.getFields()[i].setCar(marvin, true);
			gui.getFields()[i].setCar(slart, true);
		}
	}
	private void testRemoveCar() {
	    for(Field f : gui.getFields()){
	        f.removeAllCars();
	    }
	}
	private void testSetOwner() {
		for(int i = 1; i <= 10; i++) {
		    Field f = gui.getFields()[i];
		    if(f instanceof Ownable){
		        Ownable o = (Ownable) f;
		        o.setBorder(ford.getPrimaryColor(), ford.getSecondaryColor());
		    }
		}
		for(int i = 11; i <= 20; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Ownable){
                Ownable o = (Ownable) f;
                o.setBorder(slart.getPrimaryColor(), slart.getSecondaryColor());
            }
		}
		for(int i = 21; i <= 30; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Ownable){
                Ownable o = (Ownable) f;
                o.setBorder(arthur.getPrimaryColor(), arthur.getSecondaryColor());
            }
		}
	}
	private void testRemoveOwner() {
		for(int i = 1; i <= 5; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Ownable){
                Ownable o = (Ownable) f;
                o.setBorder(null);
            }
		}
		for(int i = 11; i <= 15; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Ownable){
                Ownable o = (Ownable) f;
                o.setBorder(null);
            }
		}
		for(int i = 21; i <= 25; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Ownable){
                Ownable o = (Ownable) f;
                o.setBorder(null);
            }
		}
	}
	private void testSetHouses() {
		for(int i = 0; i < 10; i++) {
		    Field f = gui.getFields()[i];
		    if(f instanceof Street){
		        Street s = (Street) f;
		        s.setHouses(1);
		    }
		}
		for(int i = 10; i < 20; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Street){
                Street s = (Street) f;
                s.setHouses(2);
            }
		}
		for(int i = 20; i < 30; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Street){
                Street s = (Street) f;
                s.setHouses(3);
            }
		}
		for(int i = 30; i < 40; i++) {
		    Field f = gui.getFields()[i];
            if(f instanceof Street){
                Street s = (Street) f;
                s.setHouses(4);
            }
		}
		Field f38 = gui.getFields()[37];
		System.out.println(f38.getTitle());
        if(f38 instanceof Street){
            Street s = (Street) f38;
            s.setHotel(true);
        }
        Field f40 = gui.getFields()[39];
        System.out.println(f40.getTitle());
        if(f40 instanceof Street){
            Street s = (Street) f40;
            s.setHotel(true);
        }
	}
	private void testRemoveHouses() {
	    try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	    int i = 0;
	    for(Field f : gui.getFields()){
            if(i < 38 && i % 3 == 0){
                if(f instanceof Street){
                    Street s = (Street) f;
                    s.setHouses(0);
                }
            }
            i++;
        }
	    Street s40 = (Street)gui.getFields()[39];
	    s40.setHotel(false);
	}
}
