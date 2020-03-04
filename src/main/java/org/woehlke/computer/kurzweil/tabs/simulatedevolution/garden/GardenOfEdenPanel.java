package org.woehlke.computer.kurzweil.tabs.simulatedevolution.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log4j2
@Getter
@ToString(callSuper = true)
public class GardenOfEdenPanel extends JPanel implements SimulatedEvolution {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String gardenOfEdenPanelBorderLabel;
    private final CompoundBorder gardenOfEdenPanelBorder;

    public GardenOfEdenPanel(SimulatedEvolutionContext tabCtx) {
        super(new FlowLayout());
        this.tabCtx=tabCtx;
        this.gardenOfEdenPanelBorderLabel = tabCtx.getCtx().getProperties().getSimulatedevolution().getGardenOfEden().getPanelGardenOfEden();
        this.gardenOfEdenPanelBorder = this.tabCtx.getCtx().getBorder(gardenOfEdenPanelBorderLabel);
        this.setBorder(gardenOfEdenPanelBorder);
    }
}
