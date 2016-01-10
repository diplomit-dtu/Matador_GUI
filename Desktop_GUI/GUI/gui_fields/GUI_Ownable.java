package gui_fields;

import java.awt.Color;
import javax.swing.border.Border;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;
import gui_resources.Attrs;

public abstract class GUI_Ownable extends GUI_Field{
	public static String OWNABLELABEL;
	private Color color1, color2;
	protected String ownerName;
	private String rent;
	
	public GUI_Ownable(Color bgColor, Color fgColor, String title, String subText, String description, String leje){
		super(bgColor, fgColor, title, subText, description);
		this.rent = leje;
		OWNABLELABEL = Attrs.getString("GUI_Field.Label.owns");
	}
	
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
	protected void displayOnCenter(GUI_Player[] playerList){
		super.displayOnCenter(playerList);
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