package gui.fields;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

import gui.core.Center;
import gui.util.SwingComponentFactory;
import gui.core.Field;
import gui.core.Player;

public final class Start extends Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;

    public Start(){
        this(TITLE, SUBTEXT, DESCRIPTION, BG_COLOR, FG_COLOR);
    }

    public Start(String title, String subText, String description, Color bgColor, Color fgColor){
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeTitleLabel(this.title);
        this.subTextLabel = makeSubTextLabel(this.subText);
        SwingComponentFactory factory = new SwingComponentFactory();
        this.layered.add(this.titleLabel, factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, factory.createGridBagConstraints(0, 1));
    }
    private JLabel makeTitleLabel(String titleStart) {
        JLabel l = makeLabel(TITLEHEIGHT);
        l.setText(titleStart);
        l.setFont(new Font(l.getFont().getName(), Font.BOLD, 18));
        return l;
    }
    private JLabel makeSubTextLabel(String subTextStart) {
        JLabel l = makeLabel(SUBTEXTHEIGHT);
        l.setText(subTextStart);
        return l;
    }
    @Override
    public void displayOnCenter(Player[] playerList) {
        super.displayOnCenter(playerList);
        Center.label[1].setText(this.title.replace("<html><center>", ""));
        Center.label[2].setText("__________________________");
        Center.label[3].setText(this.description);
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "Start [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }
    
        
}
