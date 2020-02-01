package org.woehlke.computer.kurzweil.view.frame;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.events.UserSignal;
import org.woehlke.computer.kurzweil.control.events.UserSlot;
import org.woehlke.computer.kurzweil.control.startables.Startable;
import org.woehlke.computer.kurzweil.model.Bounds;
import org.woehlke.computer.kurzweil.view.common.PanelBorder;
import org.woehlke.computer.kurzweil.view.common.PanelCopyright;
import org.woehlke.computer.kurzweil.view.common.PanelSubtitle;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
public class ComputerKurzweilApplicationFrame extends JFrame implements Serializable,
    MenuContainer,
    ImageObserver,
    Accessible,
    WindowListener,
    WindowFocusListener,
    WindowStateListener, Startable, UserSlot {

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final ComputerKurzweilApplicationTabbedPane a;

    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilApplicationContext ctx
    ) throws HeadlessException {
        super(ctx.getProperties().getAllinone().getView().getTitle());
        this.ctx = ctx;
        this.ctx.setFrame(this);
        a = new ComputerKurzweilApplicationTabbedPane(this.ctx);
        rootPane.setLayout(new ComputerKurzweilApplicationFrameLayout( rootPane ));
        rootPane.setBorder(PanelBorder.getBorder());
        rootPane.add(PanelSubtitle.getPanelSubtitleForAllinone(this.ctx));
        rootPane.add(a);
        rootPane.add(new PanelCopyright(this.ctx));
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        showMe();
    }

    @Override
    public void start(){
        log.info("start");
        a.start();
        showMe();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        a.stop();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
        a.update();
        log.info("updated");
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
        rootPane.setVisible(true);
        this.setVisible(true);
        toFront();
        repaint();
    }

    public void exit() {
        this.dispose();
        System.exit(0);
    }

    public void repaint(){
        super.repaint();
        a.repaint();
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
}
