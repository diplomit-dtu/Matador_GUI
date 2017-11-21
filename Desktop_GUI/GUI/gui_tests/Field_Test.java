package gui_tests;

import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_main.GUI;

public class Field_Test {
    
    private static Object[] array;

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
        
        
        
        fields[23].setSubText("No Luck");

        
        
        
    }
}
