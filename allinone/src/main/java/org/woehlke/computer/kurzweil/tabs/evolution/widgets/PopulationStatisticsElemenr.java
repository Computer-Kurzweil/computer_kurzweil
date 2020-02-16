package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.model.CellLifeCycleStatus;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class PopulationStatisticsElemenr extends JPanel {

    private final JLabel label;
    private final JTextField statistics;
    private final CellLifeCycleStatus lifeCycleStatus;
    private final int cols = 3;
    private final String defaultTextField = "0";

    public PopulationStatisticsElemenr(String label, CellLifeCycleStatus lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
        this.label = new JLabel(label);
        this.statistics = new JTextField(defaultTextField,cols);
        this.add(this.label);
        this.add(this.statistics);
        this.setBackground(this.lifeCycleStatus.getColorBackground());
        this.setForeground(this.lifeCycleStatus.getColorForeground());
    }

    public void setText(int value){
        this.statistics.setText(""+value);
    }
}
