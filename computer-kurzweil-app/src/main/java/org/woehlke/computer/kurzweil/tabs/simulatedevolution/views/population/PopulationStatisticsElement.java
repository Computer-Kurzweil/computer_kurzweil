package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.population;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellLifeCycleStatus;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class PopulationStatisticsElement extends JPanel implements SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    private final JLabel label;
    private final JTextField statistics;
    private final CellLifeCycleStatus lifeCycleStatus;
    private final int cols = 3;
    private final String defaultTextField = "0";

    public PopulationStatisticsElement(String label, CellLifeCycleStatus lifeCycleStatus) {
        this.label = new JLabel(label);
        this.lifeCycleStatus = lifeCycleStatus;
        this.statistics = new JTextField(defaultTextField,cols);
        this.add(this.label);
        this.add(this.statistics);
        statistics.setBackground(this.lifeCycleStatus.getColorBackground());
        statistics.setForeground(this.lifeCycleStatus.getColorForeground());
    }

    public void setText(int value){
        this.statistics.setText(""+value);
    }

    public void setText(long value){
        this.statistics.setText(""+value);
    }
}
