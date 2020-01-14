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


@Log
@ToString
@EqualsAndHashCode
@SpringBootConfiguration
@Configuration
@Valid
@Validated
@NoArgsConstructor
@ConfigurationProperties("org.woehlke.simulation.cca.config")
public class CyclicCellularAutomatonProperties {

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
