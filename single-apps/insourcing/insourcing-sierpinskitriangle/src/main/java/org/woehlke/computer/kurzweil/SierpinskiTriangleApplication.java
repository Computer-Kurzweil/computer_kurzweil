package org.woehlke.computer.kurzweil;

import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.sierpinskitriangle.SierpinskiTriangleTab;

import java.io.File;
import java.net.URL;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 */
public class SierpinskiTriangleApplication {

    private SierpinskiTriangleApplication() {
        String configFileName = "/application.yml";
        URL fileUrl = getClass().getResource(configFileName);
        File configFile = new File(fileUrl.getFile());
        ComputerKurzweilProperties properties = ComputerKurzweilProperties.propertiesFactory(configFile);
        SierpinskiTriangleTab frame = new SierpinskiTriangleTab(properties);
    }

    /**
     * Starting the Application.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        SierpinskiTriangleApplication application = new SierpinskiTriangleApplication();
    }
}
