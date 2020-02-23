package org.woehlke.computer.kurzweil.tabs.randomwalk;

import org.woehlke.computer.kurzweil.commons.model.LatticePoint;

public enum ParticleOrientation {

    UP(0, -1),
    UP_RIGHT(1, -1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN(0, 1),
    DOWN_LEFT(-1, 1),
    LEFT(-1, 0),
    UP_LEFT(-1, -1);

    private LatticePoint move;

    public LatticePoint getMove() {
        return move;
    }

    ParticleOrientation(int x, int y) {
        move = new LatticePoint(x, y);
    }
}
