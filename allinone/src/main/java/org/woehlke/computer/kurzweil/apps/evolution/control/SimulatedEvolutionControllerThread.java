package org.woehlke.computer.kurzweil.apps.evolution.control;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;
import org.woehlke.computer.kurzweil.apps.evolution.model.world.SimulatedEvolutionWorld;
import org.woehlke.computer.kurzweil.apps.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.computer.kurzweil.apps.evolution.view.widgets.SimulatedEvolutionButtonRowPanel;
import org.woehlke.computer.kurzweil.apps.evolution.view.widgets.SimulatedEvolutionStatisticsPanel;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;

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
public class SimulatedEvolutionControllerThread extends Thread implements
    ActionListener, ControllerThread {

    private final SimulatedEvolutionStateService ctxService;

    @Getter
    private final SimulatedEvolutionWorld world;
    private final SimulatedEvolutionStatisticsPanel statisticsPanel;
    private final SimulatedEvolutionButtonRowPanel panelButtons;
    private final SimulatedEvolutionFrame frame;

    private Boolean goOn;

  public SimulatedEvolutionControllerThread(
      SimulatedEvolutionStateService ctxService,
      SimulatedEvolutionWorld world,
      SimulatedEvolutionStatisticsPanel statisticsPanel,
      SimulatedEvolutionButtonRowPanel panelButtons,
      SimulatedEvolutionFrame frame
  ) {
      this.ctxService = ctxService;
      this.world = world;
      this.statisticsPanel = statisticsPanel;
      this.panelButtons = panelButtons;
      this.frame = frame;
      this.goOn = Boolean.TRUE;
      this.panelButtons.getFoodPanel().getButtonFoodPerDayIncrease().addActionListener(this);
      this.panelButtons.getFoodPanel().getButtonFoodPerDayDecrease().addActionListener(this);
      this.panelButtons.getGardenOfEdenPanel().getButtonToggleGardenOfEden().addActionListener(this);
  }

  //TODO: move to View Object.
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panelButtons.getFoodPanel().getButtonFoodPerDayIncrease()) {
            ctxService.increaseFoodPerDay();
            this.panelButtons.getFoodPanel().getFoodPerDayField().setText(
                ctxService.getSimulatedEvolutionState().getFoodPerDay()+""
            );
        } else if (ae.getSource() == this.panelButtons.getFoodPanel().getButtonFoodPerDayDecrease()) {
            ctxService.decreaseFoodPerDay();
            this.panelButtons.getFoodPanel().getFoodPerDayField().setText(
                ctxService.getSimulatedEvolutionState().getFoodPerDay()+""
            );
        } else if (ae.getSource() == this.panelButtons.getGardenOfEdenPanel().getButtonToggleGardenOfEden()) {
            ctxService.toggleGardenOfEden();
            this.world.toggleGardenOfEden();
            boolean selected = ctxService.getSimulatedEvolutionState().isGardenOfEdenEnabled();
            this.panelButtons.getGardenOfEdenPanel().getGardenOfEdenEnabled().setSelected(selected);
        }
    }

  public void run() {
    show();
    boolean doMyJob;
    do {
      synchronized (goOn) {
        doMyJob = goOn.booleanValue();
      }
     this.world.step();
      this.statisticsPanel.update();
     this.frame.repaint();
      try {
        sleep( this.ctxService.getCtx().getProperties().getEvolution().getControl().getTime2wait() );
      } catch (InterruptedException e) {
        System.out.println(e.getLocalizedMessage());
      }
    }
    while (doMyJob);
  }

  protected void show() {
      this.frame.showMe();
  }

    public void exit() {
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
