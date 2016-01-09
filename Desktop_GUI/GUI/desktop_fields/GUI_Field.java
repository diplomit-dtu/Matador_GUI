package desktop_fields;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import desktop_codebehind.GUI_Center;
import desktop_codebehind.SwingComponentFactory;

public abstract class GUI_Field {
	public static final int FIELDWIDTH = 63;
	public static final int FIELDHEIGHT = 63;
	private static int nextNumber = 0;
	protected int number;
	private int x;
	private int y;
	protected JLayeredPane layered = new JLayeredPane();
	protected JLabel titleLabel;
	protected JLabel subTextLabel;
	protected Color bgColor;
	protected Color fgColor;
	protected String title;
	protected String subText;
	protected String description;
	private boolean[] hasCars = new boolean[GUI_Board.MAX_PLAYER_COUNT];
	private SwingComponentFactory factory = new SwingComponentFactory();
	private JLabel[] cars;
    
    //Default values
    protected static final String TITLE = "Title";
    protected static final String SUBTEXT = "subText";
    protected static final String DESCRIPTION = "description";
    protected static final String PICTURE = "default";
    protected static final String RENT = "Rent";
    protected static final Color BG_COLOR = Color.LIGHT_GRAY;
    protected static final Color FG_COLOR = Color.BLACK;
    
	//TODO add number to parameters - just for display
    protected GUI_Field(Color bgColor, Color fgColor, String title, String subText, String description) {
        this(bgColor, fgColor, title, subText, description, BorderFactory.createLineBorder(Color.BLACK));
    }
	protected GUI_Field(Color bgColor, Color fgColor, String title, String subText, String description, Border border) {
	    title = title.replace("\n", "<BR>");
	    subText = subText.replace("\n", "<BR>");
	    description = description.replace("\n", "<BR>");
	    
		nextNumber = (nextNumber % 40) + 1;
		this.number = nextNumber;
		Point p = GUI_Board.points[GUI_Board.nextPoint];
        GUI_Board.nextPoint = ++GUI_Board.nextPoint % 40;
        this.x = p.x;
        this.y = p.y;
		this.bgColor = bgColor;
		this.fgColor = fgColor;
		this.makeLabels();
		this.setTitle(title);
		this.setSubText(subText);
		this.setDescription(description);
		this.layered.setBackground(bgColor);
		this.layered.setForeground(fgColor);
		this.layered.setOpaque(true);
		this.layered.setBorder(border);
		this.factory.setSize(this.layered, 1 * FIELDWIDTH, 1 * FIELDHEIGHT);
		this.layered.addMouseListener(new FieldMouseListener(this));
		this.layered.setLayout(new GridBagLayout());
	}
	private void makeLabels() {
		this.titleLabel = makeLabel(24);
		this.titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		this.titleLabel.setText(this.title);
		this.subTextLabel = makeLabel(10);
		this.subTextLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		this.subTextLabel.setText(this.subText);
	}
	public boolean hasCar(GUI_Player p) {
		return this.hasCars[p.getNumber()];
	}
	public void setCar(GUI_Player p, boolean hasCar) {
		if(p != null) {
			this.hasCars[p.getNumber()] = hasCar;
			this.cars[p.getNumber()].setIcon(new ImageIcon(p.getImage()));
			this.cars[p.getNumber()].setVisible(hasCar);
		}
	}
	public void removeAllCars(){
	    for(int i = 0; i < hasCars.length; i++){
	        hasCars[i] = false;
	        cars[i].setVisible(false);
	    }
	}
	/**
	 * Makes a standard label
	 * @param height - in px
	 * @return a JLabel object
	 */
	protected JLabel makeLabel(int height) {
		JLabel label = new JLabel();
		this.factory.setSize(label, 1 * FIELDWIDTH - 2, height);
		label.setFont(new java.awt.Font(GUI_Board.FONT, 0, GUI_Board.FONTSIZE));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(this.bgColor);
		label.setForeground(this.fgColor);
		return label;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	protected JLayeredPane getPanel() {
		return this.layered;
	}
	public void setTitle(String title) {
		this.title = "<html><center>" + title.replace("\\n", "<br>");
		this.titleLabel.setText(this.title);
	}
	public void setSubText(String subText) {
		this.subText = subText;
		this.subTextLabel.setText(subText);
	}
	public void setDescription(String description) {
		if(description.length() > 20) {
			this.description = "<html><table><tr><td>"
				+ description.replace("\\n", "<br>");
		} else {
			this.description = description;
		}
	}
	public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getTitle() {
        return title.replace("<html><center>", "").replace("<br>", "");
    }
    public String getSubText() {
        return subText;
    }
    public String getDescription() {
        return description.replace("<html><table><tr><td>", "").replace("<br>", "\n");
    }
    public void setBackGroundColor(Color color){
	    this.bgColor = color;
	    this.layered.setBackground(bgColor);
	}
	public void setForeGroundColor(Color color){
	    this.fgColor = color;
        this.layered.setForeground(fgColor);
        titleLabel.setForeground(fgColor);
        subTextLabel.setForeground(fgColor);
	}
	protected void setCarIcons(JLabel[] cars) {
		this.cars = cars;
	}
	/**
	 * Each type of field displays information on the center
	 */
	protected void displayOnCenter(){
		GUI_Center.getInstance().clearLabels();
		GUI_Center.getInstance().setBGColor(this.bgColor);
		GUI_Center.getInstance().setFGColor(this.fgColor);
		for(JLabel l : GUI_Center.label){
			l.setBackground(this.bgColor);
			l.setForeground(this.fgColor);
		}
		GUI_Center.label[0].setText(""+this.number);
	}
	protected void displayCarOnCenter() {
		for(int i = 0; i < GUI_Board.MAX_PLAYER_COUNT; i++) {
			GUI_Player p = GUI_Board.playerList[i];
			if(p != null && hasCar(p)) {
				GUI_Center.cars[i].setIcon(new ImageIcon(p.getImage()));
				GUI_Center.cars[i].setVisible(true);
			} else {
				GUI_Center.cars[i].setIcon(null);
				GUI_Center.cars[i].setVisible(false);
			}
		}
	}
}