package org.woehlke.simulation.mandelbrot.model;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;

@Log
@Component
public class MandelbrotContext  {

    @Getter private final MandelbrotProperties properties;

    @Autowired
    public MandelbrotContext(
        MandelbrotProperties properties
    ) {
        this.properties = properties;

    }

}
