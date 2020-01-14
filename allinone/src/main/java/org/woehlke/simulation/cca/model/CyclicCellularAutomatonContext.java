package org.woehlke.simulation.cca.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonColorScheme;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonProperties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
@ToString
@EqualsAndHashCode
@Component
public class CyclicCellularAutomatonContext {

    @Getter @Setter private volatile CyclicCellularAutomatonProperties properties;
    @Getter @Setter private volatile CyclicCellularAutomatonColorScheme colorScheme;

    @Autowired
    public CyclicCellularAutomatonContext(CyclicCellularAutomatonProperties properties) {
        this.properties = properties;
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
    }

    public LatticePoint getWorldDimensions() {
        int x = properties.getWidth();
        int y = properties.getHeight();
        return new LatticePoint(x,y);
    }
}
