package org.woehlke.computer.kurzweil.trashcan.signals;

@Deprecated
public enum UserSignal {

    START,
    STOP,
    UPDATE,
    START_THREAD,
    STOP_THREAD,
    STEP;

    public static UserSignal[] getStartables(){
        UserSignal[] e = { START, STOP };
        return e;
    }

    public static UserSignal[] getControllerThreads(){
        UserSignal[] e = {  START_THREAD, STOP_THREAD };
        return e;
    }

    public static UserSignal[] getGui(){
        UserSignal[] e = {  START, STOP, UPDATE };
        return e;
    }

    public static UserSignal[] getModels(){
        UserSignal[] e = {  START, STOP, STEP };
        return e;
    }
}
