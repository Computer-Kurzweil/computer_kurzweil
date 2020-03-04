package org.woehlke.computer.kurzweil.tabs.simulatedevolution.world;

import lombok.*;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SimulatedEvolutionParameter implements SimulatedEvolution {

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
