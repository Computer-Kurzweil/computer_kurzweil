package org.woehlke.computer.kurzweil.control.events;

public enum UserSignal {

    START,
    STOP,
    UPDATE,
    START_THREAD,
    STOP_THREAD,
    STEP;

    public UserSignal[] getStartables(){
        UserSignal[] e = { START, STOP, UPDATE};
        return e;
    }

    public UserSignal[] getControllerThreads(){
        UserSignal[] e = {  START_THREAD, STOP_THREAD, STEP };
        return e;
    }

    public UserSignal[] getCamvas(){
        UserSignal[] e = {  UPDATE, STEP };
        return e;
    }
}
