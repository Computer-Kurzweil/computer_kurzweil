package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.SimulatedEvolutionParameter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This Frame wraps the SimulatedEvolutionApplet which is the Container for this Simulution.
 *
 * @see SimulatedEvolutionApplet
 * @see javax.swing.JFrame
 * @see java.awt.image.ImageObserver
 * @see java.awt.event.WindowListener
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log
@Getter
public class SimulatedEvolutionTab extends JFrame implements
        MenuContainer,
        WindowListener,
        SimulatedEvolution,
        Tab {

    private static final long serialVersionUID = 7526471155622776147L;

    private final static String TITLE = "Simulated Evolution";

    private final static int EXIT_STATUS = 0;

    private final static String APPLET_POSITION = "Center";

    private final static int HEIGHT_OF_TITLE = 30;

    private final static int START_POSITION_ON_SCREEN_X = 100;

    private final static int START_POSITION_ON_SCREEN_Y = 100;

    private SimulatedEvolutionApplet simulatedEvolutionApplet;

    private final SimulatedEvolutionParameter simulatedEvolutionParameter;

    private final ComputerKurzweilContext ctx;

    private final SimulatedEvolutionContext tabCtx;

    private final ComputerKurzweilProperties properties;

    private void setMyBounds(){
        int x = START_POSITION_ON_SCREEN_X;
        int y = START_POSITION_ON_SCREEN_Y;
        int width = simulatedEvolutionApplet.getCanvasDimensions().getX();
        int height = simulatedEvolutionApplet.getCanvasDimensions().getY() + HEIGHT_OF_TITLE;
        setBounds(x, y, width, height);
    }

    public SimulatedEvolutionTab(ComputerKurzweilProperties properties) {
        super(TITLE);
        this.properties = properties;
        simulatedEvolutionParameter = new SimulatedEvolutionParameter();
        simulatedEvolutionApplet = new SimulatedEvolutionApplet();
        simulatedEvolutionApplet.init();
        add(APPLET_POSITION, simulatedEvolutionApplet);
        this.ctx = new ComputerKurzweilContext(properties);
        this.tabCtx = new SimulatedEvolutionContext(this,ctx);
        pack();
        setVisible(true);
        toFront();
        addWindowListener(this);
    }

    public void windowOpened(WindowEvent e) {
        setMyBounds();
        setVisible(true);
        toFront();
    }

    public void windowClosing(WindowEvent e) {
        System.exit(EXIT_STATUS);
    }

    public void windowClosed(WindowEvent e) {
        System.exit(EXIT_STATUS);
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {
        setMyBounds();
        setVisible(true);
        toFront();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void update(){}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
