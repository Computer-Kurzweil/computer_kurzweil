package org.woehlke.computer.kurzweil.tabs.dla;

import org.woehlke.computer.kurzweil.tabs.TabType;

import java.io.Serializable;

import static org.woehlke.computer.kurzweil.tabs.TabType.DIFFUSION_LIMITED_AGGREGATION;

public interface DiffusionLimitedAggregation extends Serializable {

    TabType TAB_TYPE = DIFFUSION_LIMITED_AGGREGATION;
}
