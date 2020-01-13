package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.all.view.PanelCopyright;
import org.woehlke.simulation.all.view.PanelSubtitle;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

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
@Component
public class SimulatedEvolutionFrame extends JPanel implements ImageObserver {

    private final SimulatedEvolutionContext ctx;

  @Autowired
  public SimulatedEvolutionFrame(
      SimulatedEvolutionContext ctx
    ) {
    this.ctx=ctx;
    PanelCopyright panelCopyright = new PanelCopyright(this.ctx);
    PanelSubtitle panelSubtitle = new PanelSubtitle(this.ctx);
    BoxLayout layout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
      this.setLayout(layout);
      this.add(panelSubtitle);
      this.add(ctx.getCanvas());
      this.add(panelCopyright);
      this.add(ctx.getPanelStatistics());
      this.add(ctx.getPanelButtons());
      showMe();
    this.ctx.setFrame(this);
  }

    /**
     * TODO write doc.
     */
    public void showMe() {
        this.setVisible(true);
        ctx.getCanvas().setVisible(true);
        ctx.getPanelStatistics().setVisible(true);
        ctx.getPanelButtons().setVisible(true);
        this.setVisible(true);
        repaint();
    }

    public void repaint(){
        ctx.getCanvas().repaint();
        ctx.getPanelStatistics().repaint();
        ctx.getPanelButtons().repaint();
        super.repaint();
    }

    public void start() {
        ctx.getControllerThread().start();
    }

}
