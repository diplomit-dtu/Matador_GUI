package desktop_fields;

import java.awt.Color;
import javax.swing.JLabel;
import desktop_board.Center;
import desktop_codebehind.SwingComponentFactory;

public final class Chance extends Field {
    private static final int TITLEHEIGHT = 47;
    private static final int SUBTEXTHEIGHT = 14;
    private SwingComponentFactory factory = new SwingComponentFactory();
    
    public Chance(){
        this("<b><font size=\"7\">?", Field.SUBTEXT, Field.DESCRIPTION, new Color(204, 204, 204), Field.FG_COLOR);
    }
    public Chance(String title, String subText, String description, Color bgColor, Color fgColor) {
        super(bgColor, fgColor, title, subText, description);
        this.titleLabel = makeRoadNameLabel();
        this.subTextLabel = makeBottomLabel();
        this.layered.add(this.titleLabel, this.factory.createGridBagConstraints(0, 0));
        this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 1));
    }
    private JLabel makeRoadNameLabel() {
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
    public void displayOnCenter() {
        super.displayOnCenter();
        Center.label[1].setIcon(this.factory.createIcon("/desktop_resources/pics/Prøv lykken small.png"));
        Center.label[2].setText("__________________________");
        Center.label[3].setText(this.description);
        super.displayCarOnCenter();
    }
}
