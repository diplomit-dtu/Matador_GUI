package desktop_fields;

import java.awt.Color;
import static desktop_board.Board.BASECOLOR;

public class GUI_Empty extends GUI_Field {
    
    public GUI_Empty(){
        this(BASECOLOR, Color.BLACK, "", "", "");
    }
    public GUI_Empty(Color bgColor, Color fgColor, String title, String subText, String description) {
        super(bgColor, fgColor, subText, description, null);
    }
    @Override
    public void displayOnCenter() { }
}
