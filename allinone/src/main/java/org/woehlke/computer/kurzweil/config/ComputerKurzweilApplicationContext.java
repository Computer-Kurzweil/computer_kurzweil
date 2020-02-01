package org.woehlke.computer.kurzweil.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.computer.kurzweil.control.events.UserSignal;
import org.woehlke.computer.kurzweil.control.events.UserSlot;
import org.woehlke.computer.kurzweil.control.events.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.cca.model.CyclicCellularAutomatonColorScheme;
import org.woehlke.computer.kurzweil.apps.evolution.model.cell.CellCore;
import org.woehlke.computer.kurzweil.apps.evolution.model.cell.CellLifeCycle;
import org.woehlke.computer.kurzweil.control.startables.Startable;
import org.woehlke.computer.kurzweil.view.frame.ComputerKurzweilApplicationFrame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.beans.Transient;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@Log
@Component
public class ComputerKurzweilApplicationContext implements Startable {

    @Getter private final ComputerKurzweilProperties properties;

    @Getter private final CyclicCellularAutomatonColorScheme colorScheme;

    @Getter private final Random random;

    private final Map<TabApps, SignalSlotDispatcher> signalSlotContainer = new TreeMap<>();

    @Getter @Setter
    private ComputerKurzweilApplicationFrame frame;

    @Autowired
    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties computerKurzweilProperties
    ) {
        this.properties = computerKurzweilProperties;
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        long seed = new Date().getTime();
        this.random = new Random(seed);
        initSignalSlotContainer();
    }

    public void initSignalSlotContainer(){
        for(TabApps app :TabApps.values()){
            SignalSlotDispatcher c = new SignalSlotDispatcher();
            signalSlotContainer.put(app,c);
        }
    }

    public void registerSignalsAndSlots(TabApps app, UserSignal[] signals, UserSlot[] slots) {
        SignalSlotDispatcher c  = signalSlotContainer.get(app);
        c.registerSignalsAndSlots(signals,slots);
        signalSlotContainer.put(app,c);
    }

    public void sendSignal(TabApps app, UserSignal signal) {
        SignalSlotDispatcher c  = signalSlotContainer.get(app);
        c.handleUserSignal(signal);
    }

    @Transient
    public void exit() {
        System.exit(0);
    }

    @Transient
    public CompoundBorder getBorder(String label){
        int top = this.getProperties().getAllinone().getView().getBorderPadding();
        int left = top;
        int bottom = top;
        int right = top;
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    @Transient
    public Rectangle getFrameBounds(){
        double twoOfFiveParts = 2d;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double startX = (screenSize.getWidth() -  this.properties.getAllinone().getLattice().getWidth()) / twoOfFiveParts;
        double startY = (screenSize.getHeight() - this.properties.getAllinone().getLattice().getWidth()) / twoOfFiveParts;
        int myheight = Double.valueOf( this.properties.getAllinone().getLattice().getHeight() ).intValue()
            + this.properties.getAllinone().getView().getTitleHeight();
        int mywidth = Double.valueOf( this.properties.getAllinone().getLattice().getWidth() ).intValue();
        int mystartX = Double.valueOf( startX ).intValue();
        int mystartY = Double.valueOf( startY ).intValue();
        return new Rectangle(
            mystartX, mystartY,
            mywidth, myheight
        );
    }

    @Transient
    public Rectangle getCanvasBounds(){
        int start=0;
        return new Rectangle(
            start, start,
            this.getWorldDimensions().getX(),
            this.getWorldDimensions().getY()
        );
    }

    @Transient
    public Dimension getLatticeDimension(){
        return new Dimension(
            this.getWorldDimensions().getX(),
            this.getWorldDimensions().getY()
        );
    }

    @Transient
    public Point getLatticeDimensions(){
        return new Point(
            this.getWorldDimensions().getX(),
            this.getWorldDimensions().getY()
        );
    }

    @Transient
    public LatticePoint getWorldDimensions(){
        return new LatticePoint(
            this.properties.getAllinone().getLattice().getWidth(),
            this.properties.getAllinone().getLattice().getHeight()
        );
    }

    @Transient
    public LatticePoint getNextRandomLatticePoint() {
        int x = this.getRandom().nextInt(this.getWorldDimensions().getWidth());
        int y = this.getRandom().nextInt(this.getWorldDimensions().getHeight());
        LatticePoint p = new LatticePoint(x,y);
        p.normalize(this.getWorldDimensions());
        p.absoluteValue();
        return p;
    }

    @Transient
    public CellLifeCycle getNewCellLifeCycle() {
        return new CellLifeCycle(this.properties.getEvolution().getCellConf());
    }

    @Transient
    public CellCore getNewCellCore() {
        return new CellCore(this);
    }

    @Override
    public void start() {
        this.frame.start();
    }

    @Override
    public void stop() {
        this.frame.start();
    }

    @Override
    public void update() {
        if(this.frame!=null){
            this.frame.update();
        }
    }
}
