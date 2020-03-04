package org.woehlke.computer.kurzweil.tabs.todo.gameoflive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.layouts.LayoutCanvas;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme"})
public class ConwaysGameOfLifeCanvas extends JComponent implements TabCanvas, TabModel {

    private final ConwaysGameOfLifeContext tabCtx;
    private final Border border;
    private final Dimension preferredSize;
    private final LayoutCanvas layout;
    private final ConwaysGameOfLifeColorScheme colorScheme;
    private final int initialNumberOfParticles;
    private List<LatticePoint> particles = new ArrayList<>();

    private final int versions;
    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    private volatile int[][][] lattice;
    private volatile int source;
    private volatile int target;
    private Boolean running;
    private int age = 1;
    private long steps;

    public ConwaysGameOfLifeCanvas(ConwaysGameOfLifeContext tabCtx) {
        this.tabCtx = tabCtx;
        this.border = this.tabCtx.getCtx().getCanvasBorder();
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.layout = new LayoutCanvas(this);
        this.preferredSize = new Dimension(worldX,worldY);
        this.versions = 2;
        this.colorScheme = new ConwaysGameOfLifeColorScheme();
        this.initialNumberOfParticles = this.tabCtx.getCtx().getProperties().getDla().getControl().getNumberOfParticles();
        this.setLayout(layout);
        this.setPreferredSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setSize(this.worldX,this.worldY);
        this.resetLattice();
        this.running = Boolean.FALSE;
        showMe();
    }

    private void initCreateLattice(){
        log.info("initCreateLattice start: ");
        lattice = new int[versions][worldX][worldY];
        source = 0;
        target = 1;
        log.info("initCreateLattice finished: ");
    }

    private void initFillLattice(){
        log.info("initCreateLattice start: ");
        Random random = this.tabCtx.getCtx().getRandom();
        int maxState = this.colorScheme.getMaxState();
        int y;
        int x;
        for (y = 0; y < worldY; y++) {
            for (x = 0; x < worldX; x++) {
                lattice[source][x][y] = this.colorScheme.getBackgroundColorState();
            }
        }
        log.info("initCreateLattice finished: ");
    }

    public void resetLattice(){
        initCreateLattice();
        initFillLattice();
    }

    public void paint(Graphics g) {
        //log.info("paint START (Graphics g)");
        int x;
        int y;
        int state;
        Color stateColor;
        if (lattice != null) {
            for (y = 0; y < worldY; y++) {
                for (x = 0; x < worldX; x++) {
                    state = this.lattice[source][x][y];
                    stateColor = this.colorScheme.getColorForState(state);
                    g.setColor(stateColor);
                    g.drawLine(x, y, x, y);
                }
            }
        }
        super.paintComponent(g);
        //log.info("paint DONE (Graphics g)");
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void showMe() {

    }

    @Override
    public void step() {

    }

    @Override
    public void start() {
        log.info("start");
        showMe();
        synchronized (running) {
            running = Boolean.TRUE;
        }
        log.info("started "+this.toString());
    }

    @Override
    public void stop() {
        log.info("stop");
        synchronized (running) {
            running = Boolean.FALSE;
        }
        log.info("stopped "+this.toString());
    }

    @Override
    public void update(){
        //log.info("update");
    }
}
