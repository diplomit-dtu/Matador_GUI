package desktop_codebehind;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Field;
import desktop_fields.Jail;
import desktop_fields.Refuge;
import desktop_fields.Shipping;
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_fields.Tax;

/**
 * Creates all the fields
 * @author Ronnie
 */
public final class FieldFactory {
    public static String path = null;
    private enum Type {
        BREWERY, CHANCE, JAIL, REFUGE, SHIPPING, START, STREET, TAX
    }
    public static ArrayList<Field> fields = null;
    
    private FieldFactory() {
        
    }
    
    public static void makeFields() {
        FieldFactory fact = new FieldFactory();
        if (fields == null || fields.size() == 0) {
            try {
                fact.parse();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
        }
    }
    
    private boolean parse() {
        String str = readFile();
        
        if (str == null) {
            return false;
        }
        
        // 75% > 40 so increase in capacity is required
        fields = new ArrayList<Field>(54);
        
        for (String field : str.split("\\|\\|")) {
            String[] attributes = field.split(";;");
            // determine type
            String type = valueOf("type", attributes);
            // Verify type
            Type T = validType(type);
            if (T == null) {
                return false;
            }
            switch(T) {
                case BREWERY:
                    createBrewery(attributes);
                    break;
                case CHANCE:
                    createChance(/* attributes */);
                    break;
                case JAIL:
                    createJail(attributes);
                    break;
                case REFUGE:
                    createRefuge(attributes);
                    break;
                case SHIPPING:
                    createShipping(attributes);
                    break;
                case START:
                    createStart(attributes);
                    break;
                case STREET:
                    createStreet(attributes);
                    break;
                case TAX:
                    createTax(attributes);
                    break;
                default:
                    break;
            }
        }
        return true;
    }
    
    private String readFile() {
        BufferedReader in = null;
        String str = "";
        File file;
        try {
            if (path == null) {
                InputStream is = getClass().getResourceAsStream("/fields.txt");
                in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            } else {
                file = new File(path);
                in = new BufferedReader(new FileReader(file));
            }
            
            String line;
            while ((line = in.readLine()) != null) {
                str += line.trim() + "\n";
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return str;
    }
    private void createBrewery(String[] attributes) {
        String picture = valueOf("picture", attributes);
        String title = valueOf("title", attributes);
        String subText = valueOf("subText", attributes);
        String description = valueOf("description", attributes);
        String leje = valueOf("leje", attributes);
        Field f = new Brewery.Builder()
            .setPicture(picture)
            .setTitle(title)
            .setSubText(subText)
            .setDescription(description)
            .setRent(leje)
            .build();
        fields.add(f);
    }
    private void createChance() {
        Field f = new Chance.Builder().build();
        fields.add(f);
    }
    private void createJail(String[] attributes) {
        String picture = valueOf("picture", attributes);
        String title = valueOf("title", attributes);
        String subText = valueOf("subText", attributes);
        String description = valueOf("description", attributes);
        Field f = new Jail.Builder()
            .setPicture(picture)
            .setTitle(title)
            .setSubText(subText)
            .setDescription(description)
            .build();
        fields.add(f);
    }
    private void createRefuge(String[] attributes) {
        String picture = valueOf("picture", attributes);
        String title = valueOf("title", attributes);
        String subText = valueOf("subText", attributes);
        String description = valueOf("description", attributes);
        Field f = new Refuge.Builder()
            .setPicture(picture)
            .setTitle(title)
            .setSubText(subText)
            .setDescription(description)
            .build();
        fields.add(f);
    }
    private void createShipping(String[] attributes) {
        String picture = valueOf("picture", attributes);
        String title = valueOf("title", attributes);
        String subText = valueOf("subText", attributes);
        String description = valueOf("description", attributes);
        String leje = valueOf("leje", attributes);
        Field f = new Shipping.Builder()
            .setPicture(picture)
            .setTitle(title)
            .setSubText(subText)
            .setDescription(description)
            .setRent(leje)
            .build();
        fields.add(f);
    }
    private void createStart(String[] attributes) {
        Color bgColor = toColor(valueOf("backgroundColor", attributes));
        Color fgColor = toColor(valueOf("foregroundColor", attributes));
        String title = valueOf("title", attributes);
        String subText = valueOf("subText", attributes);
        String description = valueOf("description", attributes);
        Field f = new Start.Builder()
            .setBgColor(bgColor)
            .setFgColor(fgColor)
            .setTitle(title)
            .setSubText(subText)
            .setDescription(description)
            .build();
        fields.add(f);
    }
    private void createStreet(String[] attributes) {
        String title = valueOf("title", attributes);
        Color bgColor = toColor(valueOf("backgroundColor", attributes));
        Color fgColor = toColor(valueOf("foregroundColor", attributes));
        String subText = valueOf("subText", attributes);
        String description = valueOf("description", attributes);
        String leje = valueOf("leje", attributes);
        Field f = new Street.Builder()
            .setTitle(title)
            .setBgColor(bgColor)
            .setFgColor(fgColor) 
            .setSubText(subText)
            .setDescription(description)
            .setRent(leje)
            .build();
        fields.add(f);
    }
    private void createTax(String[] attributes) {
        String title = valueOf("title", attributes);
        String subText = valueOf("subText", attributes);
        String description = valueOf("description", attributes);
        Field f = new Tax.Builder()
            .setTitle(title)
            .setSubText(subText)
            .setDescription(description)
            .build();
        fields.add(f);
    }
    private Type validType(String type) {
        for (Type t : Type.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }
    private String valueOf(String label, String[] attributes) {
        for (String a : attributes) {
            if (a.split("::")[0].trim().equalsIgnoreCase(label)) {
                return a.split("::")[1];
            }
        }
        System.err.println("GUI - Missing attribute: " + label);
        return null;
    }
    private Color toColor(String str) {
        int r = Integer.parseInt(str.split(",,")[0]);
        int g = Integer.parseInt(str.split(",,")[1]);
        int b = Integer.parseInt(str.split(",,")[2]);
        return new Color(r, g, b);
    }
}
