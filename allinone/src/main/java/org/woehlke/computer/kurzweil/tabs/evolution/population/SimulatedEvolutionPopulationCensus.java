package org.woehlke.computer.kurzweil.tabs.evolution.population;

import lombok.*;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.cell.CellLifeCycleStatus;

@Log
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SimulatedEvolutionPopulationCensus {

    @Getter @Setter private int youngCells;
    @Getter @Setter private int youngAndFatCells;
    @Getter @Setter private int fullAgeCells;
    @Getter @Setter private int hungryCells;
    @Getter @Setter private int oldCells;
    @Getter @Setter private int deadCells;
    @Getter @Setter private int population;
    @Getter @Setter private long worldIteration;

  /**
   * TODO write doc.
   */
  public void countStatusOfOneCell(CellLifeCycleStatus status) {
    population++;
    switch (status) {
      case YOUNG:
        youngCells++;
        break;
      case YOUNG_AND_FAT:
        youngAndFatCells++;
        break;
      case FULL_AGE:
        fullAgeCells++;
        break;
      case HUNGRY:
        hungryCells++;
        break;
      case OLD:
        oldCells++;
        break;
      case DEAD:
        deadCells++;
        break;
    }
  }

}
