package gui_tests;

import java.awt.Color;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class PlayerTest {
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        
        GUI_Car car1 = new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.HORIZONTAL_GRADIANT);
        GUI_Player player1 = new GUI_Player("Sebastian Vettel", 1000, car1);
        gui.addPlayer(player1);
        
        GUI_Car car2 = new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.HORIZONTAL_GRADIANT);
        GUI_Player player2 = new GUI_Player("Michael Schumacher", 1000, car2);
        gui.addPlayer(player2);
        
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        car1.setPrimaryColor(Color.YELLOW);
        player1.setName(player2.getName());
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        car1.setSecondaryColor(Color.BLUE);
        player1.setName("Fabio Alonso");
        player2.setBalance(99999);
    }
}
