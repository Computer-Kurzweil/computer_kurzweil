package org.woehlke.simulation.evolution.control;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
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
@Log
@Component
public class SimulatedEvolutionControllerThread extends Thread implements Runnable,
    WindowListener,
    WindowFocusListener,
    WindowStateListener,
    ActionListener {

    private final SimulatedEvolutionContext ctx;

    @Getter
    private final SimulatedEvolutionWorld world;

    private Boolean mySemaphore;

  public SimulatedEvolutionControllerThread(
      SimulatedEvolutionContext ctx,
      SimulatedEvolutionWorld world) {
      this.ctx = ctx;
      this.world = world;
      this.mySemaphore = Boolean.TRUE;
      this.ctx.setControllerThread(this);
  }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  ctx.getPanelButtons().getButtonFoodPerDayIncrease()) {
            ctx.increaseFoodPerDay();
            ctx.getPanelButtons().setFoodPerDayFieldText(ctx.getFoodPerDay()+"");
        } else if (ae.getSource() ==  ctx.getPanelButtons().getButtonFoodPerDayDecrease()) {
            ctx.decreaseFoodPerDay();
            ctx.getPanelButtons().setFoodPerDayFieldText(ctx.getFoodPerDay()+"");
        } else if (ae.getSource() == this. ctx.getPanelButtons().getButtonToggleGardenOfEden()) {
            this.toggleGardenOfEden();
            boolean selected = ctx.isGardenOfEdenEnabled();
            ctx.getPanelButtons().setGardenOfEdenEnabled(selected);
        }
    }

  public void run() {
    show();
    boolean doMyJob;
    do {
      synchronized (mySemaphore) {
        doMyJob = mySemaphore.booleanValue();
      }
        this.world .letLivePopulation();
      ctx.getFrame().repaint();
      try {
        sleep(ctx.getProperties().getControl().getTime2wait());
      } catch (InterruptedException e) {
        System.out.println(e.getLocalizedMessage());
      }
    }
    while (doMyJob);
  }

  protected void show() {
      this.ctx.getFrame().showMe();
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
    if (e.getSource() ==  this.ctx.getFrame()) {
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
    ctx.getPanelStatistics().updateTextFields();
  }

  public void updateLifeCycleCount() {
      ctx.getPanelStatistics().updateTextFields();
  }

  public void exit() {
    synchronized (mySemaphore) {
      mySemaphore = Boolean.FALSE;
    }
    System.exit(ctx.getProperties().getControl().getExitStatus());
  }

  public void toggleGardenOfEden(){ this.world.toggleGardenOfEden();
  }

    public void setPanelStatistics(SimulatedEvolutionStatisticsPanel panelStatistics) {
        this.ctx.setPanelStatistics(panelStatistics);
    }

    public void setFrame(SimulatedEvolutionFrame frame) {
        this.ctx.setFrame(frame);
    }

    public void setPanelButtons(SimulatedEvolutionButtonRowPanel panelButtons) {
        panelButtons.registerController(this);
        this.ctx.setPanelButtons(panelButtons);
    }

}
