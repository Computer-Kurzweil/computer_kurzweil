package org.woehlke.simulation.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;

import javax.swing.*;
import java.awt.*;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
@Component
public class CyclicCellularAutomatonButtonsPanel extends JPanel {

  @Getter private final JButton buttonVonNeumann;
  @Getter private final JButton buttonMoore;
  @Getter private final JButton buttonWoehlke;

  @Getter private final ComputerKurzweilApplicationContext ctx;

  @Autowired
  public CyclicCellularAutomatonButtonsPanel(ComputerKurzweilApplicationContext ctx) {
    this.ctx = ctx;
    ComputerKurzweilProperties.Cca.View.Neighborhood neighborhoodConf = ctx.getProperties().getCca().getView().getNeighborhood();
    JLabel neighborhoodLabel = new JLabel(neighborhoodConf.getTitle());
    this.buttonVonNeumann = new JButton(neighborhoodConf.getTypeVonNeumann());
    this.buttonMoore = new JButton(neighborhoodConf.getTypeMoore());
    this.buttonWoehlke = new JButton(neighborhoodConf.getTypeWoehlke());
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    this.add(neighborhoodLabel);
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
  }

}
