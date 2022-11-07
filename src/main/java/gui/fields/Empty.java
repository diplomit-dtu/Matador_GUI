package gui.fields;

import static gui.fields.Board.BASECOLOR;
import java.awt.Color;

public class Empty extends Field {
    
    public Empty(){
        this(BASECOLOR, Color.BLACK, "", "", "");
    }
    public Empty(Color bgColor, Color fgColor, String title, String subText, String description) {
        super(bgColor, fgColor, title, subText, description, null);
    }
    @Override
    protected void displayOnCenter(Player[] playerList) { }
    
    @Override
    public String toString() {
        return "Empty [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }

    
}
