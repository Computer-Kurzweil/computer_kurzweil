package org.woehlke.simulation.allinone.model.lattice;

import lombok.Getter;
import org.woehlke.simulation.allinone.model.lattice.LatticeNeighbourhoodType;

public enum LatticePointNeighbourhoodPosition {

    NORTH(true, false),
    EAST(true, true),
    SOUTH(true, true),
    WEST(true,  true),
    NORTH_EAST(false, true),
    SOUTH_EAST(false, true),
    SOUTH_WEST(false, true),
    NORTH_WEST(false, false),
    CENTER(true, true);

    @Getter
    private final boolean partOfNeighbourhoodVonNeumann;

    @Getter
    private final boolean partOfNeighbourhoodMoore;

    @Getter
    private final boolean partOfNeighbourhoodWoehlke;

    LatticePointNeighbourhoodPosition(
        boolean partOfNeighbourhoodVonNeumann,
        boolean partOfNeighbourhoodWoehlke
    ){
        this.partOfNeighbourhoodVonNeumann = partOfNeighbourhoodVonNeumann;
        this.partOfNeighbourhoodMoore = true;
        this.partOfNeighbourhoodWoehlke = partOfNeighbourhoodWoehlke;
    }

    public boolean isPartOfNeighbourhood(LatticeNeighbourhoodType neighbourhoodType){
        switch (neighbourhoodType){
            case VON_NEUMANN_NEIGHBORHOOD: return partOfNeighbourhoodVonNeumann;
            case MOORE_NEIGHBORHOOD: return partOfNeighbourhoodMoore;
            case WOEHLKE_NEIGHBORHOOD: return partOfNeighbourhoodWoehlke;
            default: return false;
        }
    }
}
