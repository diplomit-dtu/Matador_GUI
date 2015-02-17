package desktop_fields;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import desktop_board.Center;
import desktop_codebehind.SwingComponentFactory;

public final class Brewery extends Ownable {
    private static final int TOPHEIGHT = 31;
    private static final int TITLEHEIGHT = 16;
    private static final int SUBTEXTHEIGHT = 14;
    private JLabel topLabel;
    private ImageIcon icon;
    private SwingComponentFactory factory = new SwingComponentFactory();
    private static int picCounter = 0;
    
    public static class Builder extends Field.Builder<Brewery.Builder>
        implements iBuilder {
        public Builder() {
            this.bgColor = Color.BLACK;
            this.fgColor = Color.WHITE;
        }
        
        @Override
        @SuppressWarnings("synthetic-access")
        public Brewery build() {
            return new Brewery(this.picture, this.title,
                this.subText, this.description, this.rent, this.bgColor, this.fgColor);
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
        public Builder setRent(String rent) {
            this.rent = rent;
            return this;
        }
    }
    
    private Brewery(String picture, String title, String subText,
        String description, String rent, Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description, rent);
        
        if ("default".equalsIgnoreCase(picture)) {
            int p = (picCounter++ % 2) + 1;
            this.icon = this.factory.createIcon("/desktop_resources/pics/Brewery" + p + ".jpg");
        } else {
            try {
                this.icon = new ImageIcon(picture);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Bad Resource: " + picture);
            }
        }
        
        this.topLabel = makeTopLabel();
        this.titleLabel = makeRoadNameLabel(this.title);
        this.subTextLabel = makeBottomLabel(this.subText);
        this.layered.add(this.topLabel,
            this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.titleLabel,
            this.factory.createGridBagConstraints(0, 1));
        this.layered.add(this.subTextLabel,
            this.factory.createGridBagConstraints(0, 2));
    }
    
    private JLabel makeTopLabel() {
        JLabel l = makeLabel(TOPHEIGHT);
        l.setIcon(this.icon);
        return l;
    }
    private JLabel makeRoadNameLabel(String roadname) {
        JLabel roadnameLabel = makeLabel(TITLEHEIGHT);
        roadnameLabel.setText(roadname);
        return roadnameLabel;
    }
    private JLabel makeBottomLabel(String bottomText) {
        JLabel bottomLabel = makeLabel(SUBTEXTHEIGHT);
        bottomLabel.setText(bottomText);
        return bottomLabel;
    }
    @Override
    public void displayOnCenter() {
        super.displayOnCenter();
        Center.label[1].setIcon(this.icon);
        Center.label[3].setText("__________________________");
        Center.label[3].setText(this.description);
        Center.label[4].setText(this.subText);
        if (this.owner != null) {
            Center.label[5].setText(OWNABLELABEL + this.owner.getName());
            Center.label[6].setText(getLeje());
        }
        super.displayCarOnCenter();
    }
}
