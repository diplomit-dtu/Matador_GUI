package gui.util;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Attrs {
    private static final String BUNDLE_NAME = "attributes";
    
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
        
    private Attrs() {}
    public static String getString(String key, Object... args) {
        try {
            return String.format(RESOURCE_BUNDLE.getString(key), args);
        } catch (MissingResourceException e) {
            throw new NullPointerException("Bad key [" + key + "]!");
        }
    }

    public static URL getImagePath(String key) {
        return getImagePath(key, 0);
    }
    public static URL getImagePath(String key, int index) {
        try {
            String [] file = RESOURCE_BUNDLE.getString(key).split(",");
            return Attrs.class.getClassLoader().getResource(file[index]);
        } catch (IndexOutOfBoundsException | MissingResourceException e) {
            throw new UnknownError("Bad key [" + key + "," + index + "]!");
        }
    }
}
