package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.BoxLayoutVertical;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.widgets.PanelCopyright;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.ImageObserver;
import java.beans.Transient;
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
    private final PanelCopyright panelCopyright;
    private final PanelSubtitle panelSubtitle;
    private final BoxLayoutVertical layout;
    private final CompoundBorder border;

    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilProperties properties
    ) throws HeadlessException {
        super(properties.getAllinone().getView().getTitle());
        this.ctx = new ComputerKurzweilApplicationContext(properties,this);
        panelSubtitle =  PanelSubtitle.getPanelSubtitleForApplication(this.ctx);
        tabbedPane = new ComputerKurzweilApplicationTabbedPane(this.ctx);
        panelCopyright = new PanelCopyright(this.ctx);
        layout = new BoxLayoutVertical( rootPane );
        border = this.ctx.getFrameBorder();
        rootPane.setLayout(layout);
        rootPane.setBorder(border);
        rootPane.add(panelSubtitle);
        rootPane.add(tabbedPane);
        rootPane.add(panelCopyright);
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
        Rectangle r = this.getFrameBounds();
        this.setBounds(r);
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

    @Transient
    public Rectangle getFrameBounds(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        /*
        int paddingX = this.ctx.getProperties().getAllinone().getView().getBorderPaddingX();
        int paddingY = this.ctx.getProperties().getAllinone().getView().getBorderPaddingY();
        double x = tabbedPane.getWidth()+paddingX;
        double y = tabbedPane.getHeight()+panelSubtitle.getHeight()+panelCopyright.getHeight()+paddingY;
         */
        double x = this.getWidth();
        double y = this.getHeight();
        double startX = 0d;
        double startY = 0d;
        double twoParts = 2d;
        if(x < screenSize.getWidth()){
            startX = (screenSize.getWidth() - x ) / twoParts;
        }
        if(y < screenSize.getHeight()){
            startY = (screenSize.getHeight() - y) / twoParts;
        }
        int myheight = Double.valueOf( y ).intValue();
        int mywidth = Double.valueOf( x ).intValue();
        int mystartX = Double.valueOf( startX ).intValue();
        int mystartY = Double.valueOf( startY ).intValue();
        return new Rectangle( mystartX, mystartY, mywidth, myheight );
    }

}
