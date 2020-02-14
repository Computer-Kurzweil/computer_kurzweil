package org.woehlke.computer.kurzweil;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationFrame;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;


/**
 * Class with main Method for Starting the Desktop Application.
 *
 * @author Thomas Woehlke
 * <p>
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @see ComputerKurzweilApplicationFrame
 * @see ComputerKurzweilProperties
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
@Log
public class ComputerKurzweilApplication {

    private final ComputerKurzweilApplicationFrame frame;

    public ComputerKurzweilApplication() {
        String conf = "application.yml";
        String jar = "allinone/build/libs/allinone-all.jar";
        ComputerKurzweilProperties properties = ComputerKurzweilProperties.propertiesFactory(conf, jar);
        this.frame = new ComputerKurzweilApplicationFrame(properties);
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

    /**
     * Starting the Desktop Application.
     *
     * @param args CLI Parameter.
     */
    public static void main(String[] args) {
        ComputerKurzweilApplication application = new ComputerKurzweilApplication();
        application.start();

        /*
        ConfigurableApplicationContext springCtx = new SpringApplicationBuilder(
            ComputerKurzweilApplication.class
        ).web(WebApplicationType.NONE).headless(false).run(args);
        EventQueue.invokeLater(() -> {
            ComputerKurzweilApplication application = springCtx.getBean(ComputerKurzweilApplication.class);
            application.start();
        });
        */
    }

}
