package org.woehlke.computer.kurzweil.apps.evolution.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.view.widgets.PanelCopyright;
import org.woehlke.computer.kurzweil.view.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.apps.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;
import org.woehlke.computer.kurzweil.apps.evolution.view.widgets.SimulatedEvolutionButtonRowPanel;
import org.woehlke.computer.kurzweil.apps.evolution.view.widgets.SimulatedEvolutionCanvas;
import org.woehlke.computer.kurzweil.apps.evolution.view.widgets.SimulatedEvolutionStatisticsPanel;

import javax.swing.*;
import java.awt.image.ImageObserver;

/**
 * This Frame wraps the SimulatedEvolutionApplet which is the Container for this Simulation.
 *
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 *
 * @see javax.swing.JFrame
 * @see java.awt.image.ImageObserver
 * @see java.awt.event.WindowListener
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Deprecated
@Log
public class SimulatedEvolutionFrame extends JPanel implements ImageObserver, Startable {

    @Getter
    private SimulatedEvolutionCanvas canvas;

    @Getter
    private SimulatedEvolutionStatisticsPanel statisticsPanel;

    @Getter
    private SimulatedEvolutionButtonRowPanel panelButtons;

    @Getter
    private final SimulatedEvolutionStateService stateService;

    private PanelCopyright panelCopyright;
    private PanelSubtitle panelSubtitle;
    private BoxLayout layout;

    private SimulatedEvolutionControllerThread controllerThread;

      public SimulatedEvolutionFrame(
        SimulatedEvolutionStateService stateService
    ) {
        this.stateService = stateService;
        this.layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layout);
      }

    public void hideMe() {
        this.setVisible(false);
        this.canvas.setVisible(false);
        this.panelButtons.setVisible(false);
        this.statisticsPanel.setVisible(false);
        this.setVisible(false);
    }

    public void showMe() {
        this.setVisible(true);
        this.canvas.setVisible(true);
        this.panelButtons.setVisible(true);
        this.statisticsPanel.setVisible(true);
        this.setVisible(true);
    }

    public void repaint(){
        try {
            this.canvas.repaint();
            this.panelButtons.repaint();
            this.statisticsPanel.update();
            this.statisticsPanel.repaint();
        } catch (NullPointerException e){
            log.info(e.getMessage());
        }
       // super.repaint();
    }

    public void start() {
        this.canvas = new SimulatedEvolutionCanvas(this.stateService);
        this.statisticsPanel = new SimulatedEvolutionStatisticsPanel(this.stateService);
        this.panelButtons = new SimulatedEvolutionButtonRowPanel(this.stateService);
        this.panelCopyright = new PanelCopyright(this.stateService.getCtx());
        this.panelSubtitle = PanelSubtitle.getPanelSubtitleForSimulatedEvolution(this.stateService.getCtx());
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.panelCopyright);
        this.add(this.statisticsPanel);
        this.add(this.panelButtons);
        showMe();
        this.controllerThread = new SimulatedEvolutionControllerThread(
            this.stateService,
            this.canvas.getWorld(),
            this.statisticsPanel,
            this.panelButtons,
            this
        );
        this.controllerThread.start();
        //repaint();
    }

    public void stop(){
        hideMe();
        this.controllerThread.exit();
        this.remove(this.panelSubtitle);
        this.remove(this.canvas);
        this.remove(this.panelCopyright);
        this.remove(this.statisticsPanel);
        this.remove(this.panelButtons);
        this.canvas = null;
        this.statisticsPanel = null;
        this.panelButtons = null;
    }

    //@Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
