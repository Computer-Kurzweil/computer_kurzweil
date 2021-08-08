package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellCore;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellLifeCycle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Log
@Getter
@ToString(exclude={"random","frame"},callSuper=true)
public class ComputerKurzweilContext implements Startable, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final Random random;
    private final ComputerKurzweilProperties properties;
    private final ComputerKurzweilFrame frame;

    public ComputerKurzweilContext(
        ComputerKurzweilProperties computerKurzweilProperties,
        ComputerKurzweilFrame frame
    ) {
        this.frame = frame;
        this.properties = computerKurzweilProperties;
        long seed = new Date().getTime();
        this.random = new Random(seed);
    }

    public CompoundBorder getTabbedPaneBorder() {
        return getBorder();
    }

    public CompoundBorder getFrameBorder(){
        return getBorder();
    }

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

    private CompoundBorder getDoubleBorder(){
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(left,right,top,bottom),
            BorderFactory.createEmptyBorder(left,right,top,bottom)
        );
    }

    private CompoundBorder getDoubleBorder(String label){
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(left,right,top,bottom),
            BorderFactory.createEmptyBorder(left,right,top,bottom)
        );
    }

    public CompoundBorder getBottomButtonsPanelBorder(){
        return getDoubleBorder();
    }

    public CompoundBorder getBottomButtonsPanelBorder(String label){
        return getDoubleBorder(label);
    }

    private Border getZeroBorder() {
        int top = 0;
        int left = 0;
        int bottom = 0;
        int right = 0;
        return BorderFactory.createEmptyBorder(top,left,bottom,right);
    }

    public Border getTabBorder() {
        return getZeroBorder();
    }

    public Border getCanvasBorder() {
        return getZeroBorder();
    }

    public LatticePoint getWorldDimensions(){
        int x = this.properties.getAllinone().getLattice().getWidth();
        int y = this.properties.getAllinone().getLattice().getHeight();
        return new LatticePoint(x,y);
    }

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

    public CellLifeCycle getNewCellLifeCycle() {
        return new CellLifeCycle(this.properties.getSimulatedevolution().getCellConf());
    }

    public CellCore getNewCellCore() {
        return new CellCore(this);
    }

    public void start() {
        frame.start();
    }

    public void stop() {
        frame.stop();
    }

}
