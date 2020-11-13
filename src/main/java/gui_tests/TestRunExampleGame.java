package gui_tests;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;


/**
 * @author Daniel Kolditz Rubin-Grøn
 */
public class TestRunExampleGame{

	public static void main(String[] args){
		realExampleGame();
	}

	private static void realExampleGame(){
		// Setup game
	    GUI_Player mn = new GUI_Player("Mads", 30000), sh = new GUI_Player("Stig", 30000);
	    
	    GUI gui = new GUI();
		sleep();
		gui.addPlayer(mn);
		sleep();
		gui.addPlayer(sh);
		sleep();
		gui.getFields()[0].setCar(mn, true);
		sleep();
		gui.getFields()[0].setCar(sh, true);
		
		// Move player 1
		sleep();
		gui.setDice(1, 2);
		sleep();
		for(GUI_Field f : gui.getFields()) f.setCar(mn, false);
		gui.getFields()[1].setCar(mn, true);
		sleep();
		for(GUI_Field f : gui.getFields()) f.setCar(mn, false);
        gui.getFields()[2].setCar(mn, true);
		sleep();
		for(GUI_Field f : gui.getFields()) f.setCar(mn, false);
        gui.getFields()[3].setCar(mn, true);;
		sleep();
		mn.setBalance(28000);
		GUI_Field f = gui.getFields()[3];
        if(f instanceof GUI_Ownable){
            GUI_Ownable o = (GUI_Ownable) f;
            o.setBorder(mn.getPrimaryColor(), mn.getSecondaryColor());
        }
		sleep();
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