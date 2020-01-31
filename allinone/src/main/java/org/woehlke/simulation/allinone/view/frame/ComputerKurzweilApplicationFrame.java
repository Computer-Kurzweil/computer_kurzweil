package org.woehlke.simulation.allinone.view.frame;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.common.Bounds;
import org.woehlke.simulation.allinone.view.common.PanelBorder;
import org.woehlke.simulation.allinone.view.common.PanelCopyright;
import org.woehlke.simulation.allinone.view.common.PanelSubtitle;

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
@ToString
@EqualsAndHashCode(callSuper=true)
public class ComputerKurzweilApplicationFrame extends JFrame implements Serializable,
    MenuContainer,
    ImageObserver,
    Accessible,
    WindowListener,
    WindowFocusListener,
    WindowStateListener {

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilApplicationContext ctx
    ) throws HeadlessException {
        super(ctx.getProperties().getAllinone().getView().getTitle());
        this.ctx = ctx;
        rootPane.setLayout(new ComputerKurzweilApplicationFrameLayout( rootPane ));
        rootPane.setBorder(PanelBorder.getBorder());
        rootPane.add(PanelSubtitle.getPanelSubtitleForAllinone(this.ctx));
        rootPane.add(new AppsTabbedPane(this.ctx));
        rootPane.add(new PanelCopyright(this.ctx));
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        showMe();
    }

    public void start(){log.info("started");}

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
}
