package org.woehlke.computer.kurzweil.control.commons;

import javax.swing.*;

public abstract class StartablePanel extends JPanel implements Startable {

    public abstract void start();
    public abstract void stop();

}
