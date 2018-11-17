package gui_fields;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;
import gui_resources.Attrs;

/**
 * Abstract base-class for GUI fields. Pass array of GUI_Fields to GUI to create your own board.
 */
public abstract class GUI_Field {
    public static final int FIELDWIDTH = 63;
    public static final int FIELDHEIGHT = 63;
    protected JLayeredPane layered = new JLayeredPane();
    protected JLabel titleLabel;
    protected JLabel subTextLabel;
    protected Color bgColor;
    protected Color fgColor;
    protected String title;
    protected String subText;
    protected String description;
    private SwingComponentFactory factory = new SwingComponentFactory();
    private HashMap<Integer, JLabel> cars = new HashMap<Integer, JLabel>();
    private JLabel[] carLabels;
    
    //Default values
    protected static final String TITLE = Attrs.getString("GUI_Field.Default_title");
    protected static final String SUBTEXT = Attrs.getString("GUI_Field.Default_SubText");
    protected static final String DESCRIPTION = Attrs.getString("GUI_Field.Default_Description");
    protected static final String PICTURE = Attrs.getString("GUI_Field.Default_Picture");
    protected static final String RENT = Attrs.getString("GUI_Field.Default_Rent");
    protected static final Color BG_COLOR = Color.LIGHT_GRAY;
    protected static final Color FG_COLOR = Color.BLACK;
    
    //TODO add number to parameters - just for display
    protected GUI_Field(Color bgColor, Color fgColor, String title, String subText, String description) {
        this(bgColor, fgColor, title, subText, description, BorderFactory.createLineBorder(Color.BLACK));
    }
    protected GUI_Field(Color bgColor, Color fgColor, String title, String subText, String description, Border border) {
        title = title.replace("\n", "<BR>"); //$NON-NLS-1$ //$NON-NLS-2$
        subText = subText.replace("\n", "<BR>"); //$NON-NLS-1$ //$NON-NLS-2$
        description = description.replace("\n", "<BR>"); //$NON-NLS-1$ //$NON-NLS-2$
        
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
    public boolean hasCar(GUI_Player player) {
        return cars.get(player.getId()) != null && cars.get(player.getId()).isVisible();
    }
    public void setCar(GUI_Player p, boolean hasCar) {
        JLabel l = cars.get(p.getId()); 
        if(l != null){
            l.setIcon(new ImageIcon(p.getImage()));
            l.setVisible(hasCar);
        } else {
            for(JLabel lbl : carLabels){
                if(lbl.getIcon() == null){
                    lbl.setIcon(new ImageIcon(p.getImage()));
                    lbl.setVisible(hasCar);
                    cars.put(p.getId(), lbl);
                    return;
                }
            }
        }
    }
    public void removeAllCars(){
        for(Integer key : cars.keySet()){
            cars.get(key).setVisible(false);
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
    protected JLayeredPane getPanel() {
        return this.layered;
    }
    public void setTitle(String title) {
        this.title = "<html><center>" + title.replace("\\n", "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        this.titleLabel.setText(this.title);
    }
    public void setSubText(String subText) {
        this.subText = subText;
        this.subTextLabel.setText(subText);
    }
    public void setDescription(String description) {
        if(description.length() > 20) {
            this.description = "<html><table><tr><td>" //$NON-NLS-1$
                + description.replace("\\n", "<br>"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            this.description = description;
        }
    }
    
    
    public String getTitle() {
        return title.replace("<html><center>", "").replace("<br>", "").replace("<BR>", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    }
    public String getSubText() {
        return subText.replace("<br>", "").replace("<BR>", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }
    public String getDescription() {
        return description.replace("<html><table><tr><td>", "").replace("<br>", "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
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
        this.carLabels = cars;
    }
    /**
     * Each type of field displays information on the center
     */
    protected void addMouseListener(FieldMouseListener listener){
        this.layered.addMouseListener(listener);
    }
    protected void displayOnCenter(GUI_Player[] playerList){
        GUI_Center.getInstance().clearLabels();
        GUI_Center.getInstance().setBGColor(this.bgColor);
        GUI_Center.getInstance().setFGColor(this.fgColor);
        for(JLabel l : GUI_Center.label){
            l.setBackground(this.bgColor);
            l.setForeground(this.fgColor);
        }
        GUI_Center.label[0].setText(""); 
    }
    protected void displayCarOnCenter(GUI_Player[] playerList) {
        for(int i = 0; i < GUI_Board.MAX_PLAYER_COUNT; i++) {
            GUI_Player p = playerList[i];
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