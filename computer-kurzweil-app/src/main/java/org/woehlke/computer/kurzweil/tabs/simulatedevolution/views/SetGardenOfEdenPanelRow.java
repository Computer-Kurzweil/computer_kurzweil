package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.SubTab;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class SetGardenOfEdenPanelRow extends SubTabImpl implements SimulatedEvolution, Updateable, SubTab {

    private static final long serialVersionUID = 7526471155622776147L;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;

    private final JRadioButton gardenOfEdenEnabled;
    private final JRadioButton gardenOfEdenDisabled;
    private final ButtonGroup bgroup = new ButtonGroup();
    private final JButton buttonRestart = new JButton("Set");

    public SetGardenOfEdenPanelRow(SimulatedEvolutionContext tabCtx) {
        super("Garden of Eden",tabCtx.getCtx().getProperties());
        this.tabCtx = tabCtx;
        this.gardenOfEdenEnabled = new JRadioButton("on");
        this.gardenOfEdenDisabled = new JRadioButton("off");
        bgroup.add(this.gardenOfEdenEnabled);
        bgroup.add(this.gardenOfEdenDisabled);
        this.add(this.gardenOfEdenEnabled);
        this.add(this.gardenOfEdenDisabled);
        this.add(this.buttonRestart);
    }

    public void addActionListener(SimulatedEvolutionTab tab) {
        this.buttonRestart.addActionListener(tab);
    }

    public void update() {
        boolean enabled = tabCtx.getTabModel().getWorldParameter().isGardenOfEdenEnabled();
        this.gardenOfEdenEnabled.setSelected(enabled);
        this.gardenOfEdenDisabled.setSelected(!enabled);
    }
}
