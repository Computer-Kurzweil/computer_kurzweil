package org.woehlke.simulation.allinone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.dla.model.DiffusionLimitedAggregatioContext;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;

@Log
@Component
@ToString
@EqualsAndHashCode
public class ComputerKurzweilApplicationContext {

    @Getter private final ComputerKurzweilProperties properties;

    @Getter private final MandelbrotContext mandelbrotContext;
    @Getter private final SimulatedEvolutionContext simulatedEvolutionContext;
    @Getter private final DiffusionLimitedAggregatioContext diffusionLimitedAggregationPContext;
    @Getter private final CyclicCellularAutomatonContext cyclicCellularAutomatonContext;

    @Autowired
    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties computerKurzweilProperties,
        MandelbrotContext mandelbrotContext,
        SimulatedEvolutionContext simulatedEvolutionContext,
        DiffusionLimitedAggregatioContext diffusionLimitedAggregationPContext,
        CyclicCellularAutomatonContext cyclicCellularAutomatonContext
    ) {
        this.properties = computerKurzweilProperties;
        this.mandelbrotContext = mandelbrotContext;
        this.simulatedEvolutionContext = simulatedEvolutionContext;
        this.diffusionLimitedAggregationPContext = diffusionLimitedAggregationPContext;
        this.cyclicCellularAutomatonContext = cyclicCellularAutomatonContext;
    }

    public void exit() {
        System.exit(0);
    }
}
