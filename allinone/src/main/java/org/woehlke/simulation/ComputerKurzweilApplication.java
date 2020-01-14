package org.woehlke.simulation;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;
import org.woehlke.simulation.allinone.view.ComputerKurzweilApplicationFrame;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonProperties;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;

import java.awt.*;
import java.util.logging.Logger;

/**
 * Class with main Method for Starting the Desktop Application.
 *
 * @author Thomas Woehlke
 * <p>
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @see SimulatedEvolutionFrame
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
@SpringBootApplication
@Configuration
@Import({
    ComputerKurzweilProperties.class,
    SimulatedEvolutionProperties.class,
    CyclicCellularAutomatonProperties.class
})
public class ComputerKurzweilApplication {

    private final ComputerKurzweilApplicationFrame frame;

    @Autowired
    public ComputerKurzweilApplication(
        ComputerKurzweilApplicationFrame frame
    ) {
        this.frame=frame;
    }

    public void start(){
        try {
            this.frame.start();
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
        log.info("Started the Desktop Application");
        ConfigurableApplicationContext springCtx = new SpringApplicationBuilder(ComputerKurzweilApplication.class)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(args);
        EventQueue.invokeLater(() -> {
            ComputerKurzweilApplication application = springCtx.getBean(ComputerKurzweilApplication.class);
        });
    }

}
