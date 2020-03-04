package org.woehlke.computer.kurzweil;

import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilFrame;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.Startable;


/**
 * Class with main Method for Starting the Desktop Application.
 *
 * @author Thomas Woehlke
 * <p>
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @see ComputerKurzweilFrame
 * @see ComputerKurzweilProperties
 * @see ComputerKurzweilContext
 * <p>
 * Simulated Evolution. Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * Green food appears in a world with red moving cells.
 * These cells eat the food if it is on their position.
 * Movement of the cells depends on random and their DNA.
 * A fit cell moves around and eats enough to reproduce.
 * Reproduction is done by splitting the cell and randomly changing the DNA of the two new Cells.
 * If a cell doesn't eat enough, it will first stand still and after a while it dies.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 */
@Log4j2
public class ComputerKurzweilApplication implements Startable {

    private final ComputerKurzweilFrame frame;

    public ComputerKurzweilApplication(String configFileName, String jarFilePath) {
        ComputerKurzweilProperties properties = ComputerKurzweilProperties.propertiesFactory(configFileName, jarFilePath);
        this.frame = new ComputerKurzweilFrame(properties);
        //start();
    }

    public void start(){
        try {
            this.frame.start();
            log.info("Started the Desktop Application");
        } catch (IllegalThreadStateException e){
            log.info(e.getLocalizedMessage());
        }
    }

    @Override
    public void stop() {
        try {
            this.frame.stop();
            log.info("Stopped the Desktop Application");
        } catch (IllegalThreadStateException e){
            log.info(e.getLocalizedMessage());
        }
    }

    /**
     * Starting the Desktop Application.
     *
     * @param args CLI Parameter.
     */
    public static void main(String[] args) {
        String configFileName = "application.yml";
        String jarFilePath = "build/libs/computer_kurzweil-all.jar";
        ComputerKurzweilApplication application = new ComputerKurzweilApplication(configFileName,jarFilePath);
        application.start();
    }

}
