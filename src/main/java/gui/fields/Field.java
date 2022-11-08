package gui.fields;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import gui.codebehind.Center;
import gui.codebehind.SwingComponentFactory;
import gui.resources.Attrs;

/**
 * Abstract base-class for GUI fields. Pass array of Fields to GUI to create your own board.
 */
public abstract class Field {
    public static final int FIELDWIDTH = 63;
    public static final int FIELDHEIGHT = 63;
    protected final JLayeredPane layered = new JLayeredPane();
    protected JLabel titleLabel;
    protected JLabel subTextLabel;
    protected Color bgColor;
    protected Color fgColor;
    protected String title;
    protected String subText;
    protected String description;
    private final SwingComponentFactory factory = new SwingComponentFactory();
    private final HashMap<Integer, JLabel> carLabelsMap = new HashMap<Integer, JLabel>();
    private JLabel[] carLabels;

    /** List of cars that are being drawn by this view */
    private final ArrayList<Car> drawnCars = new ArrayList<>();
    
    //Default values
    protected static final String TITLE = Attrs.getString("Field.Default_title");
    protected static final String SUBTEXT = Attrs.getString("Field.Default_SubText");
    protected static final String DESCRIPTION = Attrs.getString("Field.Default_Description");
    protected static final String PICTURE = Attrs.getString("Field.Default_Picture");
    protected static final String RENT = Attrs.getString("Field.Default_Rent");
    protected static final Color BG_COLOR = Color.LIGHT_GRAY;
    protected static final Color FG_COLOR = Color.BLACK;


    //TODO add number to parameters - just for display
    protected Field(Color bgColor, Color fgColor, String title, String subText, String description) {
        this(bgColor, fgColor, title, subText, description, BorderFactory.createLineBorder(Color.BLACK));
    }


    protected Field(Color bgColor, Color fgColor, String title, String subText, String description, Border border) {
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


    /**
     * Checks whether or not a specific Player's car is currently
     * positioned on this field.
     *
     * @param player    The Player whose car to check for
     * @return  True if 'player's car is on the field.
     */
    public boolean hasCar(Player player) {
        return Objects.equals(player.getCar().getPosition(), this);
    }


    /**
     * @deprecated  Cars should be placed / moved around using the {@link Car#setPosition(Field)} instead.
     *
     * Places the car of a Player on this field.
     *
     * @param player The player, which car is to be placed on the field
     * @param display Whether or not the car should be displayed.
     */
    @Deprecated
    public void setCar(Player player, boolean display) {
        if( !display && !hasCar(player) ) return;

        if( display && hasCar(player) ) return;

        if( !display )
            player.getCar().setPosition(null);
        else
            player.getCar().setPosition(this);



        JLabel l = carLabelsMap.get(player.getId());
        if(l != null){
            l.setIcon(new ImageIcon(player.getImage()));
            l.setVisible(display);
        } else {
            for(JLabel lbl : carLabels){
                if(lbl.getIcon() == null){
                    lbl.setIcon(new ImageIcon(player.getImage()));
                    lbl.setVisible(display);
                    carLabelsMap.put(player.getId(), lbl);
                    return;
                }
            }
        }
    }


    /**
     *  Adds the car as a car, which should be drawn on this field
     *  This method should not be called by the user of the library.
     */
    public void drawCar(Player player, boolean display){
        /*  Note on this method:
            It takes over the original code from the setCar method, as that method
            should no longer update the drawing.
            It's a temporary solution (until we move to 4.x), to have this method
            as the end user shouldn't have access to it.
         */

        JLabel l = carLabelsMap.get(player.getId());
        if(l != null){
            l.setIcon(new ImageIcon(player.getImage()));
            l.setVisible(display);
        } else {
            for(JLabel lbl : carLabels){
                if(lbl.getIcon() == null){
                    lbl.setIcon(new ImageIcon(player.getImage()));
                    lbl.setVisible(display);
                    carLabelsMap.put(player.getId(), lbl);
                    return;
                }
            }
        }

        // Update list of cars which are drawn by this field
        if( display && !drawnCars.contains(player.getCar()) )
            drawnCars.add(player.getCar());
        else if( !display && drawnCars.contains(player.getCar()))
            drawnCars.remove(player.getCar());
    }

    /**
     * @deprecated  Cars should no longer be placed using the Field class (or any inheriting classes), but the
     *              the car class directly. This method will be removed in version 4.x
     *
     * Remove all the cars from this field
     */
    @Deprecated
    public void removeAllCars(){
        /*  We iterate backwards, since cars will be removed from the list
            during iteration, when set their position to null, and their
            position was this field (which we expect them to be). */
        for(int i=drawnCars.size()-1; i >= 0; i-- ) {
            drawnCars.get(i).setPosition(null);
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
        label.setFont(new java.awt.Font(Board.FONT, 0, Board.FONTSIZE));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(this.bgColor);
        label.setForeground(this.fgColor);
        return label;
    }

    protected JLayeredPane getPanel()
    {
        return this.layered;
    }


    /**
     * Sets the title of this field. The title is displayed on the field,
     * and in the center square when the field is clicked.
     *
     * @param title The title (mind the length)
     */
    public void setTitle(String title) {
        this.title = "<html><center>" + title.replace("\\n", "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        this.titleLabel.setText(this.title);
    }


    /**
     * Sets the subtext of this field. The subtext is a small text displayed
     * on the buttom of the field, and in the center square whe field is
     * clicked.<br>
     * It's used for displaying price on streets.
     *
     * @param subText The subtext (mind the length).
     */
    public void setSubText(String subText) {
        this.subText = subText;
        this.subTextLabel.setText(subText);
    }


    /**
     * Sets the description of a field. The description is displayed in the center
     * sqaure, when the field is clicked.
     *
     * @param description The description
     */
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


    protected void displayOnCenter(Player[] playerList){
        Center.getInstance().clearLabels();
        Center.getInstance().setBGColor(this.bgColor);
        Center.getInstance().setFGColor(this.fgColor);
        for(JLabel l : Center.label){
            l.setBackground(this.bgColor);
            l.setForeground(this.fgColor);
        }
        Center.label[0].setText("");
    }


    protected void displayCarOnCenter(Player[] playerList) {
        for(int i = 0; i < Board.MAX_PLAYER_COUNT; i++) {
            Player p = playerList[i];
            if(p != null && hasCar(p)) {
                Center.cars[i].setIcon(new ImageIcon(p.getImage()));
                Center.cars[i].setVisible(true);
            } else {
                Center.cars[i].setIcon(null);
                Center.cars[i].setVisible(false);
            }
        }
    }
}