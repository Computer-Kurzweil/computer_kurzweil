package org.woehlke.simulation.cyclic.cellular.automaton.config;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config implements ConfigProperties {

    private String title;
    private String subtitle;
    private int width;
    private int height;
    private String neighborhood;
    private String buttonVonNeumann;
    private String buttonMoore;
    private String buttonWoehlke;

    private static final int TITLE_HEIGHT = 30;

    public Config() {
        String appPropertiesFile = (APP_PROPERTIES_FILENAME);
        Properties prop = new Properties();
        try (
            InputStream input = new FileInputStream(appPropertiesFile)) {
            prop.load(input);
            for( Object key : prop.keySet()){
                //System.out.println(prop.get(key).toString());
            }
            title = prop.getProperty(KEY_TITLE,TITLE);
            subtitle = prop.getProperty(KEY_SUBTITLE,SUBTITLE);
            String widthString = prop.getProperty(KEY_WIDTH,WIDTH);
            String heightString = prop.getProperty(KEY_HEIGHT,HEIGHT);
            width = Integer.parseInt(widthString);
            height = Integer.parseInt(heightString);
            neighborhood=prop.getProperty(KEY_NEIGHBORHOOD,NEIGHBORHOOD);
            buttonVonNeumann=prop.getProperty(KEY_BUTTON_VON_NEUMANN,BUTTON_VON_NEUMANN);
            buttonMoore=prop.getProperty(KEY_BUTTON_MOORE,BUTTON_MOORE);
            buttonWoehlke=prop.getProperty(KEY_BUTTON_WOEHLKE,BUTTON_WOEHLKE);
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public Rectangle getFrameBounds(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double startX = (screenSize.getWidth() - height) / 2d;
        double startY = (screenSize.getHeight() - width) / 2d;
        int myheight = Double.valueOf(height).intValue()+TITLE_HEIGHT;
        int mywidth = Double.valueOf(width).intValue();
        int mystartX = Double.valueOf(startX).intValue();
        int mystartY = Double.valueOf(startY).intValue();
        return new Rectangle(mystartX, mystartY, mywidth, myheight);
    }

    public Rectangle getCanvasBounds(){
        return new Rectangle(0, 0, width , height);
    }

    public Point getLatticeDimensions(){
        return new Point(width,height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getButtonVonNeumann() {
        return buttonVonNeumann;
    }

    public String getButtonMoore() {
        return buttonMoore;
    }

    public String getButtonWoehlke() {
        return buttonWoehlke;
    }


}
