package gui_fields;

import java.awt.Color;
import javax.swing.JLabel;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;
import gui_resources.Attrs;

public final class GUI_Chance extends GUI_Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public GUI_Chance(){
        this("<html><b><font size=\"7\">"+Attrs.getString("GUI_Field.Label.Chance.Format"),
            Attrs.getString("GUI_Field.Label.Chance.Subtext"), DESCRIPTION, new Color(204, 204, 204), FG_COLOR);
    }
    public GUI_Chance(String title, String subText, String description, Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeStreetNameLabel();
        this.subTextLabel = makeBottomLabel();
        this.layered.add(this.titleLabel, this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 1));
    }
    private JLabel makeStreetNameLabel() {
        JLabel roadnameLabel = makeLabel(TITLEHEIGHT);
        roadnameLabel.setText("<html><b><font size=\"7\">"+super.title.substring(super.title.lastIndexOf(">")+1)+"</b>");
        return roadnameLabel;
    }
    private JLabel makeBottomLabel() {
        JLabel bottomLabel = makeLabel(SUBTEXTHEIGHT);
        bottomLabel.setText(this.subText);
        return bottomLabel;
    }
    @Override
    protected void displayOnCenter(GUI_Player[] playerList) {
        super.displayOnCenter(playerList);
        String path = Attrs.getImagePath("GUI_Field.Image.Luck");
        GUI_Center.label[1].setIcon(this.factory.createIcon(path));
        GUI_Center.label[2].setText("__________________________");
        GUI_Center.label[3].setText(this.description);
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "GUI_Chance [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }

    
}
