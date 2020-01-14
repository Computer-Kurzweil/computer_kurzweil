package org.woehlke.simulation.dla.config;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@ToString
@EqualsAndHashCode
@Component
public class DiffusionLimitedAggregationProperties {

    public final static String TITLE = "diffusion limited aggregation (DLA)";

    public final static String SUBTITLE = "(c) 2019 Thomas Woehlke";

    public final static int THREAD_SLEEP_TIME = 50;

    public final static int NUMBER_OF_PARTICLES = 30000;
}
