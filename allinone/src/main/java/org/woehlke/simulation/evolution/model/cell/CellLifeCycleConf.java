package org.woehlke.simulation.evolution.model.cell;

import lombok.Getter;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

public class CellLifeCycleConf {

    @Getter private final int confFatAtBirth;
    @Getter private final int confFullAge;
    @Getter private final int confFatMinimumForSex;
    @Getter private final int confMaxHunger;
    @Getter private final int confFatPerFood;
    @Getter private final int confMaxFat;
    @Getter private final int confAgeOfAdulthood;
    @Getter private final int confMaxAge;

    public CellLifeCycleConf(SimulatedEvolutionContext ctx) {
        this.confFatAtBirth = ctx.getProperties().getCell().getFatAtBirth();
        this.confFullAge = ctx.getProperties().getCell().getAgeOfAdulthood();
        this.confFatMinimumForSex = ctx.getProperties().getCell().getFatMinimumForSex();
        this.confMaxHunger = ctx.getProperties().getCell().getFatHungerMax();
        this.confFatPerFood = ctx.getProperties().getCell().getFatPerFood();
        this.confMaxFat = ctx.getProperties().getCell().getFatMax();
        this.confAgeOfAdulthood = ctx.getProperties().getCell().getAgeOfAdulthood();
        this.confMaxAge = ctx.getProperties().getCell().getAgeMax();
    }

}
