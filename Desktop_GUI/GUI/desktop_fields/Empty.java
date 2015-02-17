package desktop_fields;

import java.awt.Color;
import desktop_board.Board;

public class Empty extends Field {
    
    public static class Builder extends Field.Builder<Empty.Builder> implements
        iBuilder {
        public Builder() {
            this.title = "";
            this.subText = "";
            this.description = "";
            this.bgColor = Board.BASECOLOR;
            this.fgColor = Color.BLACK;
        }
        
        @Override
        @SuppressWarnings("synthetic-access")
        public Empty build() {
            return new Empty(this.bgColor, this.fgColor, this.title, this.subText, this.description);
        }
        
        @Override
        @SuppressWarnings("unused")
        public Empty.Builder setFgColor(Color fgColor) {
            return this;
        }
    }
    
    private Empty(Color bgColor, Color fgColor, String title, String subText,
        String description) {
        super(bgColor, fgColor, title, subText, description, null);
    }
    @Override
    public void displayOnCenter() { }
}
