package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.BoxLayoutVertical;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.model.Bounds;
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
@ToString(callSuper=true)
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
        rootPane.setLayout(new BoxLayoutVertical( rootPane ));
        rootPane.setBorder( this.ctx.getBorder());
        rootPane.add(PanelSubtitle.getPanelSubtitleForApplication(this.ctx));
        rootPane.add(tabbedPane);
        rootPane.add(new PanelCopyright(this.ctx));
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        showMe();
    }

    public List<TabPanel> getApps(){
        return tabbedPane.getApps();
    }

    public void showMe() {
        pack();
        Bounds frameBounds = Bounds.getFrameBounds(rootPane);
        this.setBounds(
            frameBounds.getMyStartX(),
            frameBounds.getMyStartY(),
            frameBounds.getMyWidth(),
            frameBounds.getMyHeight()
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
        tabbedPane.start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        tabbedPane.stop();
        log.info("stopped");
    }
}
