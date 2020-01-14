package org.woehlke.simulation.evolution.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.view.PanelCopyright;
import org.woehlke.simulation.allinone.view.PanelSubtitle;
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

  @Autowired
  public SimulatedEvolutionFrame(
      SimulatedEvolutionContext ctx
  ) {
      this.ctx = ctx;
      this.canvas = new SimulatedEvolutionCanvas(this.ctx);
      this.statisticsPanel = new SimulatedEvolutionStatisticsPanel(this.ctx);
      this.panelButtons = new SimulatedEvolutionButtonRowPanel(this.ctx);
      this.ctx.setPanelStatistics(this.statisticsPanel);
      this.ctx.setPanelButtons(this.panelButtons);
      this.ctx.setCanvas(this.canvas);
      if(this.ctx == null){
          log.warning("ctx==null but should not");
      } else {
          PanelCopyright panelCopyright = new PanelCopyright(this.ctx);
          PanelSubtitle panelSubtitle = new PanelSubtitle(this.ctx);
          BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
          this.setLayout(layout);
          this.add(panelSubtitle);
          this.add(this.ctx.getCanvas());
          this.add(panelCopyright);
          this.add(this.ctx.getPanelStatistics());
          this.add(this.ctx.getPanelButtons());
      }
  }

    /**
     * TODO write doc.
     */
    public void showMe() {
        this.setVisible(true);
        this.canvas.setVisible(true);
        ctx.getPanelStatistics().setVisible(true);
        ctx.getPanelButtons().setVisible(true);
        this.setVisible(true);
        repaint();
    }

    public void repaint(){
        if(ctx !=null) {
            if (ctx.getCanvas() != null) {
                ctx.getCanvas().repaint();
            }
            if (ctx.getPanelStatistics() != null) {
                ctx.getPanelStatistics().repaint();
            }
            if (ctx.getPanelButtons() != null) {
                ctx.getPanelButtons().repaint();
            }
        }
        super.repaint();
    }

    public void start() {
        showMe();
        ctx.getControllerThread().start();
    }

    public void stop(){

    }

}
