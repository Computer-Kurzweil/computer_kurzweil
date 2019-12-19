package org.woehlke.simulation.mandelbrot.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

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
    private String buttonsZoomOut;

    public Config() {
        String appPropertiesFile = (APP_PROPERTIES_FILENAME);
        Properties prop = new Properties();
        try (
            InputStream input = new FileInputStream(appPropertiesFile)) {
            prop.load(input);
            /*
            for( Object key : prop.keySet()){
                System.out.println(prop.get(key).toString());
            }
            */
            title = prop.getProperty(KEY_TITLE,TITLE);
            subtitle = prop.getProperty(KEY_SUBTITLE,SUBTITLE);
            copyright = prop.getProperty(KEY_COPYRIGHT,COPYRIGHT);
            String widthString = prop.getProperty(KEY_WIDTH,WIDTH);
            String heightString = prop.getProperty(KEY_HEIGHT,HEIGHT);
            width = Integer.parseInt(widthString);
            height = Integer.parseInt(heightString);
            buttonsLabel = prop.getProperty(KEY_BUTTONS_LABEL,BUTTONS_LABEL);
            buttonsSwitch = prop.getProperty(KEY_BUTTONS_SWITCH,BUTTONS_SWITCH);
            buttonsZoom = prop.getProperty(KEY_BUTTONS_ZOOM,BUTTONS_ZOOM);
            buttonsZoomOut = prop.getProperty(KEY_BUTTONS_ZOOMOUT,BUTTONS_ZOOMOUT);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Config)) return false;
        Config config = (Config) o;
        return getWidth() == config.getWidth() &&
            getHeight() == config.getHeight() &&
            Objects.equals(getTitle(), config.getTitle()) &&
            Objects.equals(getSubtitle(), config.getSubtitle()) &&
            Objects.equals(getCopyright(), config.getCopyright()) &&
            Objects.equals(getButtonsLabel(), config.getButtonsLabel()) &&
            Objects.equals(getButtonsSwitch(), config.getButtonsSwitch()) &&
            Objects.equals(getButtonsZoom(), config.getButtonsZoom()) &&
            Objects.equals(getButtonsZoomOut(), config.getButtonsZoomOut());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getSubtitle(), getCopyright(), getWidth(), getHeight(), getButtonsLabel(), getButtonsSwitch(), getButtonsZoom(), getButtonsZoomOut());
    }

    @Override
    public String toString() {
        return "Config{" +
            "title='" + title + '\'' +
            ", subtitle='" + subtitle + '\'' +
            ", copyright='" + copyright + '\'' +
            ", width=" + width +
            ", height=" + height +
            ", buttonsLabel='" + buttonsLabel + '\'' +
            ", buttonsSwitch='" + buttonsSwitch + '\'' +
            ", buttonsZoom='" + buttonsZoom + '\'' +
            ", buttonsZoomOut='" + buttonsZoomOut + '\'' +
            '}';
    }
}
