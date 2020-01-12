package org.woehlke.simulation.cca.config;

import org.woehlke.simulation.cca.control.CyclicCellularAutomatonController;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonLattice;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonFrame;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonButtonsPanel;
import org.woehlke.simulation.all.view.PanelSubtitleCca;

public class CyclicCellularAutomatonContext {

    private volatile CyclicCellularAutomatonController controller;
    private volatile CyclicCellularAutomatonCanvas canvas;
    private volatile CyclicCellularAutomatonLattice lattice;
    private volatile CyclicCellularAutomatonFrame frame;
    private volatile CyclicCellularAutomatonProperties config;
    private volatile CyclicCellularAutomatonColorScheme colorScheme;
    private volatile CyclicCellularAutomatonButtonsPanel panelButtons;
    private volatile PanelSubtitleCca subtitle;

    public CyclicCellularAutomatonContext() {
        this.config = new CyclicCellularAutomatonProperties();
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.lattice = new CyclicCellularAutomatonLattice(this);
        this.canvas = new CyclicCellularAutomatonCanvas(this);
        this.controller = new CyclicCellularAutomatonController(this);
        this.panelButtons = new CyclicCellularAutomatonButtonsPanel(this);
        this.subtitle = new PanelSubtitleCca(this);
    }

    public CyclicCellularAutomatonController getController() {
        return controller;
    }

    public void setController(CyclicCellularAutomatonController controller) {
        this.controller = controller;
    }

    public CyclicCellularAutomatonCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(CyclicCellularAutomatonCanvas canvas) {
        this.canvas = canvas;
    }

    public CyclicCellularAutomatonLattice getLattice() {
        return lattice;
    }

    public void setLattice(CyclicCellularAutomatonLattice lattice) {
        this.lattice = lattice;
    }

    public CyclicCellularAutomatonFrame getFrame() {
        return frame;
    }

    public void setFrame(CyclicCellularAutomatonFrame frame) {
        this.frame = frame;
    }

    public CyclicCellularAutomatonColorScheme getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(CyclicCellularAutomatonColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public CyclicCellularAutomatonButtonsPanel getPanelButtons() {
        return panelButtons;
    }

    public void setPanelButtons(CyclicCellularAutomatonButtonsPanel panelButtons) {
        this.panelButtons = panelButtons;
    }

    public PanelSubtitleCca getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(PanelSubtitleCca subtitle) {
        this.subtitle = subtitle;
    }

    public CyclicCellularAutomatonProperties getConfig() {
        return config;
    }

    public void setConfig(CyclicCellularAutomatonProperties config) {
        this.config = config;
    }
}
