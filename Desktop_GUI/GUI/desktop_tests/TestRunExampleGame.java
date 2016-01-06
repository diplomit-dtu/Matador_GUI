package desktop_tests;

import desktop_resources.GUI;


/**
 * @author Daniel Kolditz Rubin-Gr�n
 */
public class TestRunExampleGame{

	public static void main(String[] args){
		realExampleGame();
	}

	private static void realExampleGame(){
		// Setup game
	    GUI gui = new GUI();
		sleep();
		gui.addPlayer("Mads", 30000);
		sleep();
		gui.addPlayer("Stig", 30000);
		sleep();
		gui.setCar(1, "Mads");
		sleep();
		gui.setCar(1, "Stig");
		
		// Move player 1
		sleep();
		gui.setDice(1, 2);
		sleep();
		gui.removeAllCars("Mads");
		gui.setCar(2, "Mads");
		sleep();
		gui.removeAllCars("Mads");
		gui.setCar(3, "Mads");
		sleep();
		gui.removeAllCars("Mads");
		gui.setCar(4, "Mads");
		sleep();
		gui.setBalance("Mads", 28000);
		gui.setOwner(4, "Mads");
		sleep();
//        gui.setNextChanceCardText("De har modtaget Bjørne Bandit - legatet og fængsles!");
		gui.displayChanceCard("De har vundet vild med dans og skifter navn til Allan!");
	}
	public static void sleep(){
		sleep(1200);
	}
	public static void sleep(int n){
		long t0, t1;
		t0 = System.currentTimeMillis();
		do{
			t1 = System.currentTimeMillis();
		}
		while((t1 - t0) < (n));
	}
}