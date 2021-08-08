package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.turing.Point;

import java.awt.*;
import java.awt.event.*;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class MandelbrotTab extends TabPanel implements Tab, Mandelbrot, MouseListener  {

    private static final long serialVersionUID = 7526471155622776147L;

    private final MandelbrotContext tabCtx;
    private final MandelbrotCanvas canvas;
    private final MandelbrotTabPane mandelbrotTabPane;
    private final MandelbrotModel mandelbrotModel;

    //private volatile MandelbrotController mandelbrotController;

    public MandelbrotTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new MandelbrotContext(this, ctx);
        this.canvas = this.tabCtx.getCanvas();
        this.mandelbrotModel = this.tabCtx.getTabModel();
        //this.mandelbrotController = this.tabCtx.getController();
        this.mandelbrotTabPane = new MandelbrotTabPane(this);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.mandelbrotTabPane);
        this.mandelbrotTabPane.stop();
        this.canvas.addMouseListener(      this);
        /*
        super(properties.getMandelbrot().getView().getTitle());

        BoxLayout layout = new BoxLayout(rootPane, BoxLayout.PAGE_AXIS);
        this.canvas = new MandelbrotCanvas(mandelbrotModel);
        this.mandelbrotController = new MandelbrotController(mandelbrotModel, this);

        JSeparator separator = new JSeparator();
        rootPane.setLayout(layout);
        rootPane.add(panelSubtitle);
        rootPane.add(canvas);
        rootPane.add(panelCopyright);
        rootPane.add(separator);
        rootPane.add(panelButtons);
        addWindowListener(this);
        this.canvas.addMouseListener(   this);
        showMeInit();
        setModeSwitch();
        this.mandelbrotController.start();
         */
    }

    @Override
    public void start() {
        log.info("start");
        this.mandelbrotTabPane.start();
        this.tabCtx.getTabModel().start();
        this.tabCtx.startController();
        this.tabCtx.getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("started with canvas x="+x+" y="+y);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabCtx.getTabModel().stop();
        this.mandelbrotTabPane.stop();
        this.tabCtx.stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.mandelbrotTabPane.getPanelChooseMouseClickMode().getRadioButtonsSwitch()){
            setModeSwitch();
        }
        if(ae.getSource() == this.mandelbrotTabPane.getPanelChooseMouseClickMode().getRadioButtonsZoom()){
            setModeZoom();
        }
        if(ae.getSource() == this.mandelbrotTabPane.getPanelZoom().getZoomOutButton()){
            this.mandelbrotModel.zoomOut();
        }
        if(ae.getSource() ==  this.mandelbrotTabPane.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() ==  this.mandelbrotTabPane.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }

    public void setModeSwitch() {
        this.mandelbrotModel.setModeSwitch();
        canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void setModeZoom() {
        this.mandelbrotModel.setModeZoom();
        canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point( e.getPoint().getX(), e.getPoint().getY());
        mandelbrotModel.click(  p  );
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
