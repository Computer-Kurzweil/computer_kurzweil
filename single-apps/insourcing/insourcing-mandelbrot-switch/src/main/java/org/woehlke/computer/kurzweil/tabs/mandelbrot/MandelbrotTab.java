package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas.PanelCopyright;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas.PanelSubtitle;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.Point;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
public class MandelbrotTab extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible,
        WindowListener,
        MouseListener {

    private volatile MandelbrotController mandelbrotController;
    private volatile MandelbrotCanvas canvas;
    private volatile MandelbrotModel mandelbrotModel;
    private volatile Rectangle rectangleBounds;
    private volatile Dimension dimensionSize;

    public MandelbrotTab(ComputerKurzweilProperties properties) {
        super(properties.getMandelbrot().getView().getTitle());
        this.mandelbrotModel = new MandelbrotModel(properties,this);
        BoxLayout layout = new BoxLayout(rootPane, BoxLayout.PAGE_AXIS);
        this.canvas = new MandelbrotCanvas(mandelbrotModel);
        this.mandelbrotController = new MandelbrotController(mandelbrotModel, this);
        PanelSubtitle panelSubtitle = new PanelSubtitle(properties.getMandelbrot().getView().getSubtitle());
        PanelCopyright panelCopyright = new PanelCopyright(properties.getAllinone().getView().getCopyright());
        JSeparator separator = new JSeparator();
        rootPane.setLayout(layout);
        rootPane.add(panelSubtitle);
        rootPane.add(canvas);
        rootPane.add(panelCopyright);
        addWindowListener(this);
        this.canvas.addMouseListener(   this);
        showMeInit();
        setModeSwitch();
        this.mandelbrotController.start();
    }

    public void windowOpened(WindowEvent e) {
        showMe();
    }

    public void windowClosing(WindowEvent e) {
        this.mandelbrotController.exit();
    }

    public void windowClosed(WindowEvent e) {
        this.mandelbrotController.exit();
    }

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    public void windowActivated(WindowEvent e) {
        showMe();
    }

    public void windowDeactivated(WindowEvent e) {}


    @Override
    public void mouseClicked(MouseEvent e) {
        Point c = new Point(e.getX(), e.getY());
        this.mandelbrotModel.click(c);
        showMe();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void showMeInit() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = this.rootPane.getWidth();
        double height  = this.canvas.getHeight() + 90;
        double startX = (screenSize.getWidth() - width) / 2d;
        double startY = (screenSize.getHeight() - height) / 2d;
        int myheight = Double.valueOf(height).intValue();
        int mywidth = Double.valueOf(width).intValue();
        int mystartX = Double.valueOf(startX).intValue();
        int mystartY = Double.valueOf(startY).intValue();
        this.rectangleBounds = new Rectangle(mystartX, mystartY, mywidth, myheight);
        this.dimensionSize = new Dimension(mywidth, myheight);
        this.setBounds(this.rectangleBounds);
        this.setSize(this.dimensionSize);
        this.setPreferredSize(this.dimensionSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        toFront();
    }

    /**
     * TODO write doc.
     */
    public void showMe() {
        this.pack();
        this.setBounds(this.rectangleBounds);
        this.setSize(this.dimensionSize);
        this.setPreferredSize(this.dimensionSize);
        this.setVisible(true);
        this.toFront();
    }

    public void setModeSwitch() {
        canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    /*
    public void setModeZoom() {
        canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
*/
    public MandelbrotCanvas getCanvas() {
        return canvas;
    }
}
