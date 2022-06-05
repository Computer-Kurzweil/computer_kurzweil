package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.config;

import org.woehlke.computer.kurzweil.tabs.TabType;

import java.io.Serializable;

import static org.woehlke.computer.kurzweil.tabs.TabType.MANDELBROT_SET;

public interface Mandelbrot extends Serializable {

    TabType TAB_TYPE = MANDELBROT_SET;
}
