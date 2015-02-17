package desktop_fields;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import desktop_board.Center;
import desktop_codebehind.SwingComponentFactory;

public final class Start extends Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public static class Builder extends Field.Builder<Start.Builder> implements
        iBuilder {
        public Builder() {
            this.title = "Start";
            this.bgColor = Color.RED;
        }
        
        @Override
        @SuppressWarnings("synthetic-access")
        public Start build() {
            return new Start(this.bgColor, this.fgColor, this.title,
                this.subText, this.description);
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
    
    private Start(Color bgColor, Color fgColor, String title,
        String subText, String description) {
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeTitleLabel(this.title);
        this.subTextLabel = makeSubTextLabel(this.subText);
        this.layered.add(this.titleLabel,
            this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel,
            this.factory.createGridBagConstraints(0, 1));
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
    public void displayOnCenter() {
        super.displayOnCenter();
        Center.label[1].setText(this.title.replace("<html><center>", ""));
        Center.label[2].setText("__________________________");
        Center.label[3].setText(this.description);
        super.displayCarOnCenter();
    }
}
