package org.woehlke.computer.kurzweil.trashcan.signals;

import org.woehlke.computer.kurzweil.commons.Stepper;

@Deprecated
public interface SignalSlotDispatcher extends Stepper, UserSlot {

    void registerSignalAndSlot(UserSignal signal, UserSlot slot);
    void registerSignalsAndSlots(UserSignal[] signals, UserSlot[] slots);
    void clear();
}
