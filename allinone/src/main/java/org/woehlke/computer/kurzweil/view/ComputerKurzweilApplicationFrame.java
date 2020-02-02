package org.woehlke.computer.kurzweil.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.model.Bounds;
import org.woehlke.computer.kurzweil.view.common.PanelBorder;
import org.woehlke.computer.kurzweil.view.common.PanelCopyright;
import org.woehlke.computer.kurzweil.view.common.PanelSubtitle;
import org.woehlke.computer.kurzweil.view.widgets.ComputerKurzweilApplicationFrameLayout;

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
public class ComputerKurzweilApplicationFrame extends JFrame implements Serializable,
    MenuContainer,
    ImageObserver,
    Accessible,
    WindowListener,
    WindowFocusListener,
    WindowStateListener, Startable, AppGuiComponent {

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final ComputerKurzweilApplicationTabbedPane tabbedPane;

    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilProperties properties
    ) throws HeadlessException {
        super(properties.getAllinone().getView().getTitle());
        this.ctx = new ComputerKurzweilApplicationContext(properties,this);
        tabbedPane = new ComputerKurzweilApplicationTabbedPane(this.ctx);
        rootPane.setLayout(new ComputerKurzweilApplicationFrameLayout( rootPane ));
        rootPane.setBorder(PanelBorder.getBorder());
        rootPane.add(PanelSubtitle.getPanelSubtitleForAllinone(this.ctx));
        rootPane.add(tabbedPane);
        rootPane.add(new PanelCopyright(this.ctx));
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        showMe();
    }

    public List<AppContext> getApps(){
        return tabbedPane.getApps();
    }

    @Override
    public void start(){
        log.info("start");
        tabbedPane.getActiveTab().start();
        showMe();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        log.info("stopped");
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
        );
        tabbedPane.setVisible(true);
        this.setVisible(true);
        toFront();
        repaint();
    }

    public void exit() {
        this.dispose();
        System.exit(0);
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
    }

    public void windowDeactivated(WindowEvent e) { }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowStateChanged(WindowEvent e) {

    }

    public void startWithNeighbourhoodVonNeumann() {

    }

    public void startWithNeighbourhoodMoore() {

    }

    public void startWithNeighbourhoodWoehlke() {

    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

    @Override
    public void update() {
        this.tabbedPane.getActiveTab().update();
        this.repaint();
    }
}
