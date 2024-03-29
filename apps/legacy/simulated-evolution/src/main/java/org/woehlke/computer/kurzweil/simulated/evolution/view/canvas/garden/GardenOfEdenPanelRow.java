package org.woehlke.computer.kurzweil.simulated.evolution.view.canvas.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.simulated.evolution.view.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.simulated.evolution.view.tabs.TabPanel;
import org.woehlke.computer.kurzweil.simulated.evolution.control.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.simulated.evolution.model.SimulatedEvolutionModel;

/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/simulated-evolution">Github Repository</a>
 * @see <a href="https://java.woehlke.org/simulated-evolution/">Maven Project Repository</a>
 */
@Log4j2
@Getter
@ToString(callSuper = true)
public class GardenOfEdenPanelRow extends SubTabImpl {

    private static final long serialVersionUID = 242L;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    @ToString.Exclude
    private final SimulatedEvolutionModel tabModel;
    private final GardenOfEdenCheckBox gardenOfEdenEnabled;
    private final GardenOfEdenToggleButton buttonToggleGardenOfEden;
    //private final GardenOfEdenPanel gardenOfEdenPanel;

    public GardenOfEdenPanelRow(SimulatedEvolutionContext tabCtx) {
        super("Garden of Eden",tabCtx.getCtx().getProperties());
        this.tabCtx = tabCtx;
        this.tabModel = this.tabCtx.getTabModel();
        this.gardenOfEdenEnabled = new GardenOfEdenCheckBox(this.tabCtx);
        this.buttonToggleGardenOfEden = new GardenOfEdenToggleButton(this.tabCtx);
        /*
        this.gardenOfEdenPanel = new GardenOfEdenPanel(this.tabCtx);
        this.gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        this.gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
        this.add( this.gardenOfEdenPanel);
        */
        this.add(this.gardenOfEdenEnabled);
        this.add(this.buttonToggleGardenOfEden);
    }

    public void setSelected(boolean selected) {
        this.tabModel.getSimulatedEvolutionParameter().setGardenOfEdenEnabled(selected);
        update();
    }

    public void toggleGardenOfEden() {
        this.tabModel.getSimulatedEvolutionParameter().toggleGardenOfEden();
        update();
    }

    public void addActionListener(TabPanel myTabPanel) {
        this.buttonToggleGardenOfEden.addActionListener(myTabPanel);
    }

    public void update() {
        boolean enabled = tabModel.getSimulatedEvolutionParameter().isGardenOfEdenEnabled();
        this.buttonToggleGardenOfEden.setSelected(enabled);
        this.gardenOfEdenEnabled.setSelected(enabled);
    }
}
