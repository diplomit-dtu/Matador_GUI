package gui.tests;

import java.awt.Color;

import gui.core.Car;
import gui.core.Player;
import gui.main.GUI;

public class PlayerTest {
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        
        Car car1 = new Car(Color.RED, Color.BLACK, Car.Type.RACECAR, Car.Pattern.HORIZONTAL_GRADIANT);
        Player player1 = new Player("Sebastian Vettel", 1000, car1);
        gui.addPlayer(player1);
        
        Car car2 = new Car(Color.RED, Color.BLACK, Car.Type.RACECAR, Car.Pattern.HORIZONTAL_GRADIANT);
        Player player2 = new Player("Michael Schumacher", 1000, car2);
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
