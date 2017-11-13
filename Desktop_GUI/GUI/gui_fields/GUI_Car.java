package gui_fields;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import gui_codebehind.Observable;
import gui_codebehind.SwingComponentFactory;
import gui_resources.Attrs;

/**
 * @author Ronnie 
 */
public final class GUI_Car extends Observable {
    public enum Type {
        CAR(0, 15), TRACTOR(1, 11), RACECAR(2, 13), UFO(3, 10);
        private final int x, h;
        private final int width = GUI_Car.WIDTH + 1;
        public final static int size = Type.values().length;
        Type(int no, int h) {
            this.x = no * this.width;
            this.h = h;
        }
        public int x() {
            return this.x;
        }
        public int h() {
            return this.h;
        }
        public static Type getTypeFromString(String typeString){
            for(Type type : Type.values()){
                if (type.toString().equals(typeString)) return type;
            }     
            System.err.println("No such Type - choosing default : CAR");
            return CAR;            
        }

    }
    public enum Pattern {
        FILL, HORIZONTAL_GRADIANT, DIAGONAL_DUAL_COLOR,
        HORIZONTAL_DUAL_COLOR, HORIZONTAL_LINE, CHECKERED, DOTTED, ZEBRA;

        public static Pattern getPatternFromString(String patternString) {
            for (Pattern pattern : Pattern.values()) {
                if (pattern.toString().equals(patternString)) return pattern;
            }
            System.err.println("No such Pattern - choosing default: FILL");
            return Pattern.FILL;
        }
    }
    
    private Color primaryColor, secondaryColor;
    private Type type;
    private Pattern pattern;
    private BufferedImage image;
    
    
    public GUI_Car(){
        this(null, null, Type.CAR, Pattern.FILL);
    }
    public GUI_Car(Color primaryColor, Color secondaryColor, Type type, Pattern pattern){
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.type = type;
        this.pattern = pattern;
        repaint();
    }
    
    private void repaint(){
        final int X = this.type.x();
        final int Y = 0;
        
        if (primaryColor == null) {
            primaryColor = secondaryColor == null ? COLORS[(int) (Math.random() * 6)] : secondaryColor;
        }
        if (secondaryColor == null) { secondaryColor = primaryColor; }
        
        BufferedImage template = new SwingComponentFactory().createImage(PATH).getSubimage(X, Y, WIDTH, HEIGHT);
        switch(this.pattern) {
            case FILL: this.image = paintFill(template, primaryColor); break;
            case HORIZONTAL_GRADIANT: this.image = paintHorizontalGradiant(template, primaryColor, secondaryColor); break;
            case DIAGONAL_DUAL_COLOR: this.image = paintDiagonalDualColor(template, primaryColor, secondaryColor); break;
            case HORIZONTAL_DUAL_COLOR: this.image = paintHorizontalDualColor(template, primaryColor, secondaryColor, this.type); break;
            case HORIZONTAL_LINE: this.image = paintHorizontalLine(template, primaryColor, secondaryColor, this.type); break;
            case CHECKERED: this.image = paintCheckered(template, primaryColor, secondaryColor); break;
            case DOTTED: this.image = paintDotted(template, primaryColor, secondaryColor); break;
            case ZEBRA: this.image = paintZebra(template, primaryColor, secondaryColor); break;
            default: throw new RuntimeException(Attrs.getString("Error.BadArgument.Car.pattern"));
        }
    }
    
    protected BufferedImage getImage() {
        return this.image;
    }
    public Color getPrimaryColor() {
        return this.primaryColor;
    }
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }
    
    public void setPrimaryColor(Color color){
        this.primaryColor = color;
        repaint();
        notifyObservers();
    }
    public void setSecondaryColor(Color color){
        this.secondaryColor = color;
        repaint();
        notifyObservers();
    }
    
    // Constants
    private static final int WIDTH = 40;
    private static final int HEIGHT = 21;
    private static final String PATH = Attrs.getImagePath("GUI_Car.Image");
    private static final int PRIMARYCOLORSTANDIN = 0xffff0000;
    private static final Color[] COLORS = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.WHITE };
    private static final Map<Pattern, String> patternImages = new HashMap<Pattern, String>();
    static {
        patternImages.put(Pattern.DIAGONAL_DUAL_COLOR, Attrs.getImagePath("GUI_Car.Image.Pattern.Diagonal_Dual_Color"));
        patternImages.put(Pattern.DOTTED, Attrs.getImagePath("GUI_Car.Image.Pattern.Dotted"));
        patternImages.put(Pattern.CHECKERED, Attrs.getImagePath("GUI_Car.Image.Pattern.Checkered"));
        patternImages.put(Pattern.ZEBRA, Attrs.getImagePath("GUI_Car.Image.Pattern.Zebra"));
    }
    
    
    
 // Helper methods
    private BufferedImage paintFill(BufferedImage img, Color c1) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    img.setRGB(x, y, c1.getRGB());
                }
            }
        }
        return img;
    }
    private BufferedImage paintHorizontalGradiant(BufferedImage img,
        Color c1, Color c2) {
        int r1 = c1.getRed();
        int r2 = c2.getRed();
        int g1 = c1.getGreen();
        int g2 = c2.getGreen();
        int b1 = c1.getBlue();
        int b2 = c2.getBlue();
        
        int min = img.getWidth();
        int max = 0;
        
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    if (x < min) {
                        min = x;
                    }
                    if (x > max) {
                        max = x;
                    }
                }
            }
        }
        double width = max - min;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == 0xffff0000) {
                    double p = (x - min) / width;
                    int r = (int) ((p * (r2 - r1)) + r1);
                    int g = (int) ((p * (g2 - g1)) + g1);
                    int b = (int) ((p * (b2 - b1)) + b1);
                    int rgb = 0xFF000000 + (r << 16) + (g << 8) + b;
                    img.setRGB(x, y, rgb);
                }
            }
        }
        return img;
    }
    private BufferedImage paintDiagonalDualColor(BufferedImage img,
        Color c1, Color c2) {
        String path = patternImages.get(Pattern.DIAGONAL_DUAL_COLOR);
        BufferedImage patternImg =
            new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintHorizontalDualColor(BufferedImage img, Color c1, Color c2,
            Type t) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    int y1 = t.h();
                    if (y < y1) {
                        img.setRGB(x, y, c1.getRGB());
                    } else {
                        img.setRGB(x, y, c2.getRGB());
                    }
                }
            }
        }
        return img;
    }
    private BufferedImage paintHorizontalLine(BufferedImage img, Color c1,
        Color c2, Type t) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    int y1 = t.h();
                    if (y == y1 || y == y1 + 1) {
                        img.setRGB(x, y, c2.getRGB());
                    } else {
                        img.setRGB(x, y, c1.getRGB());
                    }
                }
            }
        }
        return img;
    }
    private BufferedImage paintCheckered(BufferedImage img, Color c1, Color c2) {
        String path = patternImages.get(Pattern.CHECKERED);
        BufferedImage patternImg =
            new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintDotted(BufferedImage img, Color c1, Color c2) {
        String path = patternImages.get(Pattern.DOTTED);
        BufferedImage patternImg =
            new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintZebra(BufferedImage img, Color c1, Color c2) {
        String path = patternImages.get(Pattern.ZEBRA);
        BufferedImage patternImg =
            new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintPattern(BufferedImage patternImg, BufferedImage img, Color c1, Color c2) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    if (patternImg.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                        img.setRGB(x, y, c1.getRGB());
                    } else {
                        img.setRGB(x, y, c2.getRGB());
                    }
                }
            }
        }
        return img;
    }

}
