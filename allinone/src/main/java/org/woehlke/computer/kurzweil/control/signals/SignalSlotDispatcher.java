package org.woehlke.computer.kurzweil.control.signals;

import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.commons.Startable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SignalSlotDispatcher implements Startable, Stepper,  UserSlot {

    private final Map<UserSignal,List<UserSlot>> listenerMap = new TreeMap<>();

    public SignalSlotDispatcher() {
        for(UserSignal event: UserSignal.values()){
            List<UserSlot> listenerList = new ArrayList<>();
            listenerMap.put(event,listenerList);
        }
    }

    public void registerSignalAndSlot(UserSignal signal, UserSlot slot){
        List<UserSlot> slotList = listenerMap.get(signal);
        slotList.add(slot);
        listenerMap.put(signal,slotList);
    }

    public void registerSignalsAndSlots(UserSignal[] signals, UserSlot[] slots) {
        for(UserSignal signal :signals) {
            for(UserSlot slot: slots){
                List<UserSlot> slotList = listenerMap.get(signal);
                slotList.add(slot);
                listenerMap.put(signal,slotList);
            }
        }
    }

    public void clear(){
        for(UserSignal event: UserSignal.values()) {
            List<UserSlot> listenerList = listenerMap.get(event);
            listenerList.clear();
            listenerMap.put(event,listenerList);
        }
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        List<UserSlot> listenerList = listenerMap.get(userSignal);
        for(UserSlot listeners:listenerList){
            listeners.handleUserSignal(userSignal);
        }
    }

    @Override
    public void start() {
        handleUserSignal(UserSignal.START);
    }

    @Override
    public void stop() {
        handleUserSignal(UserSignal.STOP);
    }

    @Override
    public void step() {
        handleUserSignal(UserSignal.STEP);
    }
}
