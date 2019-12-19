package org.woehlke.simulation.mandelbrot.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class Config implements ConfigProperties {

    private String title;
    private String subtitle;
    private String copyright;
    private int width;
    private int height;

    private String buttonsLabel;
    private String buttonsSwitch;
    private String buttonsZoom;

    private String buttonsZoomLabel;
    private String buttonsZoomOut;

    private Boolean logDebug;

    public static Logger log = Logger.getLogger(Config.class.getName());

    public Config() {
        String appPropertiesFile = (APP_PROPERTIES_FILENAME);
        Properties prop = new Properties();
        try (
            InputStream input = new FileInputStream(appPropertiesFile)) {
            prop.load(input);
            title = prop.getProperty(KEY_TITLE,TITLE);
            subtitle = prop.getProperty(KEY_SUBTITLE,SUBTITLE);
            copyright = prop.getProperty(KEY_COPYRIGHT,COPYRIGHT);
            String widthString = prop.getProperty(KEY_WIDTH,WIDTH);
            String heightString = prop.getProperty(KEY_HEIGHT,HEIGHT);
            width = Integer.parseInt(widthString);
            height = Integer.parseInt(heightString);
            buttonsLabel = prop.getProperty(KEY_BUTTONS_LABEL,BUTTONS_LABEL);
            buttonsSwitch = prop.getProperty(KEY_BUTTONS_SWITCH,BUTTONS_SWITCH);
            buttonsZoomLabel = prop.getProperty(KEY_BUTTONS_ZOOM_LABEL,BUTTONS_ZOOM_LABEL);
            buttonsZoom = prop.getProperty(KEY_BUTTONS_ZOOM,BUTTONS_ZOOM);
            buttonsZoomOut = prop.getProperty(KEY_BUTTONS_ZOOMOUT,BUTTONS_ZOOMOUT);
            String logDebugString = prop.getProperty(KEY_DEBUG_LOG,DEBUG_LOG);
            logDebug = Boolean.parseBoolean(logDebugString);
            if(logDebug){
                for( Object key : prop.keySet()){
                    log.info(prop.get(key).toString());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getCopyright() {
        return copyright;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getButtonsLabel() {
        return buttonsLabel;
    }

    public String getButtonsSwitch() {
        return buttonsSwitch;
    }

    public String getButtonsZoom() {
        return buttonsZoom;
    }

    public String getButtonsZoomOut() {
        return buttonsZoomOut;
    }

    public Boolean getLogDebug() {
        return logDebug;
    }

    public String getButtonsZoomLabel() {
        return buttonsZoomLabel;
    }
}
