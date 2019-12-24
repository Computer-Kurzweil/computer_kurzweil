package org.woehlke.simulation.mandelbrot.view;

import org.woehlke.simulation.mandelbrot.control.ApplicationContext;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;


import javax.swing.*;
import java.awt.*;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
public class ApplicationCanvas extends JComponent {

    private final ApplicationContext model;
    private final Dimension preferredSize;

    public ApplicationCanvas(ApplicationContext model) {
        this.model = model;
        int width = this.model.getWorldDimensions().getWidth();
        int height = this.model.getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(width, height);
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
    }

    @Override public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
        super.paintComponent(g);
        int red = 0;
        int green = 0;
        int blue = 0;
        CellStatus cellStatus;
        for(int y = 0; y < model.getWorldDimensions().getY(); y++){
            for(int x = 0; x < model.getWorldDimensions().getX(); x++){
                cellStatus = model.getCellStatusFor(x,y);
                if(cellStatus.isCellStatusForYetUncomputed()){
                    blue = 0;
                } else {
                    blue = cellStatus.intValue();
                }
                Color stateColor = new Color(red, green, blue);
                g.setColor(stateColor);
                g.drawLine(x,y,x,y);
            }
        }
    }

    @Override public void update(Graphics g) {
        paint(g);
    }

    public double getCanvasHeight() { return preferredSize.getHeight(); }
}
