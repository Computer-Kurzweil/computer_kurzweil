package org.woehlke.computer.kurzweil.control.events;

import org.woehlke.computer.kurzweil.control.startables.Startable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SignalSlotDispatcher implements Startable, UserSlot {

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

    public void registerSignalAndSlots(UserSignal signal, UserSlot...slotsToAdd){
        List<UserSlot> listenerList = listenerMap.get(signal);
        for(UserSlot slotToAdd:slotsToAdd){
            listenerList.add(slotToAdd);
        }
        listenerMap.put(signal,listenerList);
    }


    public void registerSignalsAndSlots(UserSignal[] signals, UserSlot[] slots) {
        for(UserSignal signal :signals) {
            List<UserSlot> listenerList = listenerMap.get(signal);
            for (UserSlot slotToAdd : slots) {
                listenerList.add(slotToAdd);
            }
            listenerMap.put(signal, listenerList);
        }
    }

    public void clearSignalAndSlots(){
        for(UserSignal event: UserSignal.values()) {
            List<UserSlot> listenerList = listenerMap.get(event);
            listenerList.clear();
            listenerMap.put(event,listenerList);
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
    public void update() {
        handleUserSignal(UserSignal.UPDATE);
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        List<UserSlot> listenerList = listenerMap.get(userSignal);
        for(UserSlot listeners:listenerList){
            listeners.handleUserSignal(userSignal);
        }
    }
}
