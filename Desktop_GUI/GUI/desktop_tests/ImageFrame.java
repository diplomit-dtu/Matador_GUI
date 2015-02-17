package desktop_tests;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageFrame {
	private String title = "Title";
	private int x = 0, y = 0;
	private int delay = 0;
	private boolean showSaveButton = true;
	private BufferedImage image;
	private JPanel imagePanel;
	
	public ImageFrame(BufferedImage image) {
		this.image = image;
	}

	public void display() {
		if (this.image == null) {
			this.image = createEmptyImage();
		}
		final JFrame frame = new JFrame();
		frame.setBounds(this.x, this.y, 0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(this.title);
		frame.setResizable(false);
		
		JPanel base = new JPanel();
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
		frame.add(base);
		this.imagePanel = new JPanel();
		this.imagePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JLabel imageLabel = new JLabel(new ImageIcon(this.image));
		imageLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.imagePanel.add(imageLabel);
		base.add(this.imagePanel);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JLabel dim = new JLabel(this.image.getWidth()+" x "+this.image.getHeight());
		dim.setAlignmentX(Component.RIGHT_ALIGNMENT);
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(dim);
		bottomPanel.add(Box.createGlue());
		
		if(this.showSaveButton){
			JButton saveButton = new JButton("Save");
			saveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(
						@SuppressWarnings("unused") ActionEvent evt) {
					savePic();
				}
			});
			saveButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
			bottomPanel.add(saveButton);
		}
		
		base.add(bottomPanel);
		frame.setVisible(true);
		frame.repaint();
		frame.validate();
		frame.pack();

		if (this.delay > 0) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					frame.setVisible(false);
				}
			}, this.delay * 1000);
			return;
		}
	}
	void setImage(BufferedImage image){
		this.image = image;
		this.imagePanel.removeAll();
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		this.imagePanel.add(imageLabel, 0);
		this.imagePanel.validate();
		this.imagePanel.repaint();
	}
	void savePic() {
		Calendar instance = Calendar.getInstance();
		int sec = instance.get(Calendar.SECOND);
		int min = instance.get(Calendar.MINUTE);
		int hour = instance.get(Calendar.HOUR_OF_DAY);
		int day = instance.get(Calendar.DAY_OF_MONTH);
		int month = instance.get(Calendar.MONTH) + 1;
		int year = instance.get(Calendar.YEAR);
		String timestamp = sec + "." + min + "." + hour + " " + day + "."
				+ month + "." + year;

		String path = System.getProperty("user.home") + "/Desktop";
		File file = new File(path + "/image " + timestamp + ".jpg");
		try {
			ImageIO.write(this.image, "jpg", new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			System.err.println("Bad file! [ImageFrame.savePic(...)]");
		} catch (IOException e) {
			System.err.println("IO error! [ImageFrame.savePic(...)]");
		}
	}

	public ImageFrame setTitle(String title) {
		this.title = title;
		return this;
	}
	public ImageFrame setTopLeftCorner(Point p) {
		this.x = p.x;
		this.y = p.y;
		return this;
	}
	public ImageFrame setX(int x) {
		this.x = x;
		return this;
	}
	public ImageFrame setY(int y) {
		this.y = y;
		return this;
	}
	public ImageFrame setDelay(int delay) {
		this.delay = delay;
		return this;
	}
	public ImageFrame showSaveButton(boolean visible){
		this.showSaveButton = visible;
		return this;
	}
	private BufferedImage createEmptyImage(){
		BufferedImage img = new BufferedImage(400, 170, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		Font font = new Font("Verdana", Font.BOLD, 48);
		g.setFont(font);
		g.drawString("NO IMAGE", 60, 100);
		return img;
	}
}