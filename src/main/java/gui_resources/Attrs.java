package gui_resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Attrs {
    private static final String BUNDLE_NAME = "gui_resources.attributes";
    
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
        
    private Attrs() {}
    public static String getString(String key, Object... args) {
        try {
            return String.format(RESOURCE_BUNDLE.getString(key), args);
        } catch (MissingResourceException e) {
            throw new NullPointerException("Bad key [" + key + "]!");
        }
    }
    public static String getImagePath(String key) {
        try {
            String path = RESOURCE_BUNDLE.getString("Image.Path");
            String file = RESOURCE_BUNDLE.getString(key);
            return path + file;
        } catch (MissingResourceException e) {
            throw new NullPointerException("Bad key [" + key + "]!");
        }
    }
}
