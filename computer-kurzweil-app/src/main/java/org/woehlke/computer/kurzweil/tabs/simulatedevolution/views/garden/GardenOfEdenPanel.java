package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;

import javax.swing.*;
import java.awt.*;

@Log
@Getter
@ToString(callSuper = true)
public class GardenOfEdenPanel extends JPanel implements SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    @ToString.Exclude
    private final SimulatedEvolutionContext ctx;
    private final String gardenOfEdenPanelBorderLabel;

    public GardenOfEdenPanel(SimulatedEvolutionContext ctx) {
        super(new FlowLayout());
        this.ctx=ctx;
        this.gardenOfEdenPanelBorderLabel =
            ctx.getCtx().getProperties().getSimulatedevolution().getGardenOfEden().getPanelGardenOfEden();
    }
}
