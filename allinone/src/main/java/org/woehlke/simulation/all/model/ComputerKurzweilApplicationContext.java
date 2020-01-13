package org.woehlke.simulation.all.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.all.config.ComputerKurzweilProperties;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;

@Component
public class ComputerKurzweilApplicationContext {

    @Getter
    private final ComputerKurzweilProperties properties;

    @Getter
    private final SimulatedEvolutionFrame simulatedEvolutionFrame;

    @Getter
    private final MandelbrotFrame mandelbrotFrame;

    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties properties,
        SimulatedEvolutionFrame simulatedEvolutionFrame,
        MandelbrotFrame mandelbrotFrame) {
        this.properties = properties;
        this.simulatedEvolutionFrame = simulatedEvolutionFrame;
        this.mandelbrotFrame = mandelbrotFrame;
    }

    public void start() {
        this.simulatedEvolutionFrame.start();
    }

    public void exit() {
        System.exit(0);
    }
}
