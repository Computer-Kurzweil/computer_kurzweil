package org.woehlke.computer.kurzweil.cyclic.cellular.automaton.config;

import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.control.CyclicCellularAutomatonController;
import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.model.CyclicCellularAutomatonLattice;
import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.view.*;

/**
 * Cyclic Cellular Automaton.
 * <p>
 * (C) 2006 - 2022 Thomas Woehlke.
 * @see <a href="https://java.woehlke.org/cyclic-cellular-automaton">Maven Project Page</a>
 *
 * @author Thomas Woehlke
 */
public class ObjectRegistry {

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
