package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model;

import lombok.*;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SimulatedEvolutionParameter implements SimulatedEvolution, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private int foodPerDay;
    private int foodPerDayGardenOfEden;
    private boolean gardenOfEdenEnabled;

    public void increaseFoodPerDay() {
        this.foodPerDay++;
    }

    public void decreaseFoodPerDay() {
        this.foodPerDay--;
    }

    public void toggleGardenOfEden() {
      this.gardenOfEdenEnabled = ! this.gardenOfEdenEnabled;
    }

}
