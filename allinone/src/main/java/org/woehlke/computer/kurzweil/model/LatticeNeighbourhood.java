package org.woehlke.computer.kurzweil.model;

import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

public class LatticeNeighbourhood {

    private LatticePoint latticePoint;

    private LatticePoint[] neighbourhood;

    private LatticeNeighbourhoodType neighbourhoodType;

    private final LatticePoint max;

    public LatticeNeighbourhood(
        LatticePoint latticePoint,
        LatticeNeighbourhoodType neighbourhoodType,
        ComputerKurzweilApplicationContext ctx
    ) {
        this.latticePoint = latticePoint;
        this.neighbourhoodType = neighbourhoodType;
        this.max = ctx.getWorldDimensions();
        this.neighbourhood = getNeighbourhood();
    }

    /**
     * Get Neighbourhood.
     *
     * @return The Set of Points belonging to the Neighbourhood of the position given by this Point Object.
     */
    private LatticePoint[] getNeighbourhood() {
        LatticePoint[] neighbourhood = new LatticePoint[9];
        int maxX = max.getX();
        int maxY = max.getY();
        int x = latticePoint.getX();
        int y = latticePoint.getY();
        neighbourhood[0] = new LatticePoint((x + maxX - 1) % maxX, (y + maxY - 1) % maxY);
        neighbourhood[1] = new LatticePoint((x + maxX - 1) % maxX, y);
        neighbourhood[2] = new LatticePoint((x + maxX - 1) % maxX, (y + maxY + 1) % maxY);
        neighbourhood[3] = new LatticePoint(x, (y + maxY - 1) % maxY);
        neighbourhood[4] = new LatticePoint(x, y);
        neighbourhood[5] = new LatticePoint(x, (y + maxY + 1) % maxY);
        neighbourhood[6] = new LatticePoint((x + maxX + 1) % maxX, (y + maxY - 1) % maxY);
        neighbourhood[7] = new LatticePoint((x + maxX + 1) % maxX, y);
        neighbourhood[8] = new LatticePoint((x + maxX + 1) % maxX, (y + maxY + 1) % maxY);
        return neighbourhood;
    }
}
