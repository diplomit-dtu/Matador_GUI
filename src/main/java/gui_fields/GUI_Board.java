package gui_fields;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import gui_codebehind.GUI_Center;
import gui_codebehind.JLabelRotatable;
import gui_codebehind.Observer;
import gui_codebehind.SwingComponentFactory;
import gui_fields.GUI_Player.IPlayerNameValidator;
import gui_resources.Attrs;
import org.jetbrains.annotations.NotNull;

/**
 * The board
 * @author Ronnie
 */
public final class GUI_Board extends javax.swing.JFrame implements Observer {
    private static final long serialVersionUID = -2551372048143397506L;
    public static final String FONT = "Tahoma";
    public static final int FONTSIZE = 10;
    public static final Color BASECOLOR = new Color(51, 204, 0);
    public static final int MAX_PLAYER_COUNT = 6;
    
    private SwingComponentFactory factory = new SwingComponentFactory();
    public GUI_Player[] playerList = new GUI_Player[MAX_PLAYER_COUNT];
    private JLayeredPane base;
    private JLayeredPane[][] carPanes = new JLayeredPane[11][11];
    private JLabelRotatable[][] diceLabels = new JLabelRotatable[11][11];
    private JLabel[] playerLabels = new JLabel[MAX_PLAYER_COUNT];
    private JLabel[] iconLabels = new JLabel[MAX_PLAYER_COUNT];
    private JPanel inputPanel = new JPanel();
    private JTextArea messageArea = new JTextArea();
    private ImageIcon[] diceIcons = new ImageIcon[6];
    private GUI_Field[] fields = null;
    private int die1x = 1, die1y = 1, die2x = 1, die2y = 1;
    
    public static Point[] points;
    public static int nextPoint = 0;

    private static void generateSquareBoard(int sideLength) {
        int offSet = (10-sideLength)/2;
        points = new Point[sideLength*4];
        int i = 0;
        for(int x=sideLength; x > 0; x--){
            GUI_Board.points[i++] = new Point(x+offSet, sideLength+offSet);
        }
        for(int y=sideLength; y > 0; y--){
            GUI_Board.points[i++] = new Point(0+offSet, y+offSet);
        }
        for(int x=0; x < sideLength; x++){
            GUI_Board.points[i++] = new Point(x+offSet, 0+offSet);
        }
        for(int y=0; y < sideLength; y++){
            GUI_Board.points[i++] = new Point(sideLength+offSet, y+offSet);
        }
    }
    
    public GUI_Board(GUI_Field[] fields){
        this(fields, BASECOLOR);
    }
    
    public GUI_Board(GUI_Field[] fields, Color backGroundColor) {
        int sideLength = fields.length/4;
        if (sideLength<4)
        {
            sideLength=4;
        }
        if (fields.length%4 != 0){
            sideLength++;
        }
        generateSquareBoard(sideLength);
        this.fields = fields;
        nextPoint = 0;
        
        this.setTitle(Attrs.getString("GUI_Board.Title"));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        for(GUI_Field field : fields){
            if (field!=null)
                field.addMouseListener(new FieldMouseListener(field, playerList));
        }
        
        makeDice();
        makeBase(backGroundColor);
        makeBackGroundPanels(sideLength,backGroundColor);
        makeDiceLabels();
        makePlayerAreas();
        makeCenter();
        makeFieldPanels();
        makeCarPanes();
        makeInputPanel(sideLength);
        setResizable(false);
        
        makeAutogeneratedCrap();
        
        playerList = new GUI_Player[MAX_PLAYER_COUNT];
        this.setVisible(true);
    }
    
