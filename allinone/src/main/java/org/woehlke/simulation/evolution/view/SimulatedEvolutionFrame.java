package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.all.view.Bounds;
import org.woehlke.simulation.all.view.PanelCopyright;
import org.woehlke.simulation.all.view.PanelSubtitle;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.MenuContainer;
import java.awt.Toolkit;
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
public class SimulatedEvolutionFrame extends JFrame implements ImageObserver, MenuContainer {

    private final SimulatedEvolutionContext ctx;
    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionStatisticsPanel panelStatistics;
    private final SimulatedEvolutionButtonRowPanel panelButtons;
    private final Bounds bounds;

  @Autowired
  public SimulatedEvolutionFrame(
      SimulatedEvolutionContext ctx,
      SimulatedEvolutionCanvas canvas,
      SimulatedEvolutionStatisticsPanel panelStatistics,
      SimulatedEvolutionButtonRowPanel panelButtons
    ) {
    super(ctx.getProperties().getView().getTitle());
    this.ctx=ctx;
    this.canvas = canvas;
    this.panelStatistics =  panelStatistics;
    this.panelButtons = panelButtons;
    PanelCopyright panelCopyright = new PanelCopyright(this.ctx);
    PanelSubtitle panelSubtitle = new PanelSubtitle(this.ctx);
    BoxLayout layout = new BoxLayout(rootPane, BoxLayout.PAGE_AXIS);
    rootPane.setLayout(layout);
    rootPane.add(panelSubtitle);
    rootPane.add(canvas);
    rootPane.add(panelCopyright);
    rootPane.add(panelStatistics);
    rootPane.add(panelButtons);
      pack();
      double height = rootPane.getHeight();
      double width = rootPane.getWidth();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      bounds= new Bounds(height,width,screenSize);
      showMe();
    this.ctx.setFrame(this);
  }

    /**
     * TODO write doc.
     */
    public void showMe() {
        pack();
        this.setBounds(bounds.getMystartX(), bounds.getMystartY(), bounds.getMywidth(), bounds.getMyheight());
        rootPane.setVisible(true);
        canvas.setVisible(true);
        panelStatistics.setVisible(true);
        panelButtons.setVisible(true);
        this.setVisible(true);
        toFront();
        repaint();
    }

    public void repaint(){
        rootPane.repaint();
        canvas.repaint();
        panelStatistics.repaint();
        panelButtons.repaint();
        super.repaint();
    }
}
