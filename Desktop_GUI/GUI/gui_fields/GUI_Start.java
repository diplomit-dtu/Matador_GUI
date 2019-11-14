package gui_fields;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;

public final class GUI_Start extends GUI_Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public GUI_Start(){
        this(TITLE, SUBTEXT, DESCRIPTION, BG_COLOR, FG_COLOR);
    }

    public GUI_Start(String title, String subText, String description, Color bgColor, Color fgColor){
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeTitleLabel(this.title);
        this.subTextLabel = makeSubTextLabel(this.subText);
        this.layered.add(this.titleLabel, this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 1));
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
    protected void displayOnCenter(GUI_Player[] playerList) {
        super.displayOnCenter(playerList);
        GUI_Center.label[1].setText(this.title.replace("<html><center>", ""));
        GUI_Center.label[2].setText("__________________________");
        GUI_Center.label[3].setText(this.description);
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "GUI_Start [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }
    
        
}
