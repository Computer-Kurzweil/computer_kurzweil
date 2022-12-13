package org.woehlke.computer.kurzweil.mandelbrot.model.turing;



import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.woehlke.computer.kurzweil.mandelbrot.model.turing.TuringPhase.*;

public class TuringTuringPhaseStateEnumTest {

    private TuringPhaseState turingPhaseState = new TuringPhaseState();

    public static Logger log = Logger.getLogger(TuringPositionsTest.class.getName());

    @Test
    public void startTest(){
        log.info("startTest start");
        turingPhaseState = new TuringPhaseState();
        assertEquals(turingPhaseState.getTuringTuringPhase(),SEARCH_THE_SET);
        turingPhaseState.start();
        assertEquals(turingPhaseState.getTuringTuringPhase(),SEARCH_THE_SET);
        log.info("startTest done");
    }

    @Test
    public void finishGoToSetTest(){
        log.info("finishGoToSetTest start");
        turingPhaseState = new TuringPhaseState();
        turingPhaseState.start();
        assertEquals(turingPhaseState.getTuringTuringPhase(),SEARCH_THE_SET);
        turingPhaseState.finishSearchTheSet();
        assertEquals(turingPhaseState.getTuringTuringPhase(),WALK_AROUND_THE_SET);
        log.info("finishGoToSetTest done");
    }

    @Test
    public void finishWalkAroundTest() {
        log.info("finishWalkAroundTest start");
        turingPhaseState = new TuringPhaseState();
        turingPhaseState.start();
        turingPhaseState.finishSearchTheSet();
        assertEquals(turingPhaseState.getTuringTuringPhase(),WALK_AROUND_THE_SET);
        turingPhaseState.finishWalkAround();
        assertEquals(turingPhaseState.getTuringTuringPhase(), FILL_THE_OUTSIDE_WITH_COLOR);
        log.info("finishWalkAroundTest done");
    }

    @Test
    public void finishFillTheOutsideWithColorsTest() {
        log.info("finishFillTheOutsideWithColorsTest start");
        turingPhaseState = new TuringPhaseState();
        turingPhaseState.start();
        turingPhaseState.finishSearchTheSet();
        turingPhaseState.finishWalkAround();
        assertEquals(turingPhaseState.getTuringTuringPhase(), FILL_THE_OUTSIDE_WITH_COLOR);
        turingPhaseState.finishFillTheOutsideWithColors();
        assertEquals(turingPhaseState.getTuringTuringPhase(), FINISHED);
        log.info("finishFillTheOutsideWithColorsTest done");
    }
}
