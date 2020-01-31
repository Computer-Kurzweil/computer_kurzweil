package org.woehlke.simulation.allinone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonColorScheme;
import org.woehlke.simulation.evolution.model.cell.CellCore;
import org.woehlke.simulation.evolution.model.cell.CellLifeCycle;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.beans.Transient;
import java.util.Date;
import java.util.Random;

@Log
@Component
@ToString
@EqualsAndHashCode
public class ComputerKurzweilApplicationContext {

    @Getter private final ComputerKurzweilProperties properties;

    @Getter private final CyclicCellularAutomatonColorScheme colorScheme;

    @Getter private final Random random;

    @Autowired
    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties computerKurzweilProperties
    ) {
        this.properties = computerKurzweilProperties;
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        long seed = new Date().getTime();
        this.random = new Random(seed);
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
            this.properties.getAllinone().getLattice().getWidth(),
            this.properties.getAllinone().getLattice().getHeight()
        );
    }

    @Transient
    public Point getLatticeDimensions(){
        return new Point(
            this.properties.getAllinone().getLattice().getWidth(),
            this.properties.getAllinone().getLattice().getHeight()
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
        int x = this.getRandom().nextInt(this.getProperties().getAllinone().getLattice().getWidth());
        int y = this.getRandom().nextInt(this.getProperties().getAllinone().getLattice().getHeight());
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
}
