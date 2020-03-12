package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.has.HasPanelSubtitle;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.commons.layouts.BoxLayoutVertical;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;
import org.woehlke.computer.kurzweil.commons.widgets.PanelCopyright;
import org.woehlke.computer.kurzweil.commons.widgets.PanelSubtitle;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

@Log4j2
@Getter
@ToString(callSuper=true)
public class ComputerKurzweilFrame extends JFrame implements Serializable,
    MenuContainer,
    ImageObserver,
    Accessible,
    WindowListener,
    WindowFocusListener,
    WindowStateListener,
    Startable,
    GuiComponent, HasPanelSubtitle {

    private final ComputerKurzweilContext ctx;
    private final ComputerKurzweilTabbedPane tabbedPane;
    private final ComputerKurzweilMenuBar jMenuBar;
    private final PanelCopyright panelCopyright;
    private final PanelSubtitle panelSubtitle;
    private final BoxLayoutVertical layout;
    private final CompoundBorder border;

    public ComputerKurzweilFrame(
        ComputerKurzweilProperties properties
    ) throws HeadlessException {
        //super(properties.getAllinone().getView().getTitle());
        setTitle(properties.getAllinone().getView().getTitle());
        this.ctx = new ComputerKurzweilContext(properties,this);
        panelSubtitle =  PanelSubtitle.getPanelSubtitleForApplication(this.ctx);
        tabbedPane = new ComputerKurzweilTabbedPane(this.ctx);
        panelCopyright = new PanelCopyright(this.ctx);
        border = this.ctx.getFrameBorder();
        //Border bo = new LineBorder(Color.red,10,true);
        jMenuBar = new ComputerKurzweilMenuBar(this.ctx);
        //jMenuBar.setBorder(bo);
        this.setJMenuBar(jMenuBar);
        Container contentPane = this.getContentPane();
        layout = new BoxLayoutVertical( contentPane );
        contentPane.add(panelSubtitle);
        contentPane.add(tabbedPane);
        contentPane.add(panelCopyright);
        //contentPane.setBorder(border);
        contentPane.setLayout(layout);
        this.setVisible(true);
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
