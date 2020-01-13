package org.woehlke.simulation.cca.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.allinone.view.PanelSubtitle;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonColorScheme;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonProperties;
import org.woehlke.simulation.cca.control.CyclicCellularAutomatonController;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonFrame;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonButtonsPanel;

@Log
@ToString
@EqualsAndHashCode
@Component
public class CyclicCellularAutomatonContext {

    @Getter @Setter private volatile CyclicCellularAutomatonController controller;
    @Getter @Setter private volatile CyclicCellularAutomatonCanvas canvas;
    @Getter @Setter private volatile CyclicCellularAutomatonLattice lattice;
    @Getter @Setter private volatile CyclicCellularAutomatonFrame frame;
    @Getter @Setter private volatile CyclicCellularAutomatonProperties config;
    @Getter @Setter private volatile CyclicCellularAutomatonColorScheme colorScheme;
    @Getter @Setter private volatile CyclicCellularAutomatonButtonsPanel panelButtons;
    @Getter @Setter private volatile PanelSubtitle subtitle;
    @Getter @Setter private volatile CyclicCellularAutomatonProperties properties;

    @Autowired
    public CyclicCellularAutomatonContext(CyclicCellularAutomatonProperties properties) {
        this.properties = properties;
        this.config = new CyclicCellularAutomatonProperties();
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.lattice = new CyclicCellularAutomatonLattice(this);
        this.canvas = new CyclicCellularAutomatonCanvas(this);
        this.controller = new CyclicCellularAutomatonController(this);
        this.panelButtons = new CyclicCellularAutomatonButtonsPanel(this);
        this.subtitle = new PanelSubtitle(this);
    }

    public LatticePoint getWorldDimensions() {
        int x = properties.getWidth();
        int y = properties.getHeight();
        return new LatticePoint(x,y);
    }
}
