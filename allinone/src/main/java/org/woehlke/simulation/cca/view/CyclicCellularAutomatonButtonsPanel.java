package org.woehlke.simulation.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;

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
  private final CyclicCellularAutomatonContext ctx;

  @Autowired
  public CyclicCellularAutomatonButtonsPanel(CyclicCellularAutomatonContext ctx) {
    this.ctx = ctx;
    JLabel neighborhood = new JLabel("Neighborhood");
    this.buttonVonNeumann = new JButton("Von Neumann");
    this.buttonMoore = new JButton("Moore");
    this.buttonWoehlke = new JButton("Woehlke");
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    this.add(neighborhood);
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
  }

}
