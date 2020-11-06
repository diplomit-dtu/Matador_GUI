package gui_codebehind;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_resources.Attrs;

public final class GUI_Center{
	private static GUI_Center center;
	private static JPanel centerPanel;
	public static JLabel[] label = new JLabel[7];
	public static JLabel[] cars = new JLabel[GUI_Board.MAX_PLAYER_COUNT];
	public static String chanceCardText = "Prøv Lykken";
	private JLayeredPane playersPane = new JLayeredPane();
	
	private SwingComponentFactory factory = new SwingComponentFactory();

	private GUI_Center(){
		centerPanel = new javax.swing.JPanel();
		this.factory.setSize(centerPanel, 3*GUI_Field.FIELDWIDTH, 3*GUI_Field.FIELDHEIGHT);
		centerPanel.addMouseListener(new CenterMouseListener(this));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		 
		for(int i=0; i<label.length; i++){
			label[i] = new javax.swing.JLabel();
			label[i].setOpaque(false);
			label[i].setVerticalAlignment(javax.swing.SwingConstants.CENTER);
			label[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			centerPanel.add(label[i]);
		}
		for(int i = 0; i < cars.length; i++){
			JLabel l = new JLabel();
			cars[i] = l;
			l.setOpaque(false);
			l.setBounds(25*i+3, 0, GUI_Player.ICON_WIDTH, GUI_Player.ICON_HEIGHT);
			this.playersPane.setLayer(l, i+6);
			l.setVisible(false);
			this.playersPane.add(l);
		}
		centerPanel.add(this.playersPane);
		displayDefault();
	}
	/**
	 * Retrieves the singleton instance
	 * @return the Center instance
	 */
	public static GUI_Center getInstance(){
		if(center == null){
			center = new GUI_Center();
		}
		return center;
	}
	/**
	 * Clears all center labels;
	 * (icon label is handled seperatly)
	 */
	public void clearLabels(){
		for(JLabel l : label){
			l.setText("");
			l.setIcon(null);
		}
	}
	/**
	 * Sets the backgroundcolor of the center
	 * @param bgColor The background color
	 */
	public void setBGColor(Color bgColor){
		centerPanel.setBackground(bgColor);
	}
	/**
	 * Sets the foregroundcolor of the center
	 * @param fgColor The foreground color
	 */
	public void setFGColor(Color fgColor){
		centerPanel.setForeground(fgColor);
	}
	/**
	 * Display standard version of the center
	 */
	public void displayDefault(){
		centerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 3));
		clearLabels();
		String path = Attrs.getImagePath("GUI_Field.Image.Luck");
		label[0].setIcon(new ImageIcon(getClass().getResource(path)));
		label[0].setText(" ");
		label[0].setFont(new Font(label[1].getFont().getName(), Font.BOLD, 20));
		label[0].setVerticalTextPosition(SwingConstants.TOP);
		label[0].setHorizontalTextPosition(SwingConstants.CENTER);
		centerPanel.setBackground(GUI_Board.BASECOLOR);
		for(JLabel l : GUI_Center.label){
			l.setBackground(GUI_Board.BASECOLOR);
			l.setForeground(Color.BLACK);
		}
		for(int i=0; i<GUI_Board.MAX_PLAYER_COUNT; i++){
			GUI_Center.cars[i].setVisible(false);
		}
	}
    /**
     * Displays the next chance card
     */
	public void displayChanceCard() {
        clearLabels();
        centerPanel.setBackground(Color.WHITE);
        label[1].setText("<html><table><tr><td>" + chanceCardText);
	}
    /**
     * Set the next chance card
     * @param txt the text to display on the center.
     */
    public void setChanceCard(String txt) {
        GUI_Center.chanceCardText = txt;
    }
	public JPanel getCenterPanel(){
		return centerPanel;
	}
}