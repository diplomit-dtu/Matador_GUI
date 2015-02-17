package desktop_fields;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import desktop_board.Center;
import desktop_codebehind.SwingComponentFactory;

public final class Jail extends Field{
	private static final int TOPHEIGHT = 47;
	private static final int SUBTEXTHEIGHT = 14;
	private ImageIcon icon;
	private JLabel topLabel;
	private SwingComponentFactory factory = new SwingComponentFactory();
	private static int picCounter = 0;
	
	public static class Builder extends Field.Builder<Jail.Builder> implements iBuilder{
        public Builder() {
            this.bgColor = new Color(125, 125, 125);
            this.description = "You are jailed";
        }
        
        @Override
        @SuppressWarnings("synthetic-access")
        public Jail build() {
            return new Jail(this.picture, this.title,
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

	private Jail(String picture, String title, String subText, String description, Color bgColor, Color fgColor){
		super(bgColor, fgColor, title, subText, description);

		if("default".equalsIgnoreCase(picture)){
			int p = (picCounter++ % 2);
			String name = p>0 ? "GoToJail.jpg" : "Jail.jpg";
			this.icon = this.factory.createIcon("/desktop_resources/pics/"+name);
		}else{
			try{
			this.icon = new ImageIcon(picture);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Bad Resource: "+picture);
			}
		}
		
		this.topLabel = makeTopLabel();
		this.subTextLabel = makeBottomLabel(this.subText);
		this.layered.add(this.topLabel, this.factory.createGridBagConstraints(0, 0));
		this.layered.add(this.subTextLabel, this.factory.createGridBagConstraints(0, 1));
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
	public void displayOnCenter(){
		super.displayOnCenter();
		Center.label[1].setIcon(this.icon);
		Center.label[2].setText("__________________________");
		Center.label[3].setText(this.description);
		super.displayCarOnCenter();
	}
}