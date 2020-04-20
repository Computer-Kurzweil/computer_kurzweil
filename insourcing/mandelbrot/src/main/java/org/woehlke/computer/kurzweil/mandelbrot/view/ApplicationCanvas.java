package org.woehlke.computer.kurzweil.mandelbrot.view;

import org.woehlke.computer.kurzweil.mandelbrot.model.ApplicationModel;

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

    private volatile ApplicationModel app;
    private volatile Dimension preferredSize;

    public ApplicationCanvas(ApplicationModel app) {
        this.app = app;
        int width = this.app.getWorldDimensions().getWidth();
        int height = this.app.getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(width, height);
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
    }

    public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
        super.paintComponent(g);
        int red = 0;
        int green = 0;
        int blue = 0;
        for(int y = 0; y < app.getWorldDimensions().getY(); y++){
            for(int x = 0; x < app.getWorldDimensions().getX(); x++){
                blue = (((app.getCellStatusFor(x,y))*4)%256);
                Color stateColor = new Color(red, green, blue);
                g.setColor(stateColor);
                g.drawLine(x,y,x,y);
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

}
