package org.woehlke.computer.kurzweil.mandelbrot.julia.view.state;

import static org.woehlke.computer.kurzweil.mandelbrot.julia.view.state.ApplicationState.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * Created by tw on 16.12.2019.
 */
public class ApplicationStateMachine {

    private volatile ApplicationState applicationState;

    public ApplicationStateMachine() {
        this.applicationState = ApplicationState.MANDELBROT;
    }

    public void click(){
        ApplicationState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = JULIA_SET;
                break;
            case JULIA_SET:
                nextApplicationState = MANDELBROT;
                break;
        }
        this.setApplicationState(nextApplicationState);
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

}
