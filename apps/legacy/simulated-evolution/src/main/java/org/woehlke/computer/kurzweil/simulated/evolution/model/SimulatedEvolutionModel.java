package org.woehlke.computer.kurzweil.simulated.evolution.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.simulated.evolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.simulated.evolution.model.world.SimulatedEvolutionParameter;
import org.woehlke.computer.kurzweil.simulated.evolution.model.lattice.SimulatedEvolutionWorldLattice;
import org.woehlke.computer.kurzweil.simulated.evolution.model.world.WorldPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The World contains Water, Cells and Food.
 * It is the Data Model of the Simulation in a MVC Pattern.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see Cell
 * @see SimulatedEvolutionWorldLattice
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/simulated-evolution">Github Repository</a>
 * @see <a href="https://java.woehlke.org/simulated-evolution/">Maven Project Repository</a>
 *
 * User: thomas
 * Date: 04.02.2006
 * Time: 19:06:20
 */
@Log4j2
@ToString(exclude = {"random"})
@EqualsAndHashCode(exclude = {"random"})
public class SimulatedEvolutionModel implements Serializable {

    static final long serialVersionUID = 242L;

    /**
     * List of the Simulated Bacteria Cells.
     */
    private List<Cell> cells;

    /**
     * Start with 20 Cells.
     */
    private final int INITIAL_POPULATION = 20;

    /**
     * Random Generator used for Bacteria Motion.
     */
    private Random random;

    /**
     * Definition of the World's Size in Pixel Width and Height.
     */
    private WorldPoint worldDimensions;

    /**
     * Map of the World monitoring growth and eating food.
     */
    private SimulatedEvolutionWorldLattice simulatedEvolutionWorldLattice;

    //@Getter
    //private SimulatedEvolutionPopulationContainer simulatedEvolutionPopulationContainer;

    @Getter
    private SimulatedEvolutionParameter simulatedEvolutionParameter;

    public SimulatedEvolutionModel(WorldPoint worldDimensions) {
        long seed = new Date().getTime();
        random = new Random(seed);
        this.worldDimensions = worldDimensions;
        simulatedEvolutionWorldLattice = new SimulatedEvolutionWorldLattice(this.worldDimensions,random);
        createPopulation();
        simulatedEvolutionParameter = new SimulatedEvolutionParameter();
        //simulatedEvolutionPopulationContainer = new SimulatedEvolutionPopulationContainer(tabCtx);
    }

    /**
     * Create the initial Population of Bacteria Cells and give them their position in the World.
     */
    private void createPopulation() {
        cells = new ArrayList<Cell>();
        for (int i = 0; i < INITIAL_POPULATION; i++) {
            int x = random.nextInt(worldDimensions.getX());
            int y = random.nextInt(worldDimensions.getY());
            if (x < 0) {
                x *= -1;
            }
            if (y < 0) {
                y *= -1;
            }
            WorldPoint pos = new WorldPoint(x, y);
            Cell cell = new Cell(worldDimensions, pos, random);
            cells.add(cell);
        }
    }

    /**
     * One Step of Time in the World in which the Population of Bacteria Cell perform Life:
     * Every Cell moves, eats, dies of hunger, and it has sex: splitting into two children with changed DNA.
     */
    public void letLivePopulation() {
        simulatedEvolutionWorldLattice.letFoodGrow();
        WorldPoint pos;
        List<Cell> children = new ArrayList<Cell>();
        List<Cell> died = new ArrayList<Cell>();
        for (Cell cell:cells) {
            cell.move();
            if(cell.died()){
                died.add(cell);
            } else {
                pos = cell.getPosition();
                int food = simulatedEvolutionWorldLattice.eat(pos);
                cell.eat(food);
                if (cell.isPregnant()) {
                    Cell child = cell.performReproductionByCellDivision();
                    children.add(child);
                }
            }
        }
        for(Cell dead:died){
            cells.remove(dead);
        }
        cells.addAll(children);
    }

    public List<Cell> getAllCells(){
        return cells;
    }

    public boolean hasFood(int x, int y) {
        return simulatedEvolutionWorldLattice.hasFood(x,y);
    }
}
