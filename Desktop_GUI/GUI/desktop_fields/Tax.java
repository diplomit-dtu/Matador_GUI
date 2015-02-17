package desktop_fields;

import java.awt.Color;
import javax.swing.JLabel;
import desktop_board.Center;
import desktop_codebehind.SwingComponentFactory;

public final class Tax extends Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public static class Builder extends Field.Builder<Tax.Builder> implements
        iBuilder {
        public Builder() {
            this.title = "Tax";
            this.bgColor = new Color(153, 153, 153);
        }
        
        @Override
        @SuppressWarnings("synthetic-access")
        public Tax build() {
            return new Tax(this.description, this.title,
                this.subText, this.bgColor, this.fgColor);
        }
        public Builder setDescription(String description) {
            this.description = description;
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
    }
    
    private Tax(String title, String subText, String description,
        Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeTitleLabel(this.title);
        this.subTextLabel = makeSubTextLabel(this.subText);
        this.layered.add(this.titleLabel,
            this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel,
            this.factory.createGridBagConstraints(0, 1));
    }
    private JLabel makeTitleLabel(String titleTax) {
        JLabel l = makeLabel(TITLEHEIGHT);
        l.setText(titleTax);
        return l;
    }
    private JLabel makeSubTextLabel(String subTextTax) {
        JLabel l = makeLabel(SUBTEXTHEIGHT);
        l.setText(subTextTax);
        return l;
    }
    @Override
    public void displayOnCenter() {
        super.displayOnCenter();
        Center.label[1].setText("__________________________");
        Center.label[2].setText(this.description);
        super.displayCarOnCenter();
    }
}
