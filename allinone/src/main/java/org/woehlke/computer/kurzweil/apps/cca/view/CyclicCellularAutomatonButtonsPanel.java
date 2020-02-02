package org.woehlke.computer.kurzweil.apps.cca.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;

import javax.swing.*;
import java.awt.*;

@Log
public class CyclicCellularAutomatonButtonsPanel extends JPanel implements
     Startable, AppGuiComponent {

  @Getter private final JButton buttonVonNeumann;
  @Getter private final JButton buttonMoore;
  @Getter private final JButton buttonWoehlke;

  @Getter private final CyclicCellularAutomatonCanvas canvas;

  public CyclicCellularAutomatonButtonsPanel(
      CyclicCellularAutomatonCanvas canvas
  ) {
    this.canvas = canvas;
    ComputerKurzweilProperties.Cca.View.Neighborhood neighborhoodConf = this.canvas.getCtx().getProperties().getCca().getView().getNeighborhood();
    this.buttonVonNeumann = new JButton(neighborhoodConf.getTypeVonNeumann());
    this.buttonMoore = new JButton(neighborhoodConf.getTypeMoore());
    this.buttonWoehlke = new JButton(neighborhoodConf.getTypeWoehlke());
    this.setLayout(new FlowLayout());
    this.add(new JLabel(neighborhoodConf.getTitle()));
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
    public void handleUserSignal(UserSignal userSignal) {

    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void showMe() {

    }

    @Override
    public void hideMe() {

    }
}
