package desktop_fields;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import desktop_codebehind.GUI_Center;
import desktop_codebehind.GUI_Player;
import desktop_codebehind.SwingComponentFactory;

public abstract class GUI_Ownable extends GUI_Field{
	public static final String OWNABLELABEL = "Ejes af: ";
	protected GUI_Player owner;
	private String leje;
	
	public GUI_Ownable(Color bgColor, Color fgColor, String title, String subText, String description, String leje){
		super(bgColor, fgColor, title, subText, description);
		this.leje = leje;
	}
	
	public GUI_Player getOwner(){return this.owner; }
	public void setOwner(GUI_Player owner){
		this.owner = owner;
		Border border;
		if(owner == null){
			border = BorderFactory.createLineBorder(Color.BLACK, 1);
			this.subTextLabel.setText(this.subText);
		}else{
			SwingComponentFactory factory = new SwingComponentFactory();
			Color c1 = this.owner.getPrimaryColor();
			Color c2 = this.owner.getSecondaryColor();
			border = factory.createDashedBorder(2, 5, c1, c2);
			String nameToUse = owner.getName();
			nameToUse = (nameToUse.length() > 11) ? nameToUse.substring(0, 8)+"..." : nameToUse;
			this.subTextLabel.setText(nameToUse);
		}
		this.layered.setBorder(border);
		
	}
	public String getLeje(){
		return this.leje;
	}
	
	@Override
	public void displayOnCenter(){
		super.displayOnCenter();
		Border border;
		if(this.owner != null){
			SwingComponentFactory factory = new SwingComponentFactory();
			Color c1 = this.owner.getPrimaryColor();
			Color c2 = this.owner.getSecondaryColor();
			border = factory.createDashedBorder(3, 10, c1, c2);
			GUI_Center.getInstance().getCenterPanel().setBorder(border);
		}else{
			border = javax.swing.BorderFactory.createLineBorder(Color.BLACK, 3);
		}
		GUI_Center.getInstance().getCenterPanel().setBorder(border);
	}
}