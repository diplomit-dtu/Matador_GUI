package desktop_codebehind;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ronnie 
 * Implemented using builder-pattern EX: 
 * Car car = new Car.Builder()
 *   .primaryColor(Color.MAGENTA)
 *   .secondaryColor(Color.BLUE)
 *   .typeTractor() 
 *   .patternDotted() 
 *   .Build();
 */
public final class Car {
    private final BufferedImage image;
    private final Color primaryColor, secondaryColor;

    private Car(Builder builder) {
        this.image = builder.image;
        this.primaryColor = builder.color1;
        this.secondaryColor = builder.color2;
    }
    public BufferedImage getImage() {
        return this.image;
    }
    public Color getPrimaryColor() {
        return this.primaryColor;
    }
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }
    
    public static class Builder {
        private Color color1;
        private Color color2;
        private Type type;
        private Pattern pattern;
        private BufferedImage image;
        
        public Builder() {
            this.color1 = null;
            this.color2 = null;
            this.type = Type.CAR;
            this.pattern = Pattern.FILL;
        }
        
        @SuppressWarnings("synthetic-access")
        public Car build() {
            final int X = this.type.x();
            final int Y = 0;
            
            if (this.color1 == null) {
                this.color1 =
                    this.color2 == null
                        ? Builder.COLORS[(int) (Math.random() * 6)]
                        : this.color2;
            }
            if (this.color2 == null) { this.color2 = this.color1; }
            
            BufferedImage template =
                new SwingComponentFactory().createImage(PATH).getSubimage(X, Y,
                    WIDTH, HEIGHT);
            switch(this.pattern) {
                case FILL:
                    this.image = paintFill(template, this.color1);
                    break;
                case HORIZONTAL_GRADIANT:
                    this.image =
                        paintHorizontalGradiant(template, this.color1,
                            this.color2);
                    break;
                case DIAGONAL_DUAL_COLOR:
                    this.image =
                        paintDiagonalDualColor(template, this.color1,
                            this.color2);
                    break;
                case HORIZONTAL_DUAL_COLOR:
                    this.image =
                        paintHorizontalDualColor(template, this.color1,
                            this.color2, this.type);
                    break;
                case HORIZONTAL_LINE:
                    this.image =
                        paintHorizontalLine(template, this.color1, this.color2,
                            this.type);
                    break;
                case CHECKERED:
                    this.image =
                        paintCheckered(template, this.color1, this.color2);
                    break;
                case DOTTED:
                    this.image =
                        paintDotted(template, this.color1, this.color2);
                    break;
                case ZEBRA:
                    this.image = paintZebra(template, this.color1, this.color2);
                    break;
                default:
                    throw new RuntimeException(
                        "Engine failure! - Car doesn't work.");
            }
            
            return new Car(this);
        }
        
        // Setters
        public Builder primaryColor(Color color) {
            this.color1 = color;
            return this;
        }
        public Builder secondaryColor(Color color) {
            this.color2 = color;
            return this;
        }
        public Builder typeCar() {
            this.type = Type.CAR;
            return this;
        }
        public Builder typeRacecar() {
            this.type = Type.RACECAR;
            return this;
        }
        public Builder typeTractor() {
            this.type = Type.TRACTOR;
            return this;
        }
        public Builder typeUfo() {
            this.type = Type.UFO;
            return this;
        }
        public Builder patternFill() {
            this.pattern = Pattern.FILL;
            return this;
        }
        public Builder patternCheckered() {
            this.pattern = Pattern.CHECKERED;
            return this;
        }
        public Builder patternDiagonalDualColor() {
            this.pattern = Pattern.DIAGONAL_DUAL_COLOR;
            return this;
        }
        public Builder patternDotted() {
            this.pattern = Pattern.DOTTED;
            return this;
        }
        public Builder patternHorizontalDualColor() {
            this.pattern = Pattern.HORIZONTAL_DUAL_COLOR;
            return this;
        }
        public Builder patternHorizontalGradiant() {
            this.pattern = Pattern.HORIZONTAL_GRADIANT;
            return this;
        }
        public Builder patternHorizontalLine() {
            this.pattern = Pattern.HORIZONTAL_LINE;
            return this;
        }
        public final Builder patternZebra() {
            this.pattern = Pattern.ZEBRA;
            return this;
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
        private BufferedImage paintPattern(BufferedImage patternImg,
            BufferedImage img, Color c1,
            Color c2) {
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
        
        // Constants
        private enum Type {
            CAR(0, 15), TRACTOR(1, 11), RACECAR(2, 13), UFO(3, 10);
            private final int x, h;
            private final int width = Car.WIDTH + 1;
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
        }
        private enum Pattern {
            FILL, HORIZONTAL_GRADIANT, DIAGONAL_DUAL_COLOR,
            HORIZONTAL_DUAL_COLOR, HORIZONTAL_LINE, CHECKERED, DOTTED, ZEBRA
        }
        private static final int PRIMARYCOLORSTANDIN = 0xffff0000;
        private static final Color[] COLORS = { Color.RED, Color.BLUE,
            Color.GREEN, Color.YELLOW,
            Color.BLACK, Color.WHITE };
        private static final Map<Pattern, String> patternImages =
            new HashMap<Pattern, String>();
        static {
            patternImages.put(Pattern.DIAGONAL_DUAL_COLOR,
                "/desktop_resources/structures/Diag_dual.png");
            patternImages.put(Pattern.DOTTED, "/desktop_resources/structures/Dotted.png");
            patternImages.put(Pattern.CHECKERED, "/desktop_resources/structures/Checkered.png");
            patternImages.put(Pattern.ZEBRA, "/desktop_resources/structures/Zebra.png");
        }
    }
    
    // Constants
    private static final int WIDTH = 40;
    private static final int HEIGHT = 21;
    private static final String PATH = "/desktop_resources/pics/cars.png";
}
