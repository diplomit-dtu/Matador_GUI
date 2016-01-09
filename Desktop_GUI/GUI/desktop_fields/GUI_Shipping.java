package desktop_fields;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import desktop_codebehind.GUI_Center;
import desktop_codebehind.SwingComponentFactory;

public final class GUI_Shipping extends GUI_Ownable {
	private static final int TOPHEIGHT = 31;
	private static final int TITLEHEIGHT = 16;
	private static final int SUBTEXTHEIGHT = 14;
	private JLabel topLabel;
	private ImageIcon icon;
	private SwingComponentFactory factory = new SwingComponentFactory();
	private static int picCounter = 0;
	
	public GUI_Shipping(){
	    this(PICTURE, TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);
	}
	public GUI_Shipping(String picture, String title, String subText, String description, String rent, Color bgColor, Color fgColor) {
		super(bgColor, fgColor, title, subText, description, rent);
		
		if("default".equalsIgnoreCase(picture)) {
			int p = (picCounter++ % 4) + 1;
			this.icon = this.factory.createIcon("/desktop_resources/pics/Ferry" + p + ".jpg");
		} else {
			try {
				this.icon = new ImageIcon(picture);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Bad Resource: " + picture);
			}
		}
		
		this.topLabel = makeTopLabel();
		super.titleLabel = makeRoadNameLabel(this.title);
		super.subTextLabel = makeBottomLabel(this.subText);
		this.layered.add(this.topLabel, this.factory.createGridBagConstraints(0, 0));
		this.layered.add(this.titleLabel, this.factory.createGridBagConstraints(0, 1));
		this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 2));
	}
	private JLabel makeTopLabel() {
		JLabel l = makeLabel(TOPHEIGHT);
		l.setIcon(this.icon);
		return l;
	}
	private JLabel makeRoadNameLabel(String titleShipping) {
		JLabel roadnameLabel = makeLabel(TITLEHEIGHT);
		roadnameLabel.setText(titleShipping);
		return roadnameLabel;
	}
	private JLabel makeBottomLabel(String subTextShipping) {
		JLabel bottomLabel = makeLabel(SUBTEXTHEIGHT);
		bottomLabel.setText(subTextShipping);
		return bottomLabel;
	}
	@Override
	protected void displayOnCenter() {
		super.displayOnCenter();
		GUI_Center.label[1].setIcon(this.icon);
		GUI_Center.label[2].setText("__________________________");
		GUI_Center.label[3].setText(this.description);
		GUI_Center.label[4].setText(this.subText);
		if(this.owner != null) {
			GUI_Center.label[5].setText(OWNABLELABEL + this.owner.getName());
			GUI_Center.label[6].setText(getRent());
		}
		super.displayCarOnCenter();
	}
}
