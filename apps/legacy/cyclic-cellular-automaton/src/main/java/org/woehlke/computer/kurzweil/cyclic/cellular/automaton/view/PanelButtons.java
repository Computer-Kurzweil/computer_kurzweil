package org.woehlke.computer.kurzweil.cyclic.cellular.automaton.view;

import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.config.ObjectRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Cyclic Cellular Automaton.
 * <p>
 * (C) 2006 - 2022 Thomas Woehlke.
 * @see <a href="https://java.woehlke.org/cyclic-cellular-automaton">Maven Project Page</a>
 *
 * @author Thomas Woehlke
 */
public class PanelButtons extends JPanel implements ActionListener {

    private static final long serialVersionUID = 242L;

    private ObjectRegistry ctx;
    private final JLabel neighborhoodLabel = new JLabel("Neighbourhood:");
    private final JRadioButton buttonVonNeumann = new JRadioButton("Von Neumann", true);
    private final JRadioButton buttonMoore = new JRadioButton("Moore");
    private final JRadioButton buttonWoehlke = new JRadioButton("Woehlke");
    private final JButton buttonRestart = new JButton("Restart");
    private final ButtonGroup bgroup = new ButtonGroup();

    public PanelButtons(ObjectRegistry ctx) {
        this.ctx = ctx;

        bgroup.add(buttonVonNeumann);
        bgroup.add(buttonMoore);
        bgroup.add(buttonWoehlke);

        this.add(neighborhoodLabel);
        this.add(buttonVonNeumann);
        this.add(buttonMoore);
        this.add(buttonWoehlke);
        this.add(buttonRestart);

        this.buttonRestart.addActionListener(this);
    }

    /**
     * TODO write doc.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.buttonRestart) {
            if (buttonVonNeumann.isSelected()) {
                this.ctx.getController().pushButtonVonNeumann();
            }
            if (buttonMoore.isSelected()) {
                this.ctx.getController().pushButtonMoore();
            }
            if (buttonWoehlke.isSelected()) {
                this.ctx.getController().pushButtonWoehlke();
            }
        }
    }
}
