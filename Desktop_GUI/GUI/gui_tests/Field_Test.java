package gui_tests;

import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_main.GUI;

public class Field_Test {
    
    private static Object[] array;

    public static void main(String[] args) {
        GUI_Field[] fields = GUI_FieldFactory.makeFields();
        
        fields[19] = null;
        fields[20] = null;
        fields[21] = null;  
        GUI_Chance gui_Chance = new GUI_Chance();
        fields[23] = gui_Chance;

        GUI.setNull_fields_allowed(true);
        GUI gui = new GUI(fields);
        gui.showMessage("test");
        gui.getUserString("test");
        
        fields[23].setSubText("No Luck");

        
        
        
    }
}
