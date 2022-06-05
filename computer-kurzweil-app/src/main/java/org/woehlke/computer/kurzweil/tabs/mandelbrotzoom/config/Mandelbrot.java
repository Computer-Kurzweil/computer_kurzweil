package org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.config;

import org.woehlke.computer.kurzweil.tabs.TabType;

import java.io.Serializable;

import static org.woehlke.computer.kurzweil.tabs.TabType.MANDELBROT_SET;

public interface Mandelbrot extends Serializable {

    TabType TAB_TYPE = MANDELBROT_SET;
}
