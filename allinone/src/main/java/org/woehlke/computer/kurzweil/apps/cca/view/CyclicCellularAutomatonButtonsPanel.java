package org.woehlke.computer.kurzweil.apps.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class CyclicCellularAutomatonButtonsPanel extends JPanel implements
    ActionListener, Startable, AppGuiComponent {

  @Getter private final JButton buttonVonNeumann;
  @Getter private final JButton buttonMoore;
  @Getter private final JButton buttonWoehlke;

  @Getter private final ComputerKurzweilApplicationContext ctx;

  public CyclicCellularAutomatonButtonsPanel(ComputerKurzweilApplicationContext ctx) {
    this.ctx = ctx;
    ComputerKurzweilProperties.Cca.View.Neighborhood neighborhoodConf = ctx.getProperties().getCca().getView().getNeighborhood();
    this.buttonVonNeumann = new JButton(neighborhoodConf.getTypeVonNeumann());
    this.buttonMoore = new JButton(neighborhoodConf.getTypeMoore());
    this.buttonWoehlke = new JButton(neighborhoodConf.getTypeWoehlke());
    this.setLayout(new FlowLayout());
    this.add(new JLabel(neighborhoodConf.getTitle()));
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
    this.buttonVonNeumann.addActionListener(  this);
    this.buttonMoore.addActionListener(  this);
    this.buttonWoehlke.addActionListener(  this);
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
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.buttonVonNeumann) {
            ctx.getFrame().startWithNeighbourhoodVonNeumann();
        } else if (ae.getSource() == this.buttonMoore) {
            ctx.getFrame().startWithNeighbourhoodMoore();
        } else if (ae.getSource() == this.buttonWoehlke) {
            ctx.getFrame().startWithNeighbourhoodWoehlke();
        }
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {

    }

    @Override
    public void update() {

    }
}
