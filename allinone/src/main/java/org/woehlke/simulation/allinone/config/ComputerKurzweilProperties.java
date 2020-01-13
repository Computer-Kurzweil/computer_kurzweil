package org.woehlke.simulation.allinone.config;

import lombok.*;
import lombok.extern.java.Log;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Log
@ToString
@EqualsAndHashCode
@SpringBootConfiguration
@Configuration
@Valid
@Validated
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("org.woehlke.simulation.allinone.config")
public class ComputerKurzweilProperties {

    @Valid
    @Getter
    @Setter
    public SimulatedEvolutionProperties.View view = new SimulatedEvolutionProperties.View();

    @Validated
    public static class View {
        @NotBlank @Getter @Setter private String title;
        @NotBlank @Getter @Setter private String subtitle;
        @NotBlank @Getter @Setter private String footer;
        @NotNull  @Getter @Setter private Integer borderPadding;
    }
}
