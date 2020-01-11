package org.woehlke.simulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.EnableIntegration;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionController;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.mandelbrot.config.Config;

import java.awt.*;

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
@SpringBootApplication
@Configuration
@EnableIntegration
@Import({
    Config.class
})
public class ComputerKurzweilApplication {

    private final SimulatedEvolutionFrame simulatedEvolutionFrame;

    private final SimulatedEvolutionController simulatedEvolutionController;


    @Autowired
      public ComputerKurzweilApplication(SimulatedEvolutionFrame simulatedEvolutionFrame, SimulatedEvolutionController simulatedEvolutionController) {
        this.simulatedEvolutionFrame = simulatedEvolutionFrame;
            this.simulatedEvolutionController = simulatedEvolutionController;
            this.simulatedEvolutionController.setFrame(this.simulatedEvolutionFrame);
        try {
            this.simulatedEvolutionController.start();
        } catch (IllegalThreadStateException e){
            System.out.println(e.getLocalizedMessage());
        }
      }

    public void start(){
        this.simulatedEvolutionFrame.showMe();
    }

    public void exit() {
        this.simulatedEvolutionFrame.dispose();
    }

    /**
     * Starting the Desktop Application.
     *
     * @param args CLI Parameter.
     */
    public static void main(String[] args) {
        System.out.println("Started the Desktop Application");
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ComputerKurzweilApplication.class)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(args);
        EventQueue.invokeLater(() -> {
            ComputerKurzweilApplication ex = ctx.getBean(ComputerKurzweilApplication.class);
            ex.start();
        });
    }

}
