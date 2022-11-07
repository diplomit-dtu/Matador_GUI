package gui.fields;

import java.awt.Color;
import javax.swing.JLabel;
import gui.codebehind.GUI_Center;
import gui.codebehind.SwingComponentFactory;

public final class GUI_Tax extends GUI_Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public GUI_Tax(){
        this(TITLE, SUBTEXT, DESCRIPTION, BG_COLOR, FG_COLOR);
    }

    public GUI_Tax(String title, String subText, String description, Color bgColor, Color fgColor){
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeTitleLabel(this.title);
        this.subTextLabel = makeSubTextLabel(this.subText);
        this.layered.add(this.titleLabel, this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 1));
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
    protected void displayOnCenter(GUI_Player[] playerList) {
        super.displayOnCenter(playerList);
        GUI_Center.label[1].setText("__________________________");
        GUI_Center.label[2].setText(this.description);
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "GUI_Tax [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }

    
    
}
