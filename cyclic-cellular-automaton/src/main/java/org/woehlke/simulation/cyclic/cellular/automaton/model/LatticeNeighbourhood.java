package org.woehlke.simulation.cyclic.cellular.automaton.model;

public enum LatticeNeighbourhood {

    /**
     * https://en.wikipedia.org/wiki/Von_Neumann_neighborhood
     */
    VON_NEUMANN_NEIGHBORHOOD,

    /**
     * https://en.wikipedia.org/wiki/Moore_neighborhood
     */
    MOORE_NEIGHBORHOOD,

    WOEHLKE_NEIGHBORHOOD;
}
