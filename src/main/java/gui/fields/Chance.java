package gui.fields;

import java.awt.Color;
import java.net.URL;
import javax.swing.JLabel;

import gui.core.Center;
import gui.util.SwingComponentFactory;
import gui.core.Field;
import gui.core.Player;
import gui.util.Attrs;

public final class Chance extends Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private final SwingComponentFactory factory = new SwingComponentFactory();
    
    public Chance(){
        this("<html><b><font size=\"7\">"+Attrs.getString("Field.Label.Chance.Format"),
            Attrs.getString("Field.Label.Chance.Subtext"), DESCRIPTION, new Color(204, 204, 204), FG_COLOR);
    }
    public Chance(String title, String subText, String description, Color bgColor, Color fgColor) {
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
    public void displayOnCenter(Player[] playerList) {
        super.displayOnCenter(playerList);
        URL path = Attrs.getImagePath("Field.Image.Luck");
        Center.label[1].setIcon(this.factory.createIcon(path));
        Center.label[2].setText("__________________________");
        Center.label[3].setText(this.description);
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "Chance [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }

    
}
