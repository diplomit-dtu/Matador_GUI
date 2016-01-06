package desktop_tests;

import desktop_resources.GUI;

public class Non_Static_Test {
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.addPlayer("Player 1", 9999);
        
        gui = new GUI();
        gui.addPlayer("Player 2", 1000);
        
    }
    
}
