package desktop_tests;

import java.awt.Color;
import java.util.ArrayList;
import desktop_codebehind.Car;
import desktop_fields.*;
import desktop_resources.GUI;

public class TestCreate {
    
    public static void main(String[] args) {
        ArrayList<Field> list = new ArrayList<Field>();
        
        list.add(new Start.Builder().setTitle("Title").setBgColor(Color.RED).build());
        list.add(new Tax.Builder().build());
        list.add(new Street.Builder().build());
        list.add(new Shipping.Builder().build());
        list.add(new Refuge.Builder().build());
        list.add(new Jail.Builder().build());
        list.add(new Chance.Builder().build());
        list.add(new Empty.Builder().build());
        list.add(new Empty.Builder().setBgColor(Color.MAGENTA).build());
        list.add(new Empty.Builder().setFgColor(Color.YELLOW).build());
        list.add(new Brewery.Builder().build());
        
        Field[] fields = new Field[list.size()];
        for(int i = 0; i < fields.length; i++) fields[i] = list.get(i);
      
        GUI.create(fields);
        Car car = new Car.Builder()
            .typeRacecar()
            .primaryColor(Color.BLUE)
            .secondaryColor(Color.RED)
            .patternDiagonalDualColor()
            .build();
        GUI.addPlayer("Sebastian Vettel", 25000, car);
    }
}
