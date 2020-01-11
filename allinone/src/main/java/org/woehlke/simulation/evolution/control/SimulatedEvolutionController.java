package org.woehlke.simulation.evolution.control;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorld;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorldMapFood;
import org.woehlke.simulation.evolution.view.*;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

/**
 * The ControllerThreadApplet controls the Interactions between Model and View (MVC-Pattern).
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Component
public class SimulatedEvolutionController extends Thread implements Runnable,
  WindowListener,
  WindowFocusListener,
  WindowStateListener {

    private final SimulatedEvolutionProperties properties;
    private final SimulatedEvolutionContext context;
    private final SimulatedEvolutionWorldMapFood worldMapFood;
    private final SimulatedEvolutionPanelStatistics statisticsPanel;
    private final SimulatedEvolutionWorldCanvas canvas;

    private SimulatedEvolutionWorld world;
    private SimulatedEvolutionFrame frame;

    /**
   * Time to Wait in ms.
   */
  protected final int TIME_TO_WAIT = 100;

  /**
   * Control for Threading.
   */
  private Boolean mySemaphore;

  public SimulatedEvolutionController(
      SimulatedEvolutionProperties simulatedEvolutionProperties,
      SimulatedEvolutionContext simulatedEvolutionContext,
      SimulatedEvolutionWorldCanvas canvas,
      SimulatedEvolutionWorldMapFood simulatedEvolutionWorldMapFood,
      SimulatedEvolutionPanelStatistics simulatedEvolutionPanelStatistics
  ) {
      this.properties = simulatedEvolutionProperties;
      this.context = simulatedEvolutionContext;
      this.canvas = canvas;
      this.worldMapFood = simulatedEvolutionWorldMapFood;
      this.statisticsPanel = simulatedEvolutionPanelStatistics;
      this.mySemaphore = Boolean.TRUE;
  }

  public void run() {
    show();
    boolean doMyJob = true;
    do {
      synchronized (mySemaphore) {
        doMyJob = mySemaphore.booleanValue();
      }
        world.letLivePopulation();
        canvas.repaint();
      try {
        sleep(TIME_TO_WAIT);
      } catch (InterruptedException e) {
        System.out.println(e.getLocalizedMessage());
      }
    }
    while (doMyJob);
  }

  protected void show() {
      frame.showMe();
  }

  public void windowOpened(WindowEvent e) {
    show();
  }

  public void windowDeiconified(WindowEvent e) {
    show();
  }

  public void windowActivated(WindowEvent e) {
    show();
  }

  public void windowClosing(WindowEvent e) {
    this.exit();
    System.exit(properties.getExitStatus());
  }

  public void windowClosed(WindowEvent e) {
    this.exit();
    System.exit(properties.getExitStatus());
  }

  public void windowIconified(WindowEvent e) {
  }

  public void windowDeactivated(WindowEvent e) {
  }

  @Override
  public void windowGainedFocus(WindowEvent e) {
    show();
  }

  @Override
  public void windowLostFocus(WindowEvent e) {

  }

  @Override
  public void windowStateChanged(WindowEvent e) {
    if (e.getSource() == frame) {
      switch (e.getNewState()) {
        case Frame.MAXIMIZED_BOTH:
        case Frame.MAXIMIZED_HORIZ:
        case Frame.MAXIMIZED_VERT:
        case Frame.NORMAL:
          show();
          break;
        default:
          break;
      }
    }
  }

  public void updateLifeCycleCount() {
      statisticsPanel.updateTextFields();
  }

  public void exit() {
    synchronized (mySemaphore) {
      mySemaphore = Boolean.FALSE;
    }
  }

  public void increaseFoodPerDay() {
      context.increaseFoodPerDay();
  }

  public void decreaseFoodPerDay() {
      context.decreaseFoodPerDay();
  }

  public void toggleGardenOfEden() {
    context.toggleGardenOfEden();
    worldMapFood.toggleGardenOfEden();
  }

  public void showStatistic() {

  }

    public void setFrame(SimulatedEvolutionFrame frame) {
        this.frame = frame;
    }

    public SimulatedEvolutionWorld getWorld() {
        return world;
    }

    public void setWorld(SimulatedEvolutionWorld world) {
        this.world = world;
    }
}
