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
import desktop_board.Board;
import desktop_board.Center;
import desktop_codebehind.FieldMouseListener;
import desktop_codebehind.Player;
import desktop_codebehind.SwingComponentFactory;

public abstract class Field {
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
	private boolean[] hasCars = new boolean[Board.MAX_PLAYER_COUNT];
	private SwingComponentFactory factory = new SwingComponentFactory();
	private JLabel[] cars;
	private static Point[] points = new Point[40];
    private static int nextPoint = 0;
    
    static{
        int i = 0;
        for(int x=10; x > 0; x--){
            Field.points[i++] = new Point(x, 10);
        }
        for(int y=10; y > 0; y--){
            Field.points[i++] = new Point(0, y);
        }
        for(int x=0; x < 10; x++){
            Field.points[i++] = new Point(x, 0);
        }
        for(int y=0; y < 10; y++){
            Field.points[i++] = new Point(10, y);
        }
    }
    
    public static class Builder<E> {
        protected String title = "Title";
        protected String subText = "subText";
        protected String description = "description";
        protected String picture = "default";
        protected String rent = "Rent";
        protected Color bgColor = Color.LIGHT_GRAY;
        protected Color fgColor = Color.BLACK;
        
        @SuppressWarnings("unchecked")
        public E setFgColor(Color fgColor) {
            this.fgColor = fgColor;
            return (E)this;
        }
        @SuppressWarnings("unchecked")
        public E setBgColor(Color bgColor) {
            this.bgColor = bgColor;
            return (E)this;
        }
    }
    
	//TODO add number to parameters - just for display
    protected Field(Color bgColor, Color fgColor, String title, String subText, String description) {
        this(bgColor, fgColor, title, subText, description, BorderFactory.createLineBorder(Color.BLACK));
    }
	protected Field(Color bgColor, Color fgColor, String title, String subText, String description, Border border) {
		nextNumber = (nextNumber % 40) + 1;
		this.number = nextNumber;
		Point p = Field.points[nextPoint];
        nextPoint = ++nextPoint % 40;
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
	public boolean hasCar(String name) {
		Player p = Board.getInstance().getPlayer(name);
		return this.hasCars[p.getNumber()];
	}
	public void setCar(String name, boolean hasCar) {
		Board board = Board.getInstance();
		Player p = board.getPlayer(name);
		if(p != null) {
			this.hasCars[p.getNumber()] = hasCar;
			this.cars[p.getNumber()].setIcon(new ImageIcon(p.getImage()));
			this.cars[p.getNumber()].setVisible(hasCar);
		}
	}
	/**
	 * Makes a standard label
	 * @param height - in px
	 * @return a JLabel object
	 */
	public JLabel makeLabel(int height) {
		JLabel label = new JLabel();
		this.factory.setSize(label, 1 * FIELDWIDTH - 2, height);
		label.setFont(new java.awt.Font(Board.FONT, 0, Board.FONTSIZE));
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
	public JLayeredPane getPanel() {
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
	public void setCarIcons(JLabel[] cars) {
		this.cars = cars;
	}
	/**
	 * Each type of field displays information on the center
	 */
	public void displayOnCenter(){
		Center.getInstance().clearLabels();
		Center.getInstance().setBGColor(this.bgColor);
		Center.getInstance().setFGColor(this.fgColor);
		for(JLabel l : Center.label){
			l.setBackground(this.bgColor);
			l.setForeground(this.fgColor);
		}
		Center.label[0].setText(""+this.number);
	}
	protected void displayCarOnCenter() {
		for(int i = 0; i < Board.MAX_PLAYER_COUNT; i++) {
			Player p = Board.playerList[i];
			if(p != null && hasCar(p.getName())) {
				Center.cars[i].setIcon(new ImageIcon(p.getImage()));
				Center.cars[i].setVisible(true);
			} else {
				Center.cars[i].setIcon(null);
				Center.cars[i].setVisible(false);
			}
		}
	}
}