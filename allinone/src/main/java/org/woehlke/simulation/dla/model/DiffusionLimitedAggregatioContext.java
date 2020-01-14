package org.woehlke.simulation.dla.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.dla.config.DiffusionLimitedAggregationProperties;

@Component
public class DiffusionLimitedAggregatioContext {

    @Getter
    private final DiffusionLimitedAggregationProperties properties;

    @Autowired
    public DiffusionLimitedAggregatioContext(DiffusionLimitedAggregationProperties properties) {
        this.properties = properties;
    }

    public LatticePoint getWorldDimensions(){
        return new LatticePoint(properties.getWidth(),properties.getHeight());
    }
}
