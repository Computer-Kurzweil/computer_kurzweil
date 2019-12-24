package org.woehlke.simulation.mandelbrot.model.turing;

import org.junit.Assert;
import org.junit.Test;
import org.woehlke.simulation.mandelbrot.model.turing.impl.TuringPhaseStateMachineImpl;

import java.util.logging.Logger;

import static org.woehlke.simulation.mandelbrot.model.turing.state.TuringPhase.*;

public class TuringTuringPhaseStateMachineEnumTest {

    private TuringPhaseStateMachineImpl turingPhaseStateMachine = new TuringPhaseStateMachineImpl();

    public static Logger log = Logger.getLogger(TuringPositionsStateMachineTest.class.getName());

    @Test
    public void startTest(){
        log.info("startTest start");
        turingPhaseStateMachine = new TuringPhaseStateMachineImpl();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(),SEARCH_THE_SET);
        turingPhaseStateMachine.start();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(),SEARCH_THE_SET);
        log.info("startTest done");
    }

    @Test
    public void finishGoToSetTest(){
        log.info("finishGoToSetTest start");
        turingPhaseStateMachine = new TuringPhaseStateMachineImpl();
        turingPhaseStateMachine.start();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(),SEARCH_THE_SET);
        turingPhaseStateMachine.finishSearchTheSet();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(),WALK_AROUND_THE_SET);
        log.info("finishGoToSetTest done");
    }

    @Test
    public void finishWalkAroundTest() {
        log.info("finishWalkAroundTest start");
        turingPhaseStateMachine = new TuringPhaseStateMachineImpl();
        turingPhaseStateMachine.start();
        turingPhaseStateMachine.finishSearchTheSet();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(),WALK_AROUND_THE_SET);
        turingPhaseStateMachine.finishWalkAround();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(), FILL_THE_OUTSIDE_WITH_COLOR);
        log.info("finishWalkAroundTest done");
    }

    @Test
    public void finishFillTheOutsideWithColorsTest() {
        log.info("finishFillTheOutsideWithColorsTest start");
        turingPhaseStateMachine = new TuringPhaseStateMachineImpl();
        turingPhaseStateMachine.start();
        turingPhaseStateMachine.finishSearchTheSet();
        turingPhaseStateMachine.finishWalkAround();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(), FILL_THE_OUTSIDE_WITH_COLOR);
        turingPhaseStateMachine.finishFillTheOutsideWithColors();
        Assert.assertEquals(turingPhaseStateMachine.getTuringTuringPhase(), FINISHED);
        log.info("finishFillTheOutsideWithColorsTest done");
    }
}
