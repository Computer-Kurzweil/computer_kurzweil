package org.woehlke.computer.kurzweil.mandelbrot.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * Created by tw on 16.12.2019.
 */
public class Config implements ConfigProperties {

    private String title;
    private String subtitle;
    private String copyright;
    private int width;
    private int height;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Config)) return false;
        Config config = (Config) o;
        return getWidth() == config.getWidth() &&
            getHeight() == config.getHeight() &&
            Objects.equals(getTitle(), config.getTitle()) &&
            Objects.equals(getSubtitle(), config.getSubtitle()) &&
            Objects.equals(getCopyright(), config.getCopyright());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getSubtitle(), getCopyright(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return "Config{" +
            "title='" + title + '\'' +
            ", subtitle='" + subtitle + '\'' +
            ", copyright='" + copyright + '\'' +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
