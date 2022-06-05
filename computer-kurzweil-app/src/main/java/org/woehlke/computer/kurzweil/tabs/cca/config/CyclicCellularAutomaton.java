package org.woehlke.computer.kurzweil.tabs.cca.config;

import org.woehlke.computer.kurzweil.tabs.TabType;

import java.io.Serializable;

import static org.woehlke.computer.kurzweil.tabs.TabType.CYCLIC_CELLULAR_AUTOMATON;

public interface CyclicCellularAutomaton extends Serializable {

    TabType TAB_TYPE = CYCLIC_CELLULAR_AUTOMATON;
}
