package org.woehlke.simulation.evolution.view;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.all.view.PanelCopyright;
import org.woehlke.simulation.all.view.PanelSubtitle;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorld;

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

    @Getter
    private final SimulatedEvolutionContext ctx;

    @Getter
    private final SimulatedEvolutionCanvas canvas;

    @Getter
    private final SimulatedEvolutionWorld world;

  @Autowired
  public SimulatedEvolutionFrame(
      SimulatedEvolutionContext ctx,
      SimulatedEvolutionCanvas canvas,
      SimulatedEvolutionWorld world
  ) {
      this.ctx = ctx;
      this.canvas = canvas;
      this.world = world;
      PanelCopyright panelCopyright = new PanelCopyright(this.ctx);
      PanelSubtitle panelSubtitle = new PanelSubtitle(this.ctx);
      BoxLayout layout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
      this.setLayout(layout);
      this.add(panelSubtitle);
      this.add(this.canvas);
      this.add(panelCopyright);
      this.add(this.ctx.getPanelStatistics());
      this.add(this.ctx.getPanelButtons());
      showMe();
      this.ctx.setFrame(this);
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
        this.canvas.repaint();
        ctx.getPanelStatistics().repaint();
        ctx.getPanelButtons().repaint();
        super.repaint();
    }

    public void start() {
        ctx.getControllerThread().start();
    }

}
