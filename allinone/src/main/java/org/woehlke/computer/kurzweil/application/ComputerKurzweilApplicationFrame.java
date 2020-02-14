package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.widgets.layouts.ComputerKurzweilApplicationFrameLayout;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.model.Bounds;
import org.woehlke.computer.kurzweil.widgets.borders.PanelBorder;
import org.woehlke.computer.kurzweil.widgets.PanelCopyright;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.List;

@Log
@Getter
public class ComputerKurzweilApplicationFrame extends JFrame implements Serializable,
    MenuContainer,
    ImageObserver,
    Accessible,
    WindowListener,
    WindowFocusListener,
    WindowStateListener,
    Startable,
    GuiComponentTab {

    private final ComputerKurzweilApplicationContext ctx;
    private final ComputerKurzweilApplicationTabbedPane tabbedPane;

    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilProperties properties
    ) throws HeadlessException {
        super(properties.getAllinone().getView().getTitle());
        this.ctx = new ComputerKurzweilApplicationContext(properties,this);
        tabbedPane = new ComputerKurzweilApplicationTabbedPane(this.ctx);
        rootPane.setLayout(new ComputerKurzweilApplicationFrameLayout( rootPane ));
        rootPane.setBorder(PanelBorder.getBorder());
        rootPane.add(PanelSubtitle.getPanelSubtitleForApplication(this.ctx));
        rootPane.add(tabbedPane);
        rootPane.add(new PanelCopyright(this.ctx));
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        showMe();
    }

    public List<TabContext> getApps(){
        return tabbedPane.getApps();
    }

    private Bounds getMyBounds(){
        double height = rootPane.getHeight();
        double width = rootPane.getWidth();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Bounds(height,width,screenSize);
    }

    public void showMe() {
        pack();
        Bounds bounds = getMyBounds();
        this.setBounds(
            bounds.getMystartX(),
            bounds.getMystartY(),
            bounds.getMywidth(),
            bounds.getMyheight()
        );;
        tabbedPane.showMe();
        this.setVisible(true);
        toFront();
        repaint();
    }

    public void windowOpened(WindowEvent e) {
        showMe();
    }

    public void windowClosing(WindowEvent e) {
        exit();
    }

    public void windowClosed(WindowEvent e) {
        exit();
    }

    public void windowIconified(WindowEvent e) { }

    public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
        //tabbedPane.getActiveTab().start();
    }

    public void windowDeactivated(WindowEvent e) {
        //tabbedPane.getActiveTab().stop();
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {}

    @Override
    public void windowLostFocus(WindowEvent e) {}

    @Override
    public void windowStateChanged(WindowEvent e) {}

    public void exit() {
        this.dispose();
        System.exit(0);
    }

    @Override
    public void start(){
        log.info("start");
        showMe();
        tabbedPane.getActiveTab().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        tabbedPane.getActiveTab().stop();
        log.info("stopped");
    }
}
