package desktop_tests;

import java.awt.Color;
import desktop_codebehind.GUI_Car;
import desktop_resources.GUI;

public class CarTest {
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        GUI_Car car = new GUI_Car.Builder()
            .typeRacecar()
            .primaryColor(Color.BLUE)
            .secondaryColor(Color.RED)
            .patternDiagonalDualColor()
            .build();
        gui.addPlayer("Sebastian Vettel", 25000, car);
    }
}
