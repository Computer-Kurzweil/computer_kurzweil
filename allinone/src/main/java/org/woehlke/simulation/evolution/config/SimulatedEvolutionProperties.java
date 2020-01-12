package org.woehlke.simulation.evolution.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SpringBootConfiguration
@Configuration
@ConfigurationProperties("org.woehlke.simulation.evolution.config")
@Valid
@Validated
@NoArgsConstructor
public class SimulatedEvolutionProperties {

    @NotNull @Getter @Setter private Integer queueMaxLength;

    @Valid
    @Getter
    @Setter
    public View view = new View();

    @Valid
    @Getter
    @Setter
    public Control control = new Control();

    @Valid
    @Getter
    @Setter
    public Lattice lattice = new Lattice();

    @Valid
    @Getter
    @Setter
    public Cell cell = new Cell();

    @Valid
    @Getter
    @Setter
    public CellPopulation cellPopulation = new CellPopulation();

    @Valid
    @Getter
    @Setter
    public Food food = new Food();

    @Valid
    @Getter
    @Setter
    public GardenOfEden gardenOfEden = new GardenOfEden();


    @Validated
    public static class View {
        @NotBlank @Getter @Setter private String title;
        @NotBlank @Getter @Setter private String subtitle;
        @NotBlank @Getter @Setter private String footer;
        @NotNull @Getter @Setter  private Integer borderPadding;
    }

    @Validated
    public static class Control {
        @NotNull @Getter @Setter private Integer time2wait;
        @NotNull @Getter @Setter private Integer exitStatus;
    }

    @Validated
    public static class Lattice {
        @NotNull @Getter @Setter private Integer width;
        @NotNull @Getter @Setter private Integer height;
    }

    @Validated
    public static class Cell {
        @NotNull @Getter @Setter private Integer fatAtBirth;
        @NotNull @Getter @Setter private Integer fatPerFood;
        @NotNull @Getter @Setter private Integer fatMax;
        @NotNull @Getter @Setter private Integer fatHungerMax;
        @NotNull @Getter @Setter private Integer fatMinimumForSex;
        @NotNull @Getter @Setter private Integer ageOfAdulthood;
        @NotNull @Getter @Setter private Integer ageOld;
        @NotNull @Getter @Setter private Integer ageMax;
    }

    @Validated
    public static class CellPopulation {
        @NotNull  @Getter @Setter private Integer initialPopulation;
        @NotBlank @Getter @Setter private String panelPopulationStatistics;
        @NotBlank @Getter @Setter private String youngCellsLabel;
        @NotBlank @Getter @Setter private String youngAndFatCellsLabel;
        @NotBlank @Getter @Setter private String fullAgeCellsLabel;
        @NotBlank @Getter @Setter private String hungryCellsLabel;
        @NotBlank @Getter @Setter private String oldCellsLabel;
        @NotBlank @Getter @Setter private String populationLabel;
        @NotBlank @Getter @Setter private String generationOldestLabel;
        @NotBlank @Getter @Setter private String generationYoungestLabel;
    }

    @Validated
    public static class Food {
        /**
         * How much food per Time Step (a day) shall be placed in this World.
         */
        @NotNull  @Getter @Setter private Integer foodPerDay;
        @NotNull  @Getter @Setter private Integer foodPerDayFieldColumns;
        @NotBlank @Getter @Setter private String foodPerDayLabel;
        @NotBlank @Getter @Setter private String buttonFoodPerDayIncrease;
        @NotBlank @Getter @Setter private String buttonFoodPerDayDecrease;
        @NotBlank @Getter @Setter private String panelFood;
    }

    @Validated
    public static class GardenOfEden {
        /**
         * A Garden of Eden is an Area where much more Food grows within the same time.
         * As a Result of Evolution you will find sucessful Bacteria Cells with a different DNA and Motion as outside the Garden of Eden.
         */
        @NotBlank @Getter @Setter private String panelGardenOfEden;
        @NotNull  @Getter @Setter private Boolean gardenOfEdenEnabled;
        @NotBlank @Getter @Setter private String gardenOfEdenEnabledString;
        @NotBlank @Getter @Setter private String gardenOfEdenEnabledToggleButton;
        @NotNull  @Getter @Setter private Integer foodPerDay;
        @NotNull  @Getter @Setter private Integer gardenOfEdenLatticeDivisor;
        @NotNull  @Getter @Setter private Integer gardenOfEdenLatticeDivisorPadding;
    }

}
