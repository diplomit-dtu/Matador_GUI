package desktop_codebehind;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import desktop_board.Center;
import desktop_fields.Field;

/**
 * Listens to mouse action on fields
 * @author Ronnie
 */
public class FieldMouseListener implements MouseListener{
	private Field field;
    
    public FieldMouseListener(Field field){
        this.field = field;
    }
    /**
     * Called when the mouse is over a field
     */
    @Override
    public void mouseEntered(@SuppressWarnings("unused") MouseEvent e) {
    	this.field.displayOnCenter();
    }
    /**
     * Called when the mouse is no longer over a field
     */
    @Override
    public void mouseExited(@SuppressWarnings("unused") MouseEvent e) {
    	Center.getInstance().displayDefault();
    }
    @Override
    public void mouseClicked(@SuppressWarnings("unused") MouseEvent e) {
        //Do nothing
    }
    @Override
    public void mousePressed(@SuppressWarnings("unused") MouseEvent e) {
       //Do nothing
    }
    @Override
    public void mouseReleased(@SuppressWarnings("unused") MouseEvent e) {
       //Do nothing
    }
}