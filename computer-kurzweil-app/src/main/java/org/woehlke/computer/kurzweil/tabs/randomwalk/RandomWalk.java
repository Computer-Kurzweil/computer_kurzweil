package org.woehlke.computer.kurzweil.tabs.randomwalk;

import org.woehlke.computer.kurzweil.tabs.TabType;

import java.io.Serializable;

import static org.woehlke.computer.kurzweil.tabs.TabType.RANDOM_WALK_WIENER_PROCESS;

public interface RandomWalk extends Serializable {

    TabType TAB_TYPE = RANDOM_WALK_WIENER_PROCESS;
}
