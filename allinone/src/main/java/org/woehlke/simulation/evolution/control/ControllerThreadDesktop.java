package org.woehlke.simulation.evolution.control;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.World;
import org.woehlke.simulation.evolution.model.WorldMapFood;
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
public class ControllerThreadDesktop extends Thread implements Runnable,
  WindowListener,
  WindowFocusListener,
  WindowStateListener {

    private SimulatedEvolutionFrame frame;

    private final SimulatedEvolutionProperties simulatedEvolutionProperties;
    private final ObjectRegistry objectRegistry;

    private final World world;
    private final WorldCanvas canvas;
    private final WorldMapFood worldMapFood;
    private final PanelLifeCycleStatus panelLifeCycleStatus;
    /*
    private final ControllerThreadDesktop controller;
    private final LifeCycleCountContainer statistics;

    private final PanelSubtitle panelSubtitle;
    private final PanelCopyright panelCopyright;
    private final PanelButtons panelButtons;

*/

        /**
   * Time to Wait in ms.
   */
  protected final int TIME_TO_WAIT = 100;

  /**
   * Control for Threading.
   */
  private Boolean mySemaphore;


  public ControllerThreadDesktop(SimulatedEvolutionProperties simulatedEvolutionProperties, ObjectRegistry objectRegistry, World world, WorldCanvas canvas, WorldMapFood worldMapFood, PanelLifeCycleStatus panelLifeCycleStatus) {
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      this.objectRegistry = objectRegistry;
      this.world = world;
      this.canvas = canvas;
      this.worldMapFood = worldMapFood;
      this.panelLifeCycleStatus = panelLifeCycleStatus;
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
    System.exit(simulatedEvolutionProperties.getExitStatus());
  }

  public void windowClosed(WindowEvent e) {
    this.exit();
    System.exit(simulatedEvolutionProperties.getExitStatus());
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
      panelLifeCycleStatus.updateLifeCycleCount();
  }

  public void exit() {
    synchronized (mySemaphore) {
      mySemaphore = Boolean.FALSE;
    }
  }

  public void increaseFoodPerDay() {
      objectRegistry.increaseFoodPerDay();
  }

  public void decreaseFoodPerDay() {
      objectRegistry.decreaseFoodPerDay();
  }

  public void toggleGardenOfEden() {
    objectRegistry.toggleGardenOfEden();
    worldMapFood.toggleGardenOfEden();
  }

  public void showStatistic() {

  }

}
