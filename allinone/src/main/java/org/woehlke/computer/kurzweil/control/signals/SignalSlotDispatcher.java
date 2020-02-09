package org.woehlke.computer.kurzweil.control.signals;

import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;

public interface SignalSlotDispatcher extends Stepper, UserSlot {

    void registerSignalAndSlot(UserSignal signal, UserSlot slot);
    void registerSignalsAndSlots(UserSignal[] signals, UserSlot[] slots);
    void clear();
}
