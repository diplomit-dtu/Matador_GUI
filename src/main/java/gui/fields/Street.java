package gui.fields;

import java.awt.Color;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.core.Center;
import gui.core.Ownable;
import gui.util.SwingComponentFactory;
import gui.core.Player;
import gui.util.Attrs;

public final class Street extends Ownable {
    private static final int TITLEHEIGHT = 24;
    private static final int SUBTEXTHEIGHT = 10;
    private final JLabel houseLabel;
    private final SwingComponentFactory factory = new SwingComponentFactory();
    
    public Street(){
        this(TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);
    }
    public Street(String title, String subText, String description, String rent, Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description, rent);
        this.title = title.replace("\n", "<BR>");
        
        super.subTextLabel = makeSubTextLabel();
        this.houseLabel = makeHouseLabel();
        
        this.titleLabel = makeLabel(24);
        this.titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        this.titleLabel.setText(this.title);
        
        super.layered.add(this.titleLabel, this.factory.createGridBagConstraints(0, 0));
        super.layered.add(this.houseLabel, this.factory.createGridBagConstraints(0, 1));
        super.layered.add(super.subTextLabel, this.factory.createGridBagConstraints(0, 2));
        super.layered.setLayer(this.titleLabel, 1);
        super.layered.setLayer(super.subTextLabel, 1);
        super.layered.setLayer(this.houseLabel, 0);
    }
    public Street setTextColor(Color textColor) {
        this.titleLabel.setForeground(textColor);
        this.subTextLabel.setForeground(textColor);
        return this;
    }
    private JLabel makeSubTextLabel() {
        JLabel l = makeLabel(SUBTEXTHEIGHT);
        l.setHorizontalTextPosition(SwingConstants.CENTER);
        l.setText(super.subText);
        return l;
    }
    private JLabel makeHouseLabel() {
        JLabel l = makeLabel(TITLEHEIGHT);
        l.setOpaque(false);
        return l;
    }
    public void setHouses(int houseCount) {
        if(houseCount < 0 || houseCount > 4)
            throw new IllegalArgumentException(Attrs.getString("Error.BadArgument.houseCount", houseCount));
        Icon icon;
        if(houseCount == 0){
            icon = null;
        } else {
            URL path = Attrs.getImagePath("Field.Image.House", houseCount);
            icon = this.factory.createIcon(path);
        }
        this.houseLabel.setIcon(icon);
    }
    public void setHotel(boolean hasHotel) {
        Icon icon;
        if(hasHotel){
            URL path = Attrs.getImagePath("Field.Image.Hotel");
            icon = this.factory.createIcon(path);
        } else {
            icon = null;
        }
        this.houseLabel.setIcon(icon);
    }
    @Override
    public void displayOnCenter(Player[] playerList) {
        super.displayOnCenter(playerList);
        Center.label[1].setText("__________________________");
        Center.label[2].setText(this.description);
        Center.label[3].setText(this.subText);
        
        if (this.ownerName != null) {
            Center.label[4].setText(getOwnableLabel() + getOwnerName());
            Center.label[5].setText(getRentLabel() + getRent());
        }
        Center.label[6].setIcon(this.houseLabel.getIcon());
        
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "Street [ownerName=" + ownerName + ", bgColor=" + bgColor + ", fgColor=" + fgColor + ", title="
            + title + ", subText=" + subText + ", description=" + description
            + "]";
    }

    
    
    
    
}
