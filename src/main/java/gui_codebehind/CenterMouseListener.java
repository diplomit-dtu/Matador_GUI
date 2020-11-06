package gui_codebehind;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listens to mouse action on centerField
 * @author Ronnie
 */
public class CenterMouseListener implements MouseListener{
	private GUI_Center center;
	
	public CenterMouseListener(GUI_Center center){
		this.center = center;
	}
	/**
     * Called when the mouse is clicked on the centerField
     */
	@Override
	public void mouseClicked(MouseEvent e){
		GUI_Center.getInstance().displayChanceCard();
	}
	/**
     * Called when the mouse is no longer over the centerField
     */
	@Override
	public void mouseExited(MouseEvent e){
		this.center.displayDefault();
	}
	@Override
	public void mousePressed(MouseEvent e){
		// Do nothing
	}
	@Override
	public void mouseReleased(MouseEvent e){
		// Do nothing
	}
	@Override
	public void mouseEntered(MouseEvent e){
		// Never invoked
	}
}
