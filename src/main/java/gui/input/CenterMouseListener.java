package gui.input;

import gui.core.Center;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listens to mouse action on centerField
 * @author Ronnie
 */
public class CenterMouseListener implements MouseListener{
	private final Center center;
	
	public CenterMouseListener(Center center){
		this.center = center;
	}
	/**
     * Called when the mouse is clicked on the centerField
     */
	@Override
	public void mouseClicked(MouseEvent e){
		Center.getInstance().displayChanceCard();
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
