package org.woehlke.simulation.cca.model;

import lombok.Getter;
import lombok.Setter;
import org.woehlke.simulation.all.view.PanelSubtitle;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonColorScheme;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonProperties;
import org.woehlke.simulation.cca.control.CyclicCellularAutomatonController;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonFrame;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonButtonsPanel;

public class CyclicCellularAutomatonContext {

    @Getter @Setter private volatile CyclicCellularAutomatonController controller;
    @Getter @Setter private volatile CyclicCellularAutomatonCanvas canvas;
    @Getter @Setter private volatile CyclicCellularAutomatonLattice lattice;
    @Getter @Setter private volatile CyclicCellularAutomatonFrame frame;
    @Getter @Setter private volatile CyclicCellularAutomatonProperties config;
    @Getter @Setter private volatile CyclicCellularAutomatonColorScheme colorScheme;
    @Getter @Setter private volatile CyclicCellularAutomatonButtonsPanel panelButtons;
    @Getter @Setter private volatile PanelSubtitle subtitle;

    public CyclicCellularAutomatonContext() {
        this.config = new CyclicCellularAutomatonProperties();
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.lattice = new CyclicCellularAutomatonLattice(this);
        this.canvas = new CyclicCellularAutomatonCanvas(this);
        this.controller = new CyclicCellularAutomatonController(this);
        this.panelButtons = new CyclicCellularAutomatonButtonsPanel(this);
        this.subtitle = new PanelSubtitle(this);
    }

}
