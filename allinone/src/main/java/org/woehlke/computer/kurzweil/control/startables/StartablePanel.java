package org.woehlke.computer.kurzweil.control.startables;

import org.woehlke.computer.kurzweil.control.startables.Startable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class StartablePanel extends JPanel implements Startable {

    private final List<Startable> startables = new ArrayList<>();

    protected void registerStartable(Startable startable){
        startables.add(startable);
    }

    protected void registerStartables(Startable... startablesToAdd){
        for(Startable startable:startablesToAdd){
            startables.add(startable);
        }
    }

    protected void clearStartables(){
        startables.clear();
    }

    @Override
    public void start() {
        for(Startable startable:startables){
            startable.start();
        }
    }

    @Override
    public void stop() {
        for(Startable startable:startables){
            startable.stop();
        }
    }

    @Override
    public void update() {
        for(Startable startable:startables){
            startable.update();
        }
    }

}
