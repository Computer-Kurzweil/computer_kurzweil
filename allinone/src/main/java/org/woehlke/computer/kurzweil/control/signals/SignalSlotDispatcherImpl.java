package org.woehlke.computer.kurzweil.control.signals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SignalSlotDispatcherImpl implements SignalSlotDispatcher {

    private final Map<UserSignal,List<UserSlot>> listenerMap = new TreeMap<>();

    public SignalSlotDispatcherImpl() {
        for(UserSignal event: UserSignal.values()){
            List<UserSlot> listenerList = new ArrayList<>();
            listenerMap.put(event,listenerList);
        }
    }

    @Override
    public void registerSignalAndSlot(UserSignal signal, UserSlot slot){
        List<UserSlot> slotList = listenerMap.get(signal);
        slotList.add(slot);
        listenerMap.put(signal,slotList);
    }

    @Override
    public void registerSignalsAndSlots(UserSignal[] signals, UserSlot[] slots) {
        for(UserSignal signal :signals) {
            for(UserSlot slot: slots){
                List<UserSlot> slotList = listenerMap.get(signal);
                slotList.add(slot);
                listenerMap.put(signal,slotList);
            }
        }
    }

    @Override
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

    @Override
    public void update() {
        handleUserSignal(UserSignal.UPDATE);
    }
}
