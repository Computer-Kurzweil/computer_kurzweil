package org.woehlke.simulation.evolution.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.view.PanelCopyright;
import org.woehlke.simulation.allinone.view.PanelSubtitle;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.view.parts.SimulatedEvolutionButtonRowPanel;
import org.woehlke.simulation.evolution.view.parts.SimulatedEvolutionCanvas;
import org.woehlke.simulation.evolution.view.parts.SimulatedEvolutionStatisticsPanel;

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
@Log
@Component
public class SimulatedEvolutionFrame extends JPanel implements ImageObserver {

    @Getter
    private final SimulatedEvolutionContext ctx;

    @Getter
    private final SimulatedEvolutionCanvas canvas;

    @Getter
    private final SimulatedEvolutionStatisticsPanel statisticsPanel;

    @Getter
    private final SimulatedEvolutionButtonRowPanel panelButtons;

    private SimulatedEvolutionControllerThread controllerThread;

    @Autowired
      public SimulatedEvolutionFrame(
          SimulatedEvolutionContext ctx
      ) {
          this.ctx = ctx;
          this.canvas = new SimulatedEvolutionCanvas(this.ctx);
          this.statisticsPanel = new SimulatedEvolutionStatisticsPanel(this.ctx);
          this.panelButtons = new SimulatedEvolutionButtonRowPanel(this.ctx);
          PanelCopyright panelCopyright = new PanelCopyright(this.ctx);
          PanelSubtitle panelSubtitle = new PanelSubtitle(this.ctx);
          BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
          this.setLayout(layout);
          this.add(panelSubtitle);
          this.add(this.canvas);
          this.add(panelCopyright);
          this.add(this.statisticsPanel);
          this.add(this.panelButtons);
      }

    /**
     * TODO write doc.
     */
    public void showMe() {
        this.setVisible(true);
        this.canvas.setVisible(true);
        this.panelButtons.setVisible(true);
        this.statisticsPanel.setVisible(true);
        this.setVisible(true);
        repaint();
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
        super.repaint();
    }

    public void start() {
        showMe();
        this.controllerThread = new SimulatedEvolutionControllerThread( this.ctx , this.canvas.getWorld(),this.statisticsPanel, panelButtons,   this);
        this.controllerThread.start();
    }

    public void stop(){

    }

}
