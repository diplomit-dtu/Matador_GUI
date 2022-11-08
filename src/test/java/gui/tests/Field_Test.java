package gui.tests;

import gui.util.FieldFactory;
import gui.core.Field;
import gui.core.Player;
import gui.main.GUI;

public class Field_Test {
    

    public static void main(String[] args) {
        Field[] fields = FieldFactory.makeFields();
        

        Field[] fields2 = new Field[24];
        for (int i = 0; i < fields2.length; i++) {
            fields2[i] = fields[i];
            
            
        }
        GUI gui = new GUI(fields2);
        System.out.println(gui.getUserButtonPressed("test\ntest", "test1","test2"));
        gui.getUserString("test\ntest");
        gui.showMessage("test\ntest");
        Player player = new Player("Brian123456789012345667");
        gui.addPlayer(player);
        gui.addPlayer(new Player("Brian123123213213213213"));
        gui.addPlayer(new Player("Brian2"));
        gui.addPlayer(new Player("Brian3"));
        fields[23].setSubText("No Luck");
        fields[0].setCar(player, true);
        fields[0].setCar(player, false);
        System.out.println(fields[0].hasCar(player));

        for(int i = 0;i<100;i++) {
            gui.setDie(2);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        gui.close();
        
        
    }
}
