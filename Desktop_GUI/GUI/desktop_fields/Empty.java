package desktop_fields;

import java.awt.Color;
import desktop_board.Board;

public class Empty extends Field {
    
    public Empty(){
        this(Board.BASECOLOR, Color.BLACK, "", "", "");
    }
    public Empty(Color bgColor, Color fgColor, String title, String subText, String description) {
        super(bgColor, fgColor, title, subText, description, null);
    }
    @Override
    public void displayOnCenter() { }
}
