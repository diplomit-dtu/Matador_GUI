package desktop_tests;

import java.awt.Color;
import desktop_codebehind.Car;
import desktop_resources.GUI;

public class Test {
    private GUI gui = new GUI();
	
	public static void main(String[] args) {
		Test test = new Test();
		test.setDiceAngleAndCoordinates();
		// gui.create("C:\\Users\\Ronnie\\Desktop\\fields.txt");
		test.testSetText();
		test.testSetTexts();
		test.testAddPlayer();
		test.testSetBalance();
		test.testSetDice();
		test.testInput();
		test.testSetNextChanceCardText();
		test.testSetCar();
		test.testSetOwner();
		test.testSetDiceAngleAndCoordinatesMultipleTimes();
		test.testSetDiceAllAngles();
		test.testRemoveCar();
		test.testRemoveOwner();
		test.testSetHouses();
		test.testRemoveHouses();
		try {
			Thread.sleep(2000);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		// System.exit(0);
	}
	
	private void setDiceAngleAndCoordinates() {
		gui.setDice(6, 0, 1, 1, 6, 90, 2, 1);
	}
	private void testInput() {
		System.out.println("testInput:"
			+ gui.getUserSelection("Vælg en grund", "Hvidovrevej", "Rødovrevej", "Peters vej",
				"Oskars vej") + ".");
		// System.out.println(gui.getUserLeftButtonPressed("Vil du gå fallit?", "Yes", "No"));
		// System.out.println("testInput:" + gui.getUserButtonPressed("Vælg en grund",
		// "Hvidovrevej", "Rødovrevej", "Peters vej", "Oskars vej") + ".");
		// int returInt = gui.getUserInteger("Indtast tal [0-999999999]");
		// int returInt = gui.getUserInteger("Indtast tal [2-22]", 2, 22);
		// String retur = gui.getUserString("222");
		// System.out.println("Retur fra getUserInteger: " + returInt);
		// gui.showMessage("HEJ");
	}
	
	private void testSetText() {
		gui.setTitleText(3, "Ocean Blv.");
		gui.setSubText(3, "Price: 1 mio.");
		gui.setDescriptionText(
			3,
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ac libero lorem. Aliquam ligula mauris, luctus interdum malesuada non, adipiscing ut mauris.");
		gui.setTitleText(34, "Start");
		gui.setSubText(1, "Daniel2");
		gui.setDescriptionText(
			1,
			"Daniel3Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ac libero lorem. Aliquam ligula mauris, luctus interdum malesuada non, adipiscing ut mauris.");
	}
	private void testSetTexts() {
		int fieldNr = 0;
		String desc = "description";
		String sub = "subtext";
		String title = "title";
		// Start - 1
		fieldNr = 34;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
		// Brewery - 13
		fieldNr = 13;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
		// Chance - 3
		fieldNr = 3;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
		// Jail - 11
		fieldNr = 11;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
		// Refuge - 21
		fieldNr = 21;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
		// Shipping - 6
		fieldNr = 6;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
		// Street - 2
		fieldNr = 2;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
		// Tax - 5
		fieldNr = 5;
		gui.setDescriptionText(fieldNr, desc + fieldNr);
		gui.setSubText(fieldNr, sub + fieldNr);
		gui.setTitleText(fieldNr, title + fieldNr);
	}
	private void testAddPlayer() {
		gui.addPlayer("Arthur Dent", 1000);
		
		Car car;
		
		car = new Car.Builder()
			.primaryColor(Color.MAGENTA)
			.secondaryColor(Color.BLUE)
			.typeTractor()
			.patternDotted()
			.build();
		gui.addPlayer("Ford Prefect", 1000, car);
		
		car = new Car.Builder()
			.primaryColor(Color.BLACK)
			.secondaryColor(Color.RED)
			.typeUfo()
			.patternZebra()
			.build();
		gui.addPlayer("Zaphod Beeblebrox", 100000, car);
		
		car = new Car.Builder()
			.primaryColor(Color.DARK_GRAY)
			.secondaryColor(Color.CYAN)
			.typeRacecar()
			.patternHorizontalLine()
			.build();
		gui.addPlayer("Tricia McMillan", 100000, car);
		
		car = new Car.Builder()
			.primaryColor(new Color(160, 32, 240))
			.secondaryColor(Color.YELLOW)
			.patternHorizontalGradiant()
			.build();
		gui.addPlayer("Marvin", 1000, car);
		
		car = new Car.Builder()
			.primaryColor(Color.BLACK)
			.secondaryColor(Color.WHITE)
			.patternCheckered()
			.build();
		gui.addPlayer("Slartibartfast", 100000, car);
		
		gui.addPlayer("Deep Thought", 100000);
	}
	private void testSetBalance() {
		gui.setBalance("Ford Prefect", 100);
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
		for(int i = 1; i <= 10; i++) {
			gui.setCar(i, "Zaphod Beeblebrox");
		}
		for(int i = 11; i <= 20; i++) {
			gui.setCar(i, "Tricia McMillan");
			gui.setCar(i, "Ford Prefect");
		}
		for(int i = 21; i <= 30; i++) {
			gui.setCar(i, "Arthur Dent");
			gui.setCar(i, "Marvin");
			gui.setCar(i, "Slartibartfast");
		}
		for(int i = 31; i <= 40; i++) {
			gui.setCar(i, "Tricia McMillan");
			gui.setCar(i, "Zaphod Beeblebrox");
			gui.setCar(i, "Arthur Dent");
			gui.setCar(i, "Ford Prefect");
			gui.setCar(i, "Marvin");
			gui.setCar(i, "Slartibartfast");
		}
	}
	private void testRemoveCar() {
		gui.removeAllCars("Zaphod Beeblebrox");
		gui.removeAllCars("Tricia McMillan");
		gui.removeAllCars("Ford Prefect");
		gui.removeAllCars("Arthur Dent");
		gui.removeAllCars("Marvin");
		gui.removeAllCars("Slartibartfast");
		
		gui.removeAllCars("Tricia McMillan");
		gui.removeAllCars("Zaphod Beeblebrox");
		gui.removeAllCars("Arthur Dent");
		gui.removeAllCars("Ford Prefect");
		gui.removeAllCars("Marvin");
		gui.removeAllCars("Slartibartfast");
	}
	private void testSetOwner() {
		for(int i = 1; i <= 10; i++) {
			gui.setOwner(i, "Ford Prefect");
		}
		for(int i = 11; i <= 20; i++) {
			gui.setOwner(i, "Slartibartfast");
		}
		for(int i = 21; i <= 30; i++) {
			gui.setOwner(i, "Arthur Dent");
		}
	}
	private void testRemoveOwner() {
		for(int i = 1; i <= 5; i++) {
			gui.removeOwner(i);
		}
		for(int i = 11; i <= 15; i++) {
			gui.removeOwner(i);
		}
		for(int i = 21; i <= 25; i++) {
			gui.removeOwner(i);
		}
	}
	private void testSetHouses() {
		for(int i = 1; i <= 10; i++) {
			gui.setHouses(i, 1);
		}
		for(int i = 11; i <= 20; i++) {
			gui.setHouses(i, 2);
		}
		for(int i = 21; i <= 30; i++) {
			gui.setHouses(i, 3);
		}
		for(int i = 31; i <= 40; i++) {
			gui.setHouses(i, 4);
		}
		gui.setHotel(38, true);
		gui.setHotel(40, true);
	}
	private void testRemoveHouses() {
		gui.setHouses(2, 0);
		gui.setHouses(12, 0);
		gui.setHouses(22, 0);
		gui.setHouses(32, 0);
		gui.setHotel(38, false);
	}
}
