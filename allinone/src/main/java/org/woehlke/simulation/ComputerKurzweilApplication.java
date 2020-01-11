package org.woehlke.simulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.EnableIntegration;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionController;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionWorldStatisticsContainer;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorld;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorldMapFood;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionPanelButtons;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionPanelStatistics;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionWorldCanvas;

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
    SimulatedEvolutionProperties.class
})
public class ComputerKurzweilApplication {

    private final SimulatedEvolutionContext ctx;
    private final SimulatedEvolutionProperties properties;
    private final SimulatedEvolutionController controller;
    private final SimulatedEvolutionWorld simulatedEvolutionWorld;
    private final SimulatedEvolutionWorldMapFood worldMapFood;
    private final SimulatedEvolutionWorldStatisticsContainer simulatedEvolutionWorldStatisticsContainer;

    private final SimulatedEvolutionFrame simulatedEvolutionFrame;
    private final SimulatedEvolutionPanelStatistics statisticsPanel;
    private final SimulatedEvolutionWorldCanvas simulatedEvolutionWorldCanvas;
    private final SimulatedEvolutionPanelButtons simulatedEvolutionPanelButtons;

    @Autowired
      public ComputerKurzweilApplication(
        SimulatedEvolutionContext ctx,
        SimulatedEvolutionProperties properties,
        SimulatedEvolutionFrame simulatedEvolutionFrame,
        SimulatedEvolutionWorld simulatedEvolutionWorld,
        SimulatedEvolutionWorldMapFood worldMapFood,
        SimulatedEvolutionPanelStatistics statisticsPanel,
        SimulatedEvolutionWorldCanvas simulatedEvolutionWorldCanvas,
        SimulatedEvolutionController simulatedEvolutionController,
        SimulatedEvolutionPanelButtons simulatedEvolutionPanelButtons,
        SimulatedEvolutionWorldStatisticsContainer simulatedEvolutionWorldStatisticsContainer
    ) {
        this.ctx = ctx;
        this.properties = properties;

        this.simulatedEvolutionWorld = simulatedEvolutionWorld;

        this.simulatedEvolutionWorldStatisticsContainer = simulatedEvolutionWorldStatisticsContainer;

        this.worldMapFood = worldMapFood;
        this.simulatedEvolutionFrame = simulatedEvolutionFrame;
        this.statisticsPanel = statisticsPanel;
        this.simulatedEvolutionWorldCanvas = simulatedEvolutionWorldCanvas;
        this.simulatedEvolutionPanelButtons = simulatedEvolutionPanelButtons;
        this.controller = simulatedEvolutionController;

        this.controller.setStatisticsPanel(this.statisticsPanel);
        this.controller.setFrame(this.simulatedEvolutionFrame);
        this.controller.setWorld(this.simulatedEvolutionWorld);
        this.controller.setCanvas(this.simulatedEvolutionWorldCanvas);
        this.controller.setStatisticsPanel(this.statisticsPanel);
        this.controller.setWorldMapFood(this.worldMapFood);
    }

    public void start(){
        try {
            this.controller.start();
        } catch (IllegalThreadStateException e){
            System.out.println(e.getLocalizedMessage());
        }
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
