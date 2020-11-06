package gui_fields;

import static gui_fields.GUI_Board.BASECOLOR;
import java.awt.Color;

public class GUI_Empty extends GUI_Field {
    
    public GUI_Empty(){
        this(BASECOLOR, Color.BLACK, "", "", "");
    }
    public GUI_Empty(Color bgColor, Color fgColor, String title, String subText, String description) {
        super(bgColor, fgColor, subText, description, null);
    }
    @Override
    protected void displayOnCenter(GUI_Player[] playerList) { }
    
    @Override
    public String toString() {
        return "GUI_Empty [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }

    
}
