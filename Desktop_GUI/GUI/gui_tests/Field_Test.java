package gui_tests;

import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Field_Test {
    

    public static void main(String[] args) {
        GUI_Field[] fields = GUI_FieldFactory.makeFields();
        

        GUI_Field[] fields2 = new GUI_Field[24];
        for (int i = 0; i < fields2.length; i++) {
            fields2[i] = fields[i];
            
            
        }
        GUI gui = new GUI(fields2);
        System.out.println(gui.getUserButtonPressed("test", "test1","test2"));
        gui.getUserString("test");
        gui.showMessage("test");
        gui.addPlayer(new GUI_Player("Brian123456789012345667"));
        gui.addPlayer(new GUI_Player("Brian123123213213213213"));
        gui.addPlayer(new GUI_Player("Brian2"));
        gui.addPlayer(new GUI_Player("Brian3"));
        fields[23].setSubText("No Luck");
        
        for(int i = 0;i<100;i++) {
            gui.setDice(1, 2);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

        
        
        
    }
}
