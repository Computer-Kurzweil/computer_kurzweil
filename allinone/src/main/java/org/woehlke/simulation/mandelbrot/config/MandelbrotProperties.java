package org.woehlke.simulation.mandelbrot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.woehlke.simulation.all.model.LatticePointMandelbrot;

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
@Configuration
@ConfigurationProperties("org.woehlke.simulation.mandelbrot.config")
@Valid
@Validated
public class MandelbrotProperties {

    @NotBlank private String title;
    @NotBlank private String subtitle;
    @NotBlank private String copyright;
    @NotNull private Integer width;
    @NotNull private Integer height;

    @NotBlank private String buttonsLabel;
    @NotBlank private String buttonsSwitch;
    @NotBlank private String buttonsZoom;

    @NotBlank private String buttonsZoomLabel;
    @NotBlank private String buttonsZoomOut;

    @NotNull private Boolean logDebug;

    @Transient
    public LatticePointMandelbrot getWorldDimensions() {
        return new LatticePointMandelbrot(
            this.getWidth(),
            this.getHeight()
        );
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getButtonsLabel() {
        return buttonsLabel;
    }

    public void setButtonsLabel(String buttonsLabel) {
        this.buttonsLabel = buttonsLabel;
    }

    public String getButtonsSwitch() {
        return buttonsSwitch;
    }

    public void setButtonsSwitch(String buttonsSwitch) {
        this.buttonsSwitch = buttonsSwitch;
    }

    public String getButtonsZoom() {
        return buttonsZoom;
    }

    public void setButtonsZoom(String buttonsZoom) {
        this.buttonsZoom = buttonsZoom;
    }

    public String getButtonsZoomLabel() {
        return buttonsZoomLabel;
    }

    public void setButtonsZoomLabel(String buttonsZoomLabel) {
        this.buttonsZoomLabel = buttonsZoomLabel;
    }

    public String getButtonsZoomOut() {
        return buttonsZoomOut;
    }

    public void setButtonsZoomOut(String buttonsZoomOut) {
        this.buttonsZoomOut = buttonsZoomOut;
    }

    public Boolean getLogDebug() {
        return logDebug;
    }

    public void setLogDebug(Boolean logDebug) {
        this.logDebug = logDebug;
    }
}
