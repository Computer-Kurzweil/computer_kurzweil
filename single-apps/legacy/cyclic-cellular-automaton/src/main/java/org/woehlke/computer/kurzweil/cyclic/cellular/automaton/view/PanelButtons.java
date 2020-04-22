package org.woehlke.computer.kurzweil.cyclic.cellular.automaton.view;

import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.config.ObjectRegistry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * TODO write doc.
 */
public class PanelButtons extends JPanel implements ActionListener {

  private final JButton buttonVonNeumann;
  private final JButton buttonMoore;
  private final JButton buttonWoehlke;
  private ObjectRegistry ctx;

  public PanelButtons(ObjectRegistry ctx) {
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
    this.buttonVonNeumann.addActionListener(this);
    this.buttonMoore.addActionListener(this);
    this.buttonWoehlke.addActionListener(this);
  }

  /**
   * TODO write doc.
   */
  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == this.buttonVonNeumann) {
        this.ctx.getController().pushButtonVonNeumann();
    } else if (ae.getSource() == this.buttonMoore) {
        this.ctx.getController().pushButtonMoore();
    } else if (ae.getSource() == this.buttonWoehlke) {
        this.ctx.getController().pushButtonWoehlke();
    }
  }
}
