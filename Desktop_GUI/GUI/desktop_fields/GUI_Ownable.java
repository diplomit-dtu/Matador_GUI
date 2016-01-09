package desktop_fields;

import java.awt.Color;
import javax.swing.border.Border;
import desktop_codebehind.GUI_Center;
import desktop_codebehind.SwingComponentFactory;

public abstract class GUI_Ownable extends GUI_Field{
	public static final String OWNABLELABEL = "Ejes af: ";
//	protected GUI_Player owner;
	private Color color1, color2;
	protected String ownerName;
	private String rent;
	
	public GUI_Ownable(Color bgColor, Color fgColor, String title, String subText, String description, String leje){
		super(bgColor, fgColor, title, subText, description);
		this.rent = leje;
	}
	
//	public GUI_Player getOwner(){return this.owner; }
//	public void setOwner(GUI_Player owner){
//		this.owner = owner;
//		Border border;
//		if(owner == null){
//			border = BorderFactory.createLineBorder(Color.BLACK, 1);
//			this.subTextLabel.setText(this.subText);
//		}else{
//			SwingComponentFactory factory = new SwingComponentFactory();
//			Color c1 = this.owner.getPrimaryColor();
//			Color c2 = this.owner.getSecondaryColor();
//			border = factory.createDashedBorder(2, 5, c1, c2);
//			String nameToUse = owner.getName();
//			nameToUse = (nameToUse.length() > 11) ? nameToUse.substring(0, 8)+"..." : nameToUse;
//			this.subTextLabel.setText(nameToUse);
//		}
//		this.layered.setBorder(border);
//		
//	}
	public void setBorder(Color color){
	    setBorder(color, color);
	}
	public void setBorder(Color color1, Color color2){
        if(color1 == null) color1 = Color.BLACK;
	    if(color2 == null) color2 = new Color(color1.getRed(), color1.getGreen(), color1.getBlue());
	    this.color1 = color1;
	    this.color2 = color2;
	    SwingComponentFactory factory = new SwingComponentFactory();
	    Border border = factory.createDashedBorder(2, 5, color1, color2);
	    this.layered.setBorder(border);
	    
	}
	public String getOwnerName(){ return this.ownerName; }
	/**
	 * For display on center field
	 * @param ownerName Mind the length
	 */
	public void setOwnerName(String ownerName){ this.ownerName = ownerName; }
	public String getRent(){ return this.rent; }
	public void setRent(String rent){ this.rent = rent; }
	
	@Override
	protected void displayOnCenter(){
		super.displayOnCenter();
		Border border;
		if(this.ownerName != null){
			SwingComponentFactory factory = new SwingComponentFactory();
			border = factory.createDashedBorder(3, 10, color1, color2);
			GUI_Center.getInstance().getCenterPanel().setBorder(border);
		}else{
			border = javax.swing.BorderFactory.createLineBorder(Color.BLACK, 3);
		}
		GUI_Center.getInstance().getCenterPanel().setBorder(border);
	}
}