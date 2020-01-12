package org.woehlke.simulation.evolution.control;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorld;
import org.woehlke.simulation.evolution.view.*;

import java.awt.Frame;
import java.awt.event.*;

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
public class SimulatedEvolutionControllerThread extends Thread implements Runnable,
    WindowListener,
    WindowFocusListener,
    WindowStateListener,
    ActionListener {

    private final SimulatedEvolutionProperties properties;
    private final SimulatedEvolutionContext context;

    private SimulatedEvolutionWorld world;
    private SimulatedEvolutionFrame frame;
    private SimulatedEvolutionStatisticsPanel panelStatistics;
    private SimulatedEvolutionButtonRowPanel panelButtons;


  protected final int TIME_TO_WAIT = 100;

  private Boolean mySemaphore;

  public SimulatedEvolutionControllerThread(
      SimulatedEvolutionProperties properties,
      SimulatedEvolutionContext context
  ) {
      this.properties = properties;
      this.context = context;
      this.mySemaphore = Boolean.TRUE;
  }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  panelButtons.getButtonFoodPerDayIncrease()) {
            context.increaseFoodPerDay();
            panelButtons.setFoodPerDayFieldText(context.getFoodPerDay()+"");
        } else if (ae.getSource() == panelButtons.getButtonFoodPerDayDecrease()) {
            context.decreaseFoodPerDay();
            panelButtons.setFoodPerDayFieldText(context.getFoodPerDay()+"");
        } else if (ae.getSource() == this.panelButtons.getButtonToggleGardenOfEden()) {
            this.toggleGardenOfEden();
            boolean selected = context.isGardenOfEdenEnabled();
            panelButtons.setGardenOfEdenEnabled(selected);
        }
    }

  public void run() {
    show();
    boolean doMyJob;
    do {
      synchronized (mySemaphore) {
        doMyJob = mySemaphore.booleanValue();
      }
        world.letLivePopulation();
        frame.repaint();
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
  }

  public void windowClosed(WindowEvent e) {
    this.exit();
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
    panelStatistics.updateTextFields();
  }

  public void updateLifeCycleCount() {
      panelStatistics.updateTextFields();
  }

  public void exit() {
    synchronized (mySemaphore) {
      mySemaphore = Boolean.FALSE;
    }
    System.exit(properties.getControl().getExitStatus());
  }

  public void toggleGardenOfEden() {
    world.toggleGardenOfEden();
  }

    public void setWorld(SimulatedEvolutionWorld world) {
        this.world = world;
    }

    public void setPanelStatistics(SimulatedEvolutionStatisticsPanel panelStatistics) {
        this.panelStatistics = panelStatistics;
    }

    public void setFrame(SimulatedEvolutionFrame frame) {
        this.frame = frame;
    }

    public void setPanelButtons(SimulatedEvolutionButtonRowPanel panelButtons) {
        this.panelButtons = panelButtons;
    }

}
