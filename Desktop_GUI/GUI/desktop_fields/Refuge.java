package desktop_fields;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import desktop_board.Center;
import desktop_codebehind.SwingComponentFactory;

public final class Refuge extends Field {
    private static final int TOPHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private ImageIcon icon;
    private JLabel topLabel;
    
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public static class Builder extends Field.Builder<Refuge.Builder> implements
        iBuilder {
        public Builder() {
            this.title = "Refuge";
            this.bgColor = Color.WHITE;
        }
        
        @Override
        @SuppressWarnings("synthetic-access")
        public Refuge build() {
            return new Refuge(this.picture, this.title,
                this.subText, this.description, this.bgColor, this.fgColor);
        }
        
        public Builder setPicture(String picture) {
            this.picture = picture;
            return this;
        }
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setSubText(String subText) {
            this.subText = subText;
            return this;
        }
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }
    }
    
    private Refuge(String picture, String title, String subText,
        String description, Color bgColor, Color fgColor) {
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
        this.layered.add(this.topLabel,
            this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel,
            this.factory.createGridBagConstraints(0, 1));
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
    public void displayOnCenter() {
        super.displayOnCenter();
        Center.label[1].setText(this.title.replace("<html><center>", ""));
        Center.label[2].setIcon(this.icon);
        Center.label[3].setText("__________________________");
        Center.label[4].setText(this.description);
        super.displayCarOnCenter();
    }
}
