package org.woehlke.simulation.cca.config;

import lombok.*;
import lombok.extern.java.Log;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Log
@ToString
@EqualsAndHashCode
@SpringBootConfiguration
@Configuration
@Valid
@Validated
@AllArgsConstructor
@ConfigurationProperties("org.woehlke.simulation.cca.config")
public class CyclicCellularAutomatonProperties implements CyclicCellularAutomatonPropertiesI {

    @NotBlank @Getter @Setter private String title;
    @NotBlank @Getter @Setter private String subtitle;
    @NotNull  @Getter @Setter private Integer width;
    @NotNull  @Getter @Setter private Integer height;
    @NotBlank @Getter @Setter private String neighborhood;
    @NotBlank @Getter @Setter private String buttonVonNeumann;
    @NotBlank @Getter @Setter private String buttonMoore;
    @NotBlank @Getter @Setter private String buttonWoehlke;
    @NotBlank @Getter @Setter private String copyright;

    private static final int TITLE_HEIGHT = 30;

    public CyclicCellularAutomatonProperties() {
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
            copyright=prop.getProperty(KEY_COPYRIGHT,COPYRIGHT);
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

}
