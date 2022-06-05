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

   private int youngCells = 0;
   private int youngAndFatCells = 0;
   private int fullAgeCells = 0;
   private int hungryCells = 0;
   private int oldCells = 0;
   private int deadCells = 0;
   private int population=0;
   private long generationYoungest=0;
   private long generationOldest=0;
   private long worldIteration=0;

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
        this.oldCells++;
    }

    public void countDeadCell(){
        this.population++;
        this.deadCells++;
    }

}
