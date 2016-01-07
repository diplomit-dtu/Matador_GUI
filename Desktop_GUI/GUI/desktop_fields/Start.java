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
    
    public Start(){
        this(Field.TITLE, Field.SUBTEXT, Field.DESCRIPTION, Field.BG_COLOR, Field.FG_COLOR);
    }
    public Start(String title, String subText, String description, Color bgColor, Color fgColor){
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
