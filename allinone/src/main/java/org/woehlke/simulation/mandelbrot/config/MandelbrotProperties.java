package org.woehlke.simulation.mandelbrot.config;

import lombok.*;
import lombok.extern.java.Log;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.woehlke.simulation.allinone.model.LatticePoint;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.beans.Transient;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Log
@ToString
@EqualsAndHashCode
@SpringBootConfiguration
@Configuration
@Valid
@Validated
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("org.woehlke.simulation.mandelbrot.config")
public class MandelbrotProperties {

    @Getter @Setter @NotBlank private String title;
    @Getter @Setter @NotBlank private String subtitle;
    @Getter @Setter @NotBlank private String footer;
    @Getter @Setter @NotNull private Integer width;
    @Getter @Setter @NotNull private Integer height;

    @Getter @Setter @NotBlank private String buttonsLabel;
    @Getter @Setter @NotBlank private String buttonsSwitch;
    @Getter @Setter @NotBlank private String buttonsZoom;

    @Getter @Setter @NotBlank private String buttonsZoomLabel;
    @Getter @Setter @NotBlank private String buttonsZoomOut;

    @Getter @Setter @NotNull private Boolean logDebug;

    @Transient
    public LatticePoint getWorldDimensions() {
        return new LatticePoint(
            this.getWidth(),
            this.getHeight()
        );
    }

}
