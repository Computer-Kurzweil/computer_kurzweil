package org.woehlke.simulation.allinone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;


@Log
@ToString
@EqualsAndHashCode
@Component
public class ComputerKurzweilApplicationContext {

    @Getter
    private final ComputerKurzweilProperties properties;

    @Getter @Setter
    private SimulatedEvolutionFrame simulatedEvolutionFrame;

    @Getter @Setter
    private MandelbrotFrame mandelbrotFrame;

    @Autowired
    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties properties
    ) {
        this.properties = properties;
    }

    public void exit() {
        System.exit(0);
    }
}
