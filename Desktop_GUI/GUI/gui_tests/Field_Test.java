package gui_tests;

import java.awt.Color;
import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Field;
import gui_main.GUI;

public class Field_Test {
    
    public static void main(String[] args) {
        GUI_Field[] fields = GUI_FieldFactory.makeFields();
        
        fields[19] = null;
        fields[20] = null;
        fields[21] = null;  
        GUI.setNull_fields_allowed(true);
        
        new GUI(fields);
        
        GUI_Field f = fields[1];
        f.setBackGroundColor(Color.ORANGE);
        f.setForeGroundColor(new Color(0,140,20));
        f.setTitle("TEST");
        f.setSubText("TEST");
        f.setNumber(0);
        f.setDescription("TEST");
        
        
        
    }
}
