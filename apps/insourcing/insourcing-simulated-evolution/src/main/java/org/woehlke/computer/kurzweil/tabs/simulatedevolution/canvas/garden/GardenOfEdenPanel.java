package org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;

import javax.swing.*;
import java.awt.*;

@Log
@Getter
@ToString(callSuper = true)
public class GardenOfEdenPanel extends JPanel implements SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String gardenOfEdenPanelBorderLabel;
    //private final CompoundBorder gardenOfEdenPanelBorder;

    public GardenOfEdenPanel(SimulatedEvolutionContext tabCtx) {
        super(new FlowLayout());
        this.tabCtx=tabCtx;
        this.gardenOfEdenPanelBorderLabel = tabCtx.getCtx().getProperties().getSimulatedevolution().getGardenOfEden().getPanelGardenOfEden();
        //this.gardenOfEdenPanelBorder = this.tabCtx.getCtx().getBorder(gardenOfEdenPanelBorderLabel);
        //this.setBorder(gardenOfEdenPanelBorder);
    }
}
