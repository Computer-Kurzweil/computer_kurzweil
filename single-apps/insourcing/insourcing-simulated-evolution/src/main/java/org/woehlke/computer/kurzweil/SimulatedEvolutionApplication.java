package org.woehlke.computer.kurzweil;

import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;

import java.io.File;
import java.net.URL;

/**
 * Class with main Method for Starting the Desktop Application.
 *
 * @see SimulatedEvolutionTab
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @author Thomas Woehlke
 */
@Log4j2
public class SimulatedEvolutionApplication {

    private SimulatedEvolutionApplication(String[] args) {

        String configFileName = "/application.yml";
        URL fileUrl = getClass().getResource(configFileName);
        File configFile = new File(fileUrl.getFile());
        ComputerKurzweilProperties properties = ComputerKurzweilProperties.propertiesFactory(configFile);
        SimulatedEvolutionTab simulatedEvolutionTab = new SimulatedEvolutionTab(properties);
    }

    /**
     * Starting the Desktop Application
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        SimulatedEvolutionApplication application = new SimulatedEvolutionApplication(args);
    }
}
