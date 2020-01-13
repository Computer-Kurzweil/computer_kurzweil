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
    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionStatisticsPanel panelStatistics;
    private final SimulatedEvolutionButtonRowPanel panelButtons;

  @Autowired
  public SimulatedEvolutionFrame(
      SimulatedEvolutionContext ctx,
      SimulatedEvolutionCanvas canvas,
      SimulatedEvolutionStatisticsPanel panelStatistics,
      SimulatedEvolutionButtonRowPanel panelButtons
    ) {
    this.ctx=ctx;
    this.canvas = canvas;
    this.panelStatistics =  panelStatistics;
    this.panelButtons = panelButtons;
    PanelCopyright panelCopyright = new PanelCopyright(this.ctx);
    PanelSubtitle panelSubtitle = new PanelSubtitle(this.ctx);
    BoxLayout layout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
      this.setLayout(layout);
      this.add(panelSubtitle);
      this.add(canvas);
      this.add(panelCopyright);
      this.add(panelStatistics);
      this.add(panelButtons);
      showMe();
    this.ctx.setFrame(this);
  }

    /**
     * TODO write doc.
     */
    public void showMe() {
        this.setVisible(true);
        canvas.setVisible(true);
        panelStatistics.setVisible(true);
        panelButtons.setVisible(true);
        this.setVisible(true);
        repaint();
    }

    public void repaint(){
        canvas.repaint();
        panelStatistics.repaint();
        panelButtons.repaint();
        super.repaint();
    }

    public void start() {
        ctx.getControllerThread().start();
    }

}
