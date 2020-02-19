package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.evolution.cell.CellCore;
import org.woehlke.computer.kurzweil.tabs.evolution.cell.CellLifeCycle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.beans.Transient;
import java.util.Date;
import java.util.Random;

@Log
@Getter
@ToString(exclude={"random","frame"},callSuper=true)
public class ComputerKurzweilApplicationContext implements Startable {

    private final Random random;
    private final ComputerKurzweilProperties properties;
    private final ComputerKurzweilApplicationFrame frame;

    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties computerKurzweilProperties,
        ComputerKurzweilApplicationFrame frame
    ) {
        this.frame = frame;
        this.properties = computerKurzweilProperties;
        long seed = new Date().getTime();
        this.random = new Random(seed);
    }

    @Transient
    public void exit() {
        System.exit(0);
    }

    @Transient
    public CompoundBorder getBorder(){
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(),
            BorderFactory.createEmptyBorder(left,right,top,bottom)
        );
    }

    @Transient
    public CompoundBorder getBorder(String label){
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    @Transient
    public CompoundBorder getFrameBorder(){
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(),
            BorderFactory.createEmptyBorder(left,right,top,bottom)
        );
    }

    @Transient
    public CompoundBorder getBottomButtonsPanelBorder(){
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(left,right,top,bottom),
            BorderFactory.createEmptyBorder(left,right,top,bottom)
        );
    }

    @Transient
    public CompoundBorder getBottomButtonsPanelBorder(String label){
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    @Transient
    public Border getTabBorder() {
        int top = 0;
        int left = 0;
        int bottom = 0;
        int right = 0;
        return BorderFactory.createEmptyBorder(top,left,bottom,right);
    }

    @Transient
    public Border getCanvasBorder() {
        int top = 0;
        int left = 0;
        int bottom = 0;
        int right = 0;
        return BorderFactory.createEmptyBorder(top,left,bottom,right);
    }

    @Transient
    public LatticePoint getWorldDimensions(){
        int x = this.properties.getAllinone().getLattice().getWidth();
        int y = this.properties.getAllinone().getLattice().getHeight();
        return new LatticePoint(x,y);
    }

    @Transient
    public LatticePoint getNextRandomLatticePoint() {
        int x = this.properties.getAllinone().getLattice().getWidth();
        int y = this.properties.getAllinone().getLattice().getHeight();
        int nextX = this.getRandom().nextInt(x);
        int nextY = this.getRandom().nextInt(y);
        LatticePoint p = new LatticePoint(nextX,nextY);
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
        frame.start();
    }

    @Override
    public void stop() {
        frame.stop();
    }

}
