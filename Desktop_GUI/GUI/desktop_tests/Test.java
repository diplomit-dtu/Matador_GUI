package desktop_tests;

import java.awt.Color;
import desktop_codebehind.Car;
import desktop_resources.GUI;

public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
		test.setDiceAngleAndCoordinates();
		test.testClose();
		// GUI.create("C:\\Users\\Ronnie\\Desktop\\fields.txt");
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
		GUI.setDice(6, 0, 1, 1, 6, 90, 2, 1);
	}
	private void testInput() {
		System.out.println("testInput:"
			+ GUI.getUserSelection("Vælg en grund", "Hvidovrevej", "Rødovrevej", "Peters vej",
				"Oskars vej") + ".");
		// System.out.println(GUI.getUserLeftButtonPressed("Vil du gå fallit?", "Yes", "No"));
		// System.out.println("testInput:" + GUI.getUserButtonPressed("Vælg en grund",
		// "Hvidovrevej", "Rødovrevej", "Peters vej", "Oskars vej") + ".");
		// int returInt = GUI.getUserInteger("Indtast tal [0-999999999]");
		// int returInt = GUI.getUserInteger("Indtast tal [2-22]", 2, 22);
		// String retur = GUI.getUserString("222");
		// System.out.println("Retur fra getUserInteger: " + returInt);
		// GUI.showMessage("HEJ");
	}
	private void testClose() {
		GUI.setTitleText(4, "TestingClose");
		GUI.close();
	}
	private void testSetText() {
		GUI.setTitleText(3, "Ocean Blv.");
		GUI.setSubText(3, "Price: 1 mio.");
		GUI.setDescriptionText(
			3,
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ac libero lorem. Aliquam ligula mauris, luctus interdum malesuada non, adipiscing ut mauris.");
		GUI.setTitleText(34, "Start");
		GUI.setSubText(1, "Daniel2");
		GUI.setDescriptionText(
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
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
		// Brewery - 13
		fieldNr = 13;
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
		// Chance - 3
		fieldNr = 3;
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
		// Jail - 11
		fieldNr = 11;
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
		// Refuge - 21
		fieldNr = 21;
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
		// Shipping - 6
		fieldNr = 6;
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
		// Street - 2
		fieldNr = 2;
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
		// Tax - 5
		fieldNr = 5;
		GUI.setDescriptionText(fieldNr, desc + fieldNr);
		GUI.setSubText(fieldNr, sub + fieldNr);
		GUI.setTitleText(fieldNr, title + fieldNr);
	}
	private void testAddPlayer() {
		GUI.addPlayer("Arthur Dent", 1000);
		
		Car car;
		
		car = new Car.Builder()
			.primaryColor(Color.MAGENTA)
			.secondaryColor(Color.BLUE)
			.typeTractor()
			.patternDotted()
			.build();
		GUI.addPlayer("Ford Prefect", 1000, car);
		
		car = new Car.Builder()
			.primaryColor(Color.BLACK)
			.secondaryColor(Color.RED)
			.typeUfo()
			.patternZebra()
			.build();
		GUI.addPlayer("Zaphod Beeblebrox", 100000, car);
		
		car = new Car.Builder()
			.primaryColor(Color.DARK_GRAY)
			.secondaryColor(Color.CYAN)
			.typeRacecar()
			.patternHorizontalLine()
			.build();
		GUI.addPlayer("Tricia McMillan", 100000, car);
		
		car = new Car.Builder()
			.primaryColor(new Color(160, 32, 240))
			.secondaryColor(Color.YELLOW)
			.patternHorizontalGradiant()
			.build();
		GUI.addPlayer("Marvin", 1000, car);
		
		car = new Car.Builder()
			.primaryColor(Color.BLACK)
			.secondaryColor(Color.WHITE)
			.patternCheckered()
			.build();
		GUI.addPlayer("Slartibartfast", 100000, car);
		
		GUI.addPlayer("Deep Thought", 100000);
	}
	private void testSetBalance() {
		GUI.setBalance("Ford Prefect", 100);
	}
	private void testSetDice() {
		// int d1 = (int)(Math.random()*6+1);
		// int d2 = (int)(Math.random()*6+1);
		// GUI.setDice(d1, d2);
		GUI.setDice(2, 3);
	}
	private void testSetDiceAllAngles() {
		for(int a = 0; a <= 360; a++) {
			GUI.setDice(5, a, 1, 1, 6, 359 - a, 2, 1);
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
				GUI.setDice(d1, a1, 2, 2, d2, a2, x, y);
				try {
					Thread.sleep(25);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	private void testSetNextChanceCardText() {
		GUI.displayChanceCard("De har modtaget Bjørne Bandit - legatet og fængsles!");
	}
	private void testSetCar() {
		for(int i = 1; i <= 10; i++) {
			GUI.setCar(i, "Zaphod Beeblebrox");
		}
		for(int i = 11; i <= 20; i++) {
			GUI.setCar(i, "Tricia McMillan");
			GUI.setCar(i, "Ford Prefect");
		}
		for(int i = 21; i <= 30; i++) {
			GUI.setCar(i, "Arthur Dent");
			GUI.setCar(i, "Marvin");
			GUI.setCar(i, "Slartibartfast");
		}
		for(int i = 31; i <= 40; i++) {
			GUI.setCar(i, "Tricia McMillan");
			GUI.setCar(i, "Zaphod Beeblebrox");
			GUI.setCar(i, "Arthur Dent");
			GUI.setCar(i, "Ford Prefect");
			GUI.setCar(i, "Marvin");
			GUI.setCar(i, "Slartibartfast");
		}
	}
	private void testRemoveCar() {
		GUI.removeAllCars("Zaphod Beeblebrox");
		GUI.removeAllCars("Tricia McMillan");
		GUI.removeAllCars("Ford Prefect");
		GUI.removeAllCars("Arthur Dent");
		GUI.removeAllCars("Marvin");
		GUI.removeAllCars("Slartibartfast");
		
		GUI.removeAllCars("Tricia McMillan");
		GUI.removeAllCars("Zaphod Beeblebrox");
		GUI.removeAllCars("Arthur Dent");
		GUI.removeAllCars("Ford Prefect");
		GUI.removeAllCars("Marvin");
		GUI.removeAllCars("Slartibartfast");
	}
	private void testSetOwner() {
		for(int i = 1; i <= 10; i++) {
			GUI.setOwner(i, "Ford Prefect");
		}
		for(int i = 11; i <= 20; i++) {
			GUI.setOwner(i, "Slartibartfast");
		}
		for(int i = 21; i <= 30; i++) {
			GUI.setOwner(i, "Arthur Dent");
		}
	}
	private void testRemoveOwner() {
		for(int i = 1; i <= 5; i++) {
			GUI.removeOwner(i);
		}
		for(int i = 11; i <= 15; i++) {
			GUI.removeOwner(i);
		}
		for(int i = 21; i <= 25; i++) {
			GUI.removeOwner(i);
		}
	}
	private void testSetHouses() {
		for(int i = 1; i <= 10; i++) {
			GUI.setHouses(i, 1);
		}
		for(int i = 11; i <= 20; i++) {
			GUI.setHouses(i, 2);
		}
		for(int i = 21; i <= 30; i++) {
			GUI.setHouses(i, 3);
		}
		for(int i = 31; i <= 40; i++) {
			GUI.setHouses(i, 4);
		}
		GUI.setHotel(38, true);
		GUI.setHotel(40, true);
	}
	private void testRemoveHouses() {
		GUI.setHouses(2, 0);
		GUI.setHouses(12, 0);
		GUI.setHouses(22, 0);
		GUI.setHouses(32, 0);
		GUI.setHotel(38, false);
	}
}
