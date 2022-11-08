package gui.fields;

import gui.core.Field;
import gui.core.Player;

import static gui.core.Board.BASECOLOR;
import java.awt.Color;

public class Empty extends Field {
    
    public Empty(){
        this(BASECOLOR, Color.BLACK, "", "", "");
    }
    public Empty(Color bgColor, Color fgColor, String title, String subText, String description) {
        super(bgColor, fgColor, title, subText, description, null);
    }
    @Override
    public void displayOnCenter(Player[] playerList) { }
    
    @Override
    public String toString() {
        return "Empty [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }

    
}
