package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population;

import lombok.*;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;

import java.io.Serializable;

@Log
@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CellPopulationRecord implements SimulatedEvolution, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

   private int youngCells;
   private int youngAndFatCells;
   private int fullAgeCells;
   private int hungryCells;
   private int oldCells;
   private int deadCells;
   private int population;
   private long generationYoungest;
   private long generationOldest;
   private long worldIteration;

   public void countYoungCell(){
       this.population++;
       this.youngCells++;
   }

    public void countYoungAndFatCell(){
        this.population++;
        this.youngAndFatCells++;
    }

    public void countFullAgeCell(){
        this.population++;
        this.fullAgeCells++;
    }

    public void countHungryCell(){
        this.population++;
        this.hungryCells++;
    }

    public void countOldCell(){
        this.population++;
        oldCells++;
    }

    public void countDeadCell(){
        this.population++;
        deadCells++;
    }

}