    /**
     * Makes images of the dice
     */
    private void makeDice() {
        try {
            String path = Attrs.getImagePath("GUI_Board.Dice");
            BufferedImage image = ImageIO.read(getClass().getResource(path));
            for(int value = 0; value < 6; value++) {
                int x = 0;
                int y = 55 * value;
                this.diceIcons[value] = new ImageIcon(image.getSubimage(x, y, 54, 54));
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Makes a graphical representation of all the fields
     */
    private void makeFieldPanels() {
        int i = 0;
        for(GUI_Field f : fields){
            if(f != null) {
                Point point = points[i];
                JLayeredPane panel = f.getPanel();
                this.base.add(panel, this.factory.createGridBagConstraints(point.x, point.y));
            }
            i++;
        }
    }


    /**
     * Makes room for input fields
     */
    private void makeInputPanel(int sideLength) {
        this.inputPanel.setBackground(new Color(0, 128, 0));
        this.inputPanel.setOpaque(false);
        this.inputPanel = (JPanel) this.factory.setSize(this.inputPanel, 557, 179);
        this.inputPanel.setLayout(new FlowLayout());
        
        this.messageArea.setWrapStyleWord(true);
        this.messageArea.setLineWrap(true);
        this.messageArea.setSize(557, 10);
        this.messageArea.setOpaque(false);
        this.messageArea.setEditable(false);
        this.messageArea.setFocusable(false);
        
        this.inputPanel.add(this.messageArea);
        this.base.setLayer(this.inputPanel, 4);
        int labelOffset = 0;
        if (sideLength==10){
            labelOffset = 1;
        }
        this.base.add(this.inputPanel, this.factory.createGridBagConstraints(labelOffset, labelOffset, 9, 3));
    }


    /**
     * Adds Input components to the board
     * @param message The message for the user
     * @param components : input components (buttons, textfields, drop-down, etc.)
     */
    public void getUserInput(String message, Component... components) {
        this.clearInputPanel();
        this.messageArea.setText(message);
        for(Component c : components) {
            this.inputPanel.add(c);
        }
        this.inputPanel.validate();
        this.inputPanel.repaint();
    }


    /**
     * Resets input panel
     */
    public void clearInputPanel() {
        this.messageArea.setText("");
        this.inputPanel.removeAll();
        this.inputPanel.add(this.messageArea);
        this.inputPanel.validate();
        this.inputPanel.repaint();
    }

    /**
     * Makes the components on which cars can be placed
     */
    private void makeCarPanes() {
        int fieldNo = 0;
        for(GUI_Field f : fields) {
            if(f != null) {
                Point point = points[fieldNo];
            int x = point.x;
            int y = point.y;
            
            JLayeredPane layered = new JLayeredPane();
            this.factory.setSize(layered, GUI_Field.FIELDWIDTH, GUI_Field.FIELDHEIGHT);
            this.carPanes[x][y] = layered;
            layered.setOpaque(false);
            
            JLabel[] cars = new JLabel[MAX_PLAYER_COUNT];
            for(int i = 0; i < MAX_PLAYER_COUNT; i++) {
                JLabel label = new JLabel();
                cars[i] = label;
                label.setOpaque(false);
                this.factory.setSize(label, GUI_Field.FIELDWIDTH, GUI_Field.FIELDHEIGHT);
                label.setBounds(3 * i + 3, 6 * i + 1, GUI_Player.ICON_WIDTH, GUI_Player.ICON_HEIGHT);
                layered.setLayer(label, i + 5);
                label.setVisible(false);
                layered.add(label);
            }
            
            f.setCarIcons(cars);
            // fields are on layer 0.
            this.base.setLayer(layered, 1);
            this.base.add(layered, this.factory.createGridBagConstraints(x, y));
            }
            fieldNo++;
        }
    }
    /**
     * Makes the center
     */
    private void makeCenter() {
        this.base.setLayer(GUI_Center.getInstance().getCenterPanel(), 1);
        this.base.add(GUI_Center.getInstance().getCenterPanel(),
            this.factory.createGridBagConstraints(4, 4, 3, 3));
    }

    /**
     * Makes the base
     * @param backGroundColor 
     */
    private void makeBase(Color backGroundColor) {
        this.base = new javax.swing.JLayeredPane();
        this.factory.setSize(this.base, 11 * GUI_Field.FIELDWIDTH, 11 * GUI_Field.FIELDWIDTH);
        this.base.setLayout(new GridBagLayout());
        this.base.setBackground(backGroundColor);
        this.base.setOpaque(true);
    }


    /**
     * Makes the background
     */
    private void makeBackGroundPanels(int sidelength,Color backGroundColor) {
        int offSet = (10-sidelength)/2;
        for(int x = 1; x < sidelength; x++) {
            for(int y = 1; y < sidelength; y++) {
                JPanel panel = new javax.swing.JPanel();
                panel.setBackground(backGroundColor);
                this.factory.setSize(panel, GUI_Field.FIELDWIDTH, GUI_Field.FIELDHEIGHT);
                this.base.setLayer(panel, 0);
                this.base.add(panel, this.factory.createGridBagConstraints(x+offSet, y+offSet));
            }
        }
    }
    /**
     * Makes the components on which dice can be placed
     */
    private void makeDiceLabels() {
        for(int x = 0; x < 11; x++) {
            for(int y = 0; y < 11; y++) {
                JLabelRotatable label = new JLabelRotatable();
                this.diceLabels[x][y] = label;
                label.setOpaque(false);
                this.factory.setSize(label, GUI_Field.FIELDWIDTH, GUI_Field.FIELDHEIGHT);
                this.base.setLayer(label, 3);
                this.base.add(label, this.factory.createGridBagConstraints(x, y), 0);
            }
        }
    }
    /**
     * Makes the components on which to show players
     */
    private void makePlayerAreas() {
        int x = 7;
        int y = 9;
        int nameLabelSize =2;
        if (fields.length<=32) {
            y = 8;
            nameLabelSize = 1;
        }
        if (fields.length<=28) {
            x=0;
            nameLabelSize = 1;
        }
        for(int i = 0; i < MAX_PLAYER_COUNT; i++) {
            int ycalc = y - i;
            
            JLabel iconLabel = new JLabel();
            this.factory.setSize(iconLabel, 1 * GUI_Field.FIELDWIDTH, 1 * GUI_Field.FIELDWIDTH);
            this.base.setLayer(iconLabel, 1);
            this.base.add(iconLabel, this.factory.createGridBagConstraints(x, ycalc));
            this.iconLabels[i] = iconLabel;
            
            JLabel playerLabel = new JLabel();
            this.factory.setSize(playerLabel, nameLabelSize * GUI_Field.FIELDWIDTH, 1 * GUI_Field.FIELDWIDTH);
            this.base.setLayer(playerLabel, 1);
            this.base.add(playerLabel, this.factory.createGridBagConstraints(x + 1, ycalc, nameLabelSize, 1));
            this.playerLabels[i] = playerLabel;
        }
    }
    /**
     * Basic getter
     * @return fields ref
     */
    public GUI_Field[] getFields() { return fields; }
    /**
     * Places dice on the board
     * @param x1 x-coordinate for die 1
     * @param y1 y-coordinate for die 1
     * @param facevalue1 value of die 1
     * @param rotation1 the angle [0:359] of die 1
     * @param x2 x-coordinate for die 2
     * @param y2 y-coordinate for die 2
     * @param facevalue2 value of die 2
     * @param rotation2 the angle [0:359] of die 2
     */
    public void setDice(int x1, int y1, int facevalue1, int rotation1,
        int x2, int y2, int facevalue2, int rotation2) {
        this.diceLabels[this.die1x][this.die1y].setIcon(null);
        this.diceLabels[this.die2x][this.die2y].setIcon(null);
        this.die1x = x1;
        this.die1y = y1;
        this.die2x = x2;
        this.die2y = y2;
        
        this.diceLabels[x1][y1].setRotation(rotation1);
        this.diceLabels[x1][y1].setHorizontalAlignment(SwingConstants.CENTER);
        this.diceLabels[x1][y1].setVerticalAlignment(SwingConstants.CENTER);
        this.diceLabels[x1][y1].setIcon(this.diceIcons[facevalue1 - 1]);
        this.diceLabels[x2][y2].setRotation(rotation2);
        this.diceLabels[x2][y2].setHorizontalAlignment(SwingConstants.CENTER);
        this.diceLabels[x2][y2].setVerticalAlignment(SwingConstants.CENTER);
        this.diceLabels[x2][y2].setIcon(this.diceIcons[facevalue2 - 1]);
    }


    /**
     * Add a player to the board
     * @param player The player must be created beforehand
     * @return true if player is added, otherwise false
     */
    public boolean addPlayer(GUI_Player player) {
        //Check if out of room
        if(playerList[MAX_PLAYER_COUNT - 1] != null) { return false; }
        
        int i = 0;
        for(; i < MAX_PLAYER_COUNT; i++) {
            if(playerList[i] != null) {
                // No duplicate player names
                if(playerList[i].getName().equals(player.getName())) {
                    return false;
                }
            } else {
                break;
            }
        }
        player.setNumber(i);
        player.addObserver(this);
        player.setValidator(name -> {
            if(name == null || name.isEmpty()) return false;
            for(GUI_Player p : playerList){
                if(p != null && name.equals(p.getName())) return false;
            }
            return true;
        });
        player.getCar().addObserver(this);
        player.getCar().addPositionChangedListener(this::carPositionChanged);

        playerList[i] = player;
        updatePlayers();
        return true;
    }


    /**
     * Updates the board in order to correct player balances
     */
    public void updatePlayers() {
        int position = 0;
        for(GUI_Player p : playerList) {
            if(p != null) {
                Icon icon = new ImageIcon(p.getImage());
                
                this.iconLabels[position].setIcon(icon);
                this.playerLabels[position].setText("<html>" + p.getName() + "<br>"
                    + p.getBalance());
                position++;
            } else {
                break;
            }
        }
    }
    /**
     * Retrieves a player
     * @param name The name of the player
     * @return a Player object
     */
    public GUI_Player getPlayer(String name) {
        for(GUI_Player p : playerList) {
            if(p != null && name.equalsIgnoreCase(p.getName())) {
                return p;
            }
        }
        return null;
    }
    /**
     * Counts how many players are in the game
     * @return number of players
     */
    public int getPlayerCount() {
        int count = 0;
        for(GUI_Player p : playerList) {
            if(p != null) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
    /**
     * Autogenerated DON'T CHANGE!
     */
    private void makeAutogeneratedCrap() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING).addComponent(this.base,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING).addComponent(this.base,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE));
        pack();
    }
    
    @Override
    public void onUpdate() {
        updatePlayers();
    }


    /**
     * Method which is called when the position of a Car has changed, and the display
     * needs to be updated.
     */
    private void carPositionChanged(GUI_Car car, GUI_Field oldPosition, GUI_Field newPosition ){
        if( newPosition != null && !hasField(newPosition) )
            throw new IllegalArgumentException("Car's old position is not a field added to the GUI");

        GUI_Player player = getCarOwner(car);
        if( player == null )
            throw new NullPointerException("Player was null and it was not expected - contact developers!");

        if( oldPosition != null )
            oldPosition.drawCar(player, false);

        if( newPosition != null )
            newPosition.drawCar(player, true);
    }


    /**
     * Utility method to check if this Board contains the given GUI_Field
     */
    public boolean hasField(@NotNull GUI_Field field){
        for( GUI_Field match : fields ){
            if( match.equals(field) ) return true;
        }
        return false;
    }


    /**
     * @return  Returns the GUI_Player whose car is the given car, or null if no GUI_Player owns this car
     */
    public GUI_Player getCarOwner(GUI_Car car) {
        for( GUI_Player player : playerList ) {
            if( player.getCar().equals(car) ) return player;
        }
        return null;
    }


}