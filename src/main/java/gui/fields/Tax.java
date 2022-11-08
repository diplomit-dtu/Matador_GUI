package gui.fields;

import java.awt.Color;
import javax.swing.JLabel;

import gui.core.Center;
import gui.util.SwingComponentFactory;
import gui.core.Field;
import gui.core.Player;

public final class Tax extends Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;

    public Tax(){
        this(TITLE, SUBTEXT, DESCRIPTION, BG_COLOR, FG_COLOR);
    }

    public Tax(String title, String subText, String description, Color bgColor, Color fgColor){
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeTitleLabel(this.title);
        this.subTextLabel = makeSubTextLabel(this.subText);
        SwingComponentFactory factory = new SwingComponentFactory();
        this.layered.add(this.titleLabel, factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, factory.createGridBagConstraints(0, 1));
    }
    private JLabel makeTitleLabel(String titleTax) {
        JLabel l = makeLabel(TITLEHEIGHT);
        l.setText(titleTax);
        return l;
    }
    private JLabel makeSubTextLabel(String subTextTax) {
        JLabel l = makeLabel(SUBTEXTHEIGHT);
        l.setText(subTextTax);
        return l;
    }
    @Override
    public void displayOnCenter(Player[] playerList) {
        super.displayOnCenter(playerList);
        Center.label[1].setText("__________________________");
        Center.label[2].setText(this.description);
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "Tax [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }

    
    
}
