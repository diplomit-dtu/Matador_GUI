package gui.resources;

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
        try {
            String file = RESOURCE_BUNDLE.getString(key);
            return Attrs.class.getClassLoader().getResource(file);
        } catch (MissingResourceException e) {
            throw new NullPointerException("Bad key [" + key + "]!");
        }
    }
}
