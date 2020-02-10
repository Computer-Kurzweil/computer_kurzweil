package org.woehlke.computer.kurzweil.apps.cca;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.AppGuiComponent;

import javax.swing.*;
import java.awt.*;

@Log
@Getter
public class CyclicCellularAutomatonButtonsPanel extends JPanel implements
     Startable, AppGuiComponent {

  private final JButton buttonVonNeumann;
  private final JButton buttonMoore;
  private final JButton buttonWoehlke;
  private final CyclicCellularAutomatonCanvas canvas;

  public CyclicCellularAutomatonButtonsPanel(
      CyclicCellularAutomatonCanvas canvas
  ) {
    this.canvas = canvas;
    this.buttonVonNeumann = new JButton(this.canvas.getCtx().getProperties().getCca().getView().getNeighborhood().getTypeVonNeumann());
    this.buttonMoore = new JButton(this.canvas.getCtx().getProperties().getCca().getView().getNeighborhood().getTypeMoore());
    this.buttonWoehlke = new JButton(this.canvas.getCtx().getProperties().getCca().getView().getNeighborhood().getTypeWoehlke());
    this.setLayout(new FlowLayout());
    this.add(new JLabel(this.canvas.getCtx().getProperties().getCca().getView().getNeighborhood().getTitle()));
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
    this.buttonVonNeumann.addActionListener(this.canvas);
    this.buttonMoore.addActionListener(this.canvas);
    this.buttonWoehlke.addActionListener(this.canvas);
  }

    @Override
    public void start() {
        log.info("start");
        this.setVisible(true);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.setVisible(false);
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void showMe() {
        log.info("showMe");
    }

    @Override
    public void hideMe() {
        log.info("hideMe");
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
