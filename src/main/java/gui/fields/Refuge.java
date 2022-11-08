package gui.fields;

import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import gui.codebehind.Center;
import gui.codebehind.SwingComponentFactory;
import gui.resources.Attrs;

public final class Refuge extends Field {
    private static final int TOPHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private ImageIcon icon;

    public Refuge(){
        this(PICTURE, TITLE, SUBTEXT, DESCRIPTION, BG_COLOR, FG_COLOR);
    }
    public Refuge(String picture, String title, String subText, String description, Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description);

        SwingComponentFactory factory = new SwingComponentFactory();
        if ("default".equalsIgnoreCase(picture)) {
            URL path = Attrs.getImagePath("Field.Image.Cones");
            this.icon = factory.createIcon(path);
        } else {
            try {
                this.icon = new ImageIcon(picture);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Attrs.getString("Error.BadArgument.ImagePath", picture));
            }
        }

        JLabel topLabel = makeTopLabel();
        this.subTextLabel = makeBottomLabel(this.subText);
        this.layered.add(topLabel, factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, factory.createGridBagConstraints(0, 1));
    }
    private JLabel makeTopLabel() {
        JLabel l = makeLabel(TOPHEIGHT);
        l.setIcon(this.icon);
        return l;
    }
    private JLabel makeBottomLabel(String subTextRefuge) {
        JLabel bottomLabel = makeLabel(SUBTEXTHEIGHT);
        bottomLabel.setText(subTextRefuge);
        return bottomLabel;
    }
    @Override
    protected void displayOnCenter(Player[] playerList) {
        super.displayOnCenter(playerList);
        Center.label[1].setText(this.title.replace("<html><center>", ""));
        Center.label[2].setIcon(this.icon);
        Center.label[3].setText("__________________________");
        Center.label[4].setText(this.description);
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "Refuge [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }
    
    
}
