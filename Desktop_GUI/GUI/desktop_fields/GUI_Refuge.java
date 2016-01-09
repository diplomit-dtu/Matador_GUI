package desktop_fields;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import desktop_codebehind.GUI_Center;
import desktop_codebehind.SwingComponentFactory;

public final class GUI_Refuge extends GUI_Field {
    private static final int TOPHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private ImageIcon icon;
    private JLabel topLabel;
    
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public GUI_Refuge(){
        this(PICTURE, TITLE, SUBTEXT, DESCRIPTION, BG_COLOR, FG_COLOR);
    }
    public GUI_Refuge(String picture, String title, String subText, String description, Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description);
        
        if ("default".equalsIgnoreCase(picture)) {
            this.icon = this.factory.createIcon("/desktop_resources/pics/Cones.jpg");
        } else {
            try {
                this.icon = new ImageIcon(picture);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Bad Resource: " + picture);
            }
        }
        
        this.topLabel = makeTopLabel();
        this.subTextLabel = makeBottomLabel(this.subText);
        this.layered.add(this.topLabel, this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 1));
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
    protected void displayOnCenter() {
        super.displayOnCenter();
        GUI_Center.label[1].setText(this.title.replace("<html><center>", ""));
        GUI_Center.label[2].setIcon(this.icon);
        GUI_Center.label[3].setText("__________________________");
        GUI_Center.label[4].setText(this.description);
        super.displayCarOnCenter();
    }
}
