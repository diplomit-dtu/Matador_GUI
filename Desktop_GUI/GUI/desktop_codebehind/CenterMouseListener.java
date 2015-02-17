package desktop_codebehind;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import desktop_board.Center;

/**
 * Listens to mouse action on centerField
 * @author Ronnie
 */
public class CenterMouseListener implements MouseListener{
	private Center center;
	
	public CenterMouseListener(Center center){
		this.center = center;
	}
	/**
     * Called when the mouse is clicked on the centerField
     */
	@Override
	public void mouseClicked(@SuppressWarnings("unused") MouseEvent e){
		Center.getInstance().displayChanceCard();
	}
	/**
     * Called when the mouse is no longer over the centerField
     */
	@Override
	public void mouseExited(@SuppressWarnings("unused") MouseEvent e){
		this.center.displayDefault();
	}
	@Override
	public void mousePressed(@SuppressWarnings("unused") MouseEvent e){
		// Do nothing
	}
	@Override
	public void mouseReleased(@SuppressWarnings("unused") MouseEvent e){
		// Do nothing
	}
	@Override
	public void mouseEntered(@SuppressWarnings("unused") MouseEvent e){
		// Never invoked
	}
}
