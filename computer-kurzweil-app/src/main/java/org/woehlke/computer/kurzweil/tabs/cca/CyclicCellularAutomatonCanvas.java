package org.woehlke.computer.kurzweil.tabs.cca;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.commons.layouts.LayoutCanvas;
import org.woehlke.computer.kurzweil.tabs.cca.canvas.CyclicCellularAutomatonColorScheme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme","cyclicCellularAutomatonModel"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme","cyclicCellularAutomatonModel"})
public class CyclicCellularAutomatonCanvas extends JComponent implements
    Serializable, TabCanvas, CyclicCellularAutomaton, Future<Void> {

    private static final long serialVersionUID = 7526471155622776147L;

    private final CyclicCellularAutomatonContext tabCtx;
    private final Border border;
    private final Dimension preferredSize;
    private final LayoutCanvas layout;
    private final CyclicCellularAutomatonColorScheme colorScheme;
    private final CyclicCellularAutomatonModel cyclicCellularAutomatonModel;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public CyclicCellularAutomatonCanvas(CyclicCellularAutomatonContext tabCtx) {
        this.tabCtx = tabCtx;
        this.border = this.tabCtx.getCtx().getCanvasBorder();
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.layout = new LayoutCanvas(this);
        this.preferredSize = new Dimension(worldX,worldY);
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.setLayout(layout);
        this.setPreferredSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setSize(this.worldX,this.worldY);
        this.cyclicCellularAutomatonModel = new CyclicCellularAutomatonModel(this.tabCtx);
        showMe();
    }

    public void paint(Graphics g) {
        //log.info("paint START (Graphics g)");
        int x;
        int y;
        int state;
        Color stateColor;
        if (this.cyclicCellularAutomatonModel.getLattice() != null) {
            for (y = 0; y < worldY; y++) {
                for (x = 0; x < worldX; x++) {
                    state = this.cyclicCellularAutomatonModel.getState(x,y);
                    stateColor = this.colorScheme.getColorForState(state);
                    g.setColor(stateColor);
                    g.drawLine(x, y, x, y);
                }
            }
        }
        super.paintComponent(g);
        //log.info("paint DONE (Graphics g)");
    }

    public void update(Graphics g) {
        //log.info("update(Graphics g)");
        paint(g);
    }

    @Override
    public void showMe() {
        log.info("showMe "+this.toString());
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Void get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
