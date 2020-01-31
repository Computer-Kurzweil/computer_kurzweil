package org.woehlke.simulation.allinone.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.parts.Bounds;
import org.woehlke.simulation.allinone.view.parts.PanelBorder;
import org.woehlke.simulation.allinone.view.parts.PanelCopyright;
import org.woehlke.simulation.allinone.view.parts.PanelSubtitle;
import org.woehlke.simulation.allinone.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.simulation.allinone.view.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.simulation.allinone.view.tabs.MandelbrotTab;
import org.woehlke.simulation.allinone.view.tabs.SimulatedEvolutionTab;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
@Component
public class ComputerKurzweilApplicationFrame extends JFrame implements
    MenuContainer,
    ImageObserver,
    Serializable,
    Accessible, WindowListener {

    private final Bounds bounds;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;

    @Getter
    private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;

    @Getter
    private final MandelbrotTab mandelbrotTab;

    @Getter
    private final SimulatedEvolutionTab simulatedEvolutionTab;

    @Autowired
    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilApplicationContext ctx,
        CyclicCellularAutomatonTab cyclicCellularAutomatonTab,
        DiffusionLimitedAggregationTab diffusionLimitedAggregationTab,
        MandelbrotTab mandelbrotTab,
        SimulatedEvolutionTab simulatedEvolutionTab
    ) throws HeadlessException {
        super(ctx.getProperties().getAllinone().getView().getTitle());
        CompoundBorder border = PanelBorder.getBorder();
        this.ctx = ctx;
        this.cyclicCellularAutomatonTab = cyclicCellularAutomatonTab;
        this.diffusionLimitedAggregationTab = diffusionLimitedAggregationTab;
        this.mandelbrotTab = mandelbrotTab;
        this.simulatedEvolutionTab = simulatedEvolutionTab;
        PanelSubtitle panelSubtitle = new PanelSubtitle(ctx);
        PanelCopyright panelCopyright = new PanelCopyright(ctx);
        BoxLayout layout = new BoxLayout(rootPane, BoxLayout.Y_AXIS);
        rootPane.setLayout(layout);
        rootPane.setBorder(border);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(
            ctx.getProperties().getCca().getView().getTitle(), this.cyclicCellularAutomatonTab
        );
        tabbedPane.add(
            ctx.getProperties().getDla().getView().getTitle(), this.diffusionLimitedAggregationTab
        );
        tabbedPane.add(
            ctx.getProperties().getMandelbrot().getView().getTitle(), this.mandelbrotTab
        );
        tabbedPane.add(
            ctx.getProperties().getEvolution().getView().getTitle(), this.simulatedEvolutionTab
        );
        rootPane.add(panelSubtitle);
        rootPane.add(tabbedPane);
        rootPane.add(panelCopyright);
        pack();
        double height = rootPane.getHeight();
        double width = rootPane.getWidth();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        bounds = new Bounds(height,width,screenSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        showMe();
    }

    /**
     * TODO write doc.
     */
    public void showMe() {
        pack();
        this.setBounds(
            bounds.getMystartX(),
            bounds.getMystartY(),
            bounds.getMywidth(),
            bounds.getMyheight()
        );
        rootPane.setVisible(true);
        this.setVisible(true);
        toFront();
        repaint();
    }

    public void exit() {
        this.dispose();
    }

    public void start() {
        //this.simulatedEvolutionFrame.start();
    }

    public void repaint(){
        super.repaint();
        //this.getSimulatedEvolutionFrame().repaint();
    }

    public void windowOpened(WindowEvent e) {
        showMe();
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    public void windowIconified(WindowEvent e) { }

    public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
