package desktop_codebehind;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 * Used to create basic swing elements
 * @author Ronnie
 */
public class SwingComponentFactory {
	
	/**
	 * Creates a Dimension object (used to set size on swing components)
	 * @param width - in px
	 * @param height - in px
	 * @return a Dimension object
	 */
	private Dimension createDimension(int width, int height) {
		return new Dimension(width, height);
	}
	/**
	 * Creats a GridBagConstraints object (used to set the relative position of a swing component on
	 * its parent component. (Don't fortget to set GridBagLayout on parent component.
	 * @param x - in px - direction: left to right
	 * @param y - in px - direction: top to bottom
	 * @param width - in columns
	 * @param height - in rows
	 * @return a new GridBagConstraints object
	 */
	public GridBagConstraints createGridBagConstraints(int x, int y, int width, int height) {
		GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = width;
		gridBagConstraints.gridheight = height;
		return gridBagConstraints;
	}
	/**
	 * Overloaded method Calls createGridBagConstraints(x, y, 1, 1)
	 * @param x The X-coordinate
	 * @param y The Y-coordinate
	 * @return a new GridBagConstraints object
	 */
	public GridBagConstraints createGridBagConstraints(int x, int y) {
		return createGridBagConstraints(x, y, 1, 1);
	}
	/**
	 * Creates an icon using a picture file
	 * @param path - absolute path - start with / to set path relative to project
	 * @return an ImageIcon
	 */
	public ImageIcon createIcon(String path) {
		return new ImageIcon(getClass().getResource(path));
	}
	/**
	 * Sets the size of a component. Short for calling setMaximumSize, setMinimimSize and
	 * setPreferredSize all by creating single Dimension object using createDimension
	 * @param component - any swing component
	 * @param width - in px
	 * @param height - in px
	 * @return the given component, now with a new size
	 */
	public Component setSize(Component component, int width, int height) {
		component.setMaximumSize(createDimension(width, height));
		component.setMinimumSize(createDimension(width, height));
		component.setPreferredSize(createDimension(width, height));
		return component;
	}
	/**
	 * Creates an image from a source
	 * @param src The source
	 * @return A new image object
	 */
	public BufferedImage createImage(String src) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource(src));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return image;
	}
	
	public Border createDashedBorder(int thickness, int stroke, Color color1, Color color2) {
		int w = stroke * 2;
		int h = stroke * 2;
		BufferedImage i = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				if(x < stroke && y < stroke) {
					i.setRGB(x, y, color1.getRGB());
				} else if(x >= stroke && y < stroke) {
					i.setRGB(x, y, color2.getRGB());
				} else if(x < stroke && y >= stroke) {
					i.setRGB(x, y, color2.getRGB());
				} else if(x >= stroke && y >= stroke) {
					i.setRGB(x, y, color1.getRGB());
				}
			}
		}
		return BorderFactory.createMatteBorder(thickness, thickness, thickness, thickness,
			new ImageIcon(i));
	}
}