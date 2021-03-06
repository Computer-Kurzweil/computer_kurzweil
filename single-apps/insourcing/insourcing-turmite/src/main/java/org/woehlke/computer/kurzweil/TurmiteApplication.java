package org.woehlke.computer.kurzweil;

import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.turmite.TurmiteTab;

import java.io.File;
import java.net.URL;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 */
public class TurmiteApplication {

    private TurmiteApplication() {
        String configFileName = "/application.yml";
        URL fileUrl = getClass().getResource(configFileName);
        File configFile = new File(fileUrl.getFile());
        ComputerKurzweilProperties properties = ComputerKurzweilProperties.propertiesFactory(configFile);
        TurmiteTab frame = new TurmiteTab(properties);
    }

    /**
     * Starting the Application.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        TurmiteApplication application = new TurmiteApplication();
    }
}
