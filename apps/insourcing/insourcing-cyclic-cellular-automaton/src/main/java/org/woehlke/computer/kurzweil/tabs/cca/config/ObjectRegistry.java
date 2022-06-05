package org.woehlke.computer.kurzweil.tabs.cca.config;

import org.woehlke.computer.kurzweil.tabs.cca.control.CyclicCellularAutomatonController;
import org.woehlke.computer.kurzweil.tabs.cca.model.CyclicCellularAutomatonLattice;
import org.woehlke.computer.kurzweil.tabs.cca.view.CyclicCellularAutomatonFrame;
import org.woehlke.computer.kurzweil.tabs.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.tabs.cca.view.PanelButtons;
import org.woehlke.computer.kurzweil.tabs.cca.view.PanelSubtitle;

import java.io.Serializable;

public class ObjectRegistry implements Serializable {

    private static final long serialVersionUID = 4357793241219932594L;

    private volatile CyclicCellularAutomatonController controller;
    private volatile CyclicCellularAutomatonCanvas canvas;
    private volatile CyclicCellularAutomatonLattice lattice;
    private volatile CyclicCellularAutomatonFrame frame;
    private volatile Config config;
    private volatile ColorScheme colorScheme;
    private volatile PanelButtons panelButtons;
    private volatile PanelSubtitle subtitle;

    public ObjectRegistry() {
        this.config = new Config();
        this.colorScheme = new ColorScheme();
        this.lattice = new CyclicCellularAutomatonLattice(this);
        this.canvas = new CyclicCellularAutomatonCanvas(this);
        this.controller = new CyclicCellularAutomatonController(this);
        this.panelButtons = new PanelButtons(this);
        this.subtitle = new PanelSubtitle(this);
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

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public PanelButtons getPanelButtons() {
        return panelButtons;
    }

    public void setPanelButtons(PanelButtons panelButtons) {
        this.panelButtons = panelButtons;
    }

    public PanelSubtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(PanelSubtitle subtitle) {
        this.subtitle = subtitle;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
