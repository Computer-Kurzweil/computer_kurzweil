package org.woehlke.computer.kurzweil.commons.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import java.io.Serializable;

@Log
@Getter
@ToString
@EqualsAndHashCode
public class LatticeNeighbourhood implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final LatticeNeighbourhoodType neighbourhoodType;
    private final int maxX;
    private final int maxY;
    private final int x;
    private final int y;

    private LatticePoint[] neighbourhood;

    public LatticeNeighbourhood(
        int maxX,  int maxY, int x,  int y,
        LatticeNeighbourhoodType neighbourhoodType
    ) {
        this.neighbourhoodType = neighbourhoodType;
        this.maxX = maxX;
        this.maxY = maxY;
        this.x = x;
        this.y = y;
        this.neighbourhood = getNeighbourhoodPoints();
    }

    /**
     * Get Neighbourhood.
     *
     * @return The Set of Points belonging to the Neighbourhood of the position given by this Point Object.
     */
    private LatticePoint[] getNeighbourhoodPoints() {
        LatticePointNeighbourhoodPosition[] positions = LatticePointNeighbourhoodPosition.getNeighbourhoodFor(neighbourhoodType);
        this.neighbourhood = new LatticePoint[positions.length];
        for(int i = 0; i < positions.length; i++){
            this.neighbourhood[i] = new LatticePoint(
                (x + maxX + positions[i].getX()) % maxX,
                (y + maxY + positions[i].getY()) % maxY
            );
        }
        return this.neighbourhood;
    }

    public static LatticePoint[] get(int worldX, int worldY, int myX, int myY) {
        LatticeNeighbourhoodType neighbourhoodType = LatticeNeighbourhoodType.MOORE_NEIGHBORHOOD;
        LatticeNeighbourhood n = new LatticeNeighbourhood(worldX, worldY, myX, myY, neighbourhoodType);
        return n.getNeighbourhoodPoints();
    }

    public static LatticePoint[] getMoore(int worldX, int worldY, int myX, int myY) {
        LatticeNeighbourhoodType neighbourhoodType = LatticeNeighbourhoodType.MOORE_NEIGHBORHOOD;
        LatticeNeighbourhood n = new LatticeNeighbourhood(worldX, worldY, myX, myY, neighbourhoodType);
        return n.getNeighbourhoodPoints();
    }

    public static LatticePoint[] getVonNeumann(int worldX, int worldY, int myX, int myY) {
        LatticeNeighbourhoodType neighbourhoodType = LatticeNeighbourhoodType.VON_NEUMANN_NEIGHBORHOOD;
        LatticeNeighbourhood n = new LatticeNeighbourhood(worldX, worldY, myX, myY, neighbourhoodType);
        return n.getNeighbourhoodPoints();
    }

    public static LatticePoint[] getWoehlke(int worldX, int worldY, int myX, int myY) {
        LatticeNeighbourhoodType neighbourhoodType = LatticeNeighbourhoodType.WOEHLKE_NEIGHBORHOOD;
        LatticeNeighbourhood n = new LatticeNeighbourhood(worldX, worldY, myX, myY, neighbourhoodType);
        return n.getNeighbourhoodPoints();
    }

}
