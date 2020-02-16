package org.woehlke.computer.kurzweil.model;

import lombok.Getter;

public enum LatticePointNeighbourhoodPosition {

    NORTH(0,-1),
    EAST(1,0),
    SOUTH(0,1),
    WEST(-1,0),
    NORTH_EAST(1,-1),
    SOUTH_EAST(1,1),
    SOUTH_WEST(-1,1),
    NORTH_WEST(-1,-1),
    CENTER(0,0);

    @Getter
    private final int x;

    @Getter
    private final int y;

    LatticePointNeighbourhoodPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static LatticePointNeighbourhoodPosition[] getNeighbourhoodFor(
        LatticeNeighbourhoodType neighbourhoodType
    ){
        LatticePointNeighbourhoodPosition[] result;
        switch (neighbourhoodType) {
            case VON_NEUMANN_NEIGHBORHOOD:
                result = new LatticePointNeighbourhoodPosition[4];
                result[0]=NORTH;
                result[1]=EAST;
                result[2]=SOUTH;
                result[3]=WEST;
                break;
            case MOORE_NEIGHBORHOOD:
                result = new LatticePointNeighbourhoodPosition[8];
                result[0]=NORTH_WEST;
                result[1]=NORTH;
                result[2]=NORTH_EAST;
                result[3]=EAST;
                result[4]=SOUTH_EAST;
                result[5]=SOUTH;
                result[6]=SOUTH_WEST;
                result[7]=WEST;
                break;
            case WOEHLKE_NEIGHBORHOOD:
                result = new LatticePointNeighbourhoodPosition[6];
                result[0]=NORTH_WEST;
                result[1]=NORTH;
                result[2]=NORTH_EAST;
                result[3]=EAST;
                result[4]=SOUTH_WEST;
                result[5]=WEST;
                break;
            default:
                result = new LatticePointNeighbourhoodPosition[0];
                break;
        }
        return result;
    }

}
