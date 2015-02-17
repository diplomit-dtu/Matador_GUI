package desktop_codebehind;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

public class JLabelRotatable extends JLabel {
	private static final long serialVersionUID = 6814260134323423412L;
	private int rotation = 0;
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if(this.getIcon() != null) {
			g2.rotate(Math.toRadians(this.getRotation()),
				this.getIcon().getIconWidth()/2 + 5,
				this.getIcon().getIconHeight()/2 + 5);
		}
		super.paintComponent(g);
	}

	public int getRotation() {
		return this.rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
}
