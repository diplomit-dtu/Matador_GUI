package gui_fields;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;
import gui_resources.Attrs;

public final class GUI_Street extends GUI_Ownable {
    private static final int TITLEHEIGHT = 24;
    private static final int SUBTEXTHEIGHT = 10;
    private JLabel houseLabel;
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public GUI_Street(){
        this(TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);
    }
    public GUI_Street(String title, String subText, String description, String rent, Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description, rent);
        title = title.replace("\n", "<BR>");
        
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
    public GUI_Street setTextColor(Color textColor) {
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
            String path = Attrs.getImagePath(String.format("GUI_Field.Image.House%d", houseCount));
            icon = this.factory.createIcon(path);
        }
        this.houseLabel.setIcon(icon);
    }
    public void setHotel(boolean hasHotel) {
        Icon icon;
        if(hasHotel){
            String path = Attrs.getImagePath("GUI_Field.Image.Hotel");
            icon = this.factory.createIcon(path);
        } else {
            icon = null;
        }
        this.houseLabel.setIcon(icon);
    }
    @Override
    protected void displayOnCenter(GUI_Player[] playerList) {
        super.displayOnCenter(playerList);
        GUI_Center.label[1].setText("__________________________");
        GUI_Center.label[2].setText(this.description);
        GUI_Center.label[3].setText(this.subText);
        
        if (this.ownerName != null) {
            GUI_Center.label[4].setText(getOwnableLabel() + getOwnerName());
            GUI_Center.label[5].setText(getRentLabel() + getRent());
        }
        GUI_Center.label[6].setIcon(this.houseLabel.getIcon());
        
        super.displayCarOnCenter(playerList);
    }
    @Override
    public String toString() {
        return "GUI_Street [ownerName=" + ownerName + ", bgColor=" + bgColor + ", fgColor=" + fgColor + ", title="
            + title + ", subText=" + subText + ", description=" + description
            + "]";
    }

    
    
    
    
}
