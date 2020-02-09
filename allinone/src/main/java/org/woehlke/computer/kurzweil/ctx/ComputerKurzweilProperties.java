package org.woehlke.computer.kurzweil.ctx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.*;
import lombok.extern.java.Log;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Log
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Valid
////@Validated
public class ComputerKurzweilProperties {

    @Valid @Getter @Setter public Allinone allinone = new Allinone();
    @Valid @Getter @Setter public Mandelbrot mandelbrot = new Mandelbrot();
    @Valid @Getter @Setter public Evolution evolution = new Evolution();
    @Valid @Getter @Setter public Cca cca = new Cca();
    @Valid @Getter @Setter public Dla dla = new Dla();
    @Valid @Getter @Setter public Kochsnowflake kochsnowflake = new Kochsnowflake();
    @Valid @Getter @Setter public Samegame samegame = new Samegame();
    @Valid @Getter @Setter public Sierpinskitriangle sierpinskitriangle = new Sierpinskitriangle();
    @Valid @Getter @Setter public Tetris tetris = new Tetris();
    @Valid @Getter @Setter public Turmite turmite = new Turmite();
    @Valid @Getter @Setter public Wator wator = new Wator();

    ////@Validated
    public static class Allinone {

        @Valid @Getter @Setter public Lattice lattice = new Lattice();
        @Valid @Getter @Setter public View view = new View();

        ////@Validated
        public static class Lattice {
            @NotNull  @Getter @Setter private Integer width;
            @NotNull  @Getter @Setter private Integer height;
        }

        ////@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
            @NotBlank @Getter @Setter private String copyright;
            @NotNull  @Getter @Setter private Integer borderPadding;
            @NotNull  @Getter @Setter private Integer titleHeight;
            @NotBlank @Getter @Setter private String startStopp;
            @NotBlank @Getter @Setter private String start;
            @NotBlank @Getter @Setter private String stop;
            @NotBlank @Getter @Setter private String info;
        }
    }

    ////@Validated
    public static class Mandelbrot {

        @Valid
        @Getter
        @Setter
        public View view = new View();

        ////@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
            @NotBlank @Getter @Setter private String buttonsZoom;
            @NotBlank @Getter @Setter private String buttonsZoomOut;
            @NotBlank @Getter @Setter private String buttonsSwitch;
            @NotBlank @Getter @Setter private String buttonsZoomLabel;
            @NotBlank @Getter @Setter private String buttonsLabel;
        }
    }

    ////@Validated
    public static class Evolution {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();
        @Valid @Getter @Setter public CellConf cellConf = new CellConf();
        @Valid @Getter @Setter public Population population = new Population();
        @Valid @Getter @Setter public Food food = new Food();
        @Valid @Getter @Setter public GardenOfEden gardenOfEden = new GardenOfEden();

        ////@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        ////@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer time2wait;
            @NotNull  @Getter @Setter private Integer exitStatus;
            @NotNull  @Getter @Setter private Integer queueMaxLength;
        }

        ////@Validated
        public static class CellConf {
            @NotNull  @Getter @Setter private Integer fatMax;
            @NotNull  @Getter @Setter private Integer fatHungerMax;
            @NotNull  @Getter @Setter private Integer fatMinimumForSex;
            @NotNull  @Getter @Setter private Integer fatAtBirth;
            @NotNull  @Getter @Setter private Integer fatPerFood;
            @NotNull  @Getter @Setter private Integer ageOfAdulthood;
            @NotNull  @Getter @Setter private Integer ageOld;
            @NotNull  @Getter @Setter private Integer ageMax;
        }

        ////@Validated
        public static class Population {
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

        ////@Validated
        public static class Food {
            @NotNull  @Getter @Setter private Integer foodPerDay;
            @NotNull  @Getter @Setter private Integer foodPerDayFieldColumns;
            @NotBlank @Getter @Setter private String foodPerDayLabel;
            @NotBlank @Getter @Setter private String buttonFoodPerDayIncrease;
            @NotBlank @Getter @Setter private String buttonFoodPerDayDecrease;
            @NotBlank @Getter @Setter private String panelFood;
        }

        ////@Validated
        public static class GardenOfEden {
            @NotBlank @Getter @Setter private String panelGardenOfEden;
            @NotNull  @Getter @Setter private Boolean gardenOfEdenEnabled;
            @NotBlank @Getter @Setter private String gardenOfEdenEnabledString;
            @NotBlank @Getter @Setter private String gardenOfEdenEnabledToggleButton;
            @NotNull  @Getter @Setter private Integer foodPerDay;
            @NotNull  @Getter @Setter private Integer gardenOfEdenLatticeDivisor;
            @NotNull  @Getter @Setter private Integer gardenOfEdenLatticeDivisorPadding;
        }
    }

    ////@Validated
    public static class Cca {

        @Valid @Getter @Setter public View view = new View();

        //@Validated
        public static class View {

            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;

            @Valid @Getter @Setter public Neighborhood neighborhood = new Neighborhood();

            //@Validated
            public static class Neighborhood {
                @NotBlank @Getter @Setter private String title;
                @NotBlank @Getter @Setter private String typeVonNeumann;
                @NotBlank @Getter @Setter private String typeMoore;
                @NotBlank @Getter @Setter private String typeWoehlke;
            }
        }
    }

    //@Validated
    public static class Dla {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();

        //@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        //@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer threadSleepTime;
            @NotNull  @Getter @Setter private Integer numberOfParticles;
        }
    }

    public static class Kochsnowflake {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();

        //@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        //@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer threadSleepTime;
            @NotNull  @Getter @Setter private Integer numberOfParticles;
        }
    }

    public static class Samegame {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();

        //@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        //@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer threadSleepTime;
            @NotNull  @Getter @Setter private Integer numberOfParticles;
        }
    }

    public static class Sierpinskitriangle {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();

        //@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        //@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer threadSleepTime;
            @NotNull  @Getter @Setter private Integer numberOfParticles;
        }
    }

    public static class Tetris {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();

        //@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        //@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer threadSleepTime;
            @NotNull  @Getter @Setter private Integer numberOfParticles;
        }
    }

    public static class Turmite {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();

        //@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        //@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer threadSleepTime;
            @NotNull  @Getter @Setter private Integer numberOfParticles;
        }
    }

    public static class Wator {

        @Valid @Getter @Setter public View view = new View();
        @Valid @Getter @Setter public Control control = new Control();

        //@Validated
        public static class View {
            @NotBlank @Getter @Setter private String title;
            @NotBlank @Getter @Setter private String subtitle;
        }

        //@Validated
        public static class Control {
            @NotNull  @Getter @Setter private Integer threadSleepTime;
            @NotNull  @Getter @Setter private Integer numberOfParticles;
        }
    }

    public static ComputerKurzweilProperties propertiesFactory(String conf, String jar){
        ComputerKurzweilProperties properties;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            JarFile jarFile = new JarFile(jar);
            JarEntry entry = jarFile.getJarEntry(conf);
            InputStream input = jarFile.getInputStream(entry);
            properties = mapper.readValue(input, ComputerKurzweilProperties.class);
            System.out.println(ReflectionToStringBuilder.toString(properties, ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            e.printStackTrace();
            properties = new ComputerKurzweilProperties();
        }
        return properties;
    }
}
