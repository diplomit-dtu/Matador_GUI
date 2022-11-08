package gui.fields;

import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import gui.codebehind.Center;
import gui.codebehind.SwingComponentFactory;
import gui.resources.Attrs;

public final class Jail extends Field {
	private static final int TOPHEIGHT = 47;
	private static final int SUBTEXTHEIGHT = 14;
	private ImageIcon icon;
	private static int picCounter = 0;

	public Jail(){
	    this(PICTURE, TITLE, SUBTEXT, DESCRIPTION, new Color(125, 125, 125), Color.BLACK);
	}
	public Jail(String picture, String title, String subText, String description, Color bgColor, Color fgColor){
		super(bgColor, fgColor, title, subText, description);

		SwingComponentFactory factory = new SwingComponentFactory();
		if("default".equalsIgnoreCase(picture)){
			int p = (picCounter++ % 2);
			URL path1 = Attrs.getImagePath("Field.Image.GoToJail");
			URL path2 = Attrs.getImagePath("Field.Image.Jail");
            this.icon = factory.createIcon(p>0 ? path1 : path2);
		}else{
			try{
			this.icon = new ImageIcon(picture);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Bad Resource: "+picture);
			}
		}

		JLabel topLabel = makeTopLabel();
		this.subTextLabel = makeBottomLabel(this.subText);
		this.layered.add(topLabel, factory.createGridBagConstraints(0, 0));
		this.layered.add(this.subTextLabel, factory.createGridBagConstraints(0, 1));
	}
	private JLabel makeTopLabel(){
		JLabel l = makeLabel(TOPHEIGHT);
		l.setIcon(this.icon);
		return l;
	}
	private JLabel makeBottomLabel(String subTextJail){
		JLabel bottomLabel = makeLabel(SUBTEXTHEIGHT);
		bottomLabel.setText(subTextJail);
		return bottomLabel;
	}
	public String getBottomText(){
		return this.subText;
	}
	@Override
	protected void displayOnCenter(Player[] playerList){
		super.displayOnCenter(playerList);
		Center.label[1].setIcon(this.icon);
		Center.label[2].setText("__________________________");
		Center.label[3].setText(this.description);
		super.displayCarOnCenter(playerList);
	}
    @Override
    public String toString() {
        return "Jail [bgColor=" + bgColor
            + ", fgColor=" + fgColor + ", title=" + title + ", subText="
            + subText + ", description=" + description + "]";
    }
 
	
	
}