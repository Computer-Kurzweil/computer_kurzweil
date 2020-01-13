package org.woehlke.simulation.allinone.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;

@Component
public class ComputerKurzweilApplicationContext {

    @Getter
    private final ComputerKurzweilProperties properties;


    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties properties
    ) {
        this.properties = properties;
    }

    public void exit() {
        System.exit(0);
    }
}
