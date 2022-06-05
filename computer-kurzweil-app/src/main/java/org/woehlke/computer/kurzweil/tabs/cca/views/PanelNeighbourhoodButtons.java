package org.woehlke.computer.kurzweil.tabs.cca.views;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.cca.config.CyclicCellularAutomaton;
import org.woehlke.computer.kurzweil.tabs.cca.canvas.CyclicCellularAutomatonCanvas;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log
@Getter
@ToString(callSuper = true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke","canvas"})
@EqualsAndHashCode(callSuper=true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke","canvas"})
public class PanelNeighbourhoodButtons extends SubTabImpl implements CyclicCellularAutomaton {

    private static final long serialVersionUID = 7526471155622776147L;

    private final JLabel neighborhoodLabel;
    private final JRadioButton buttonVonNeumann;
    private final JRadioButton buttonMoore;
    private final JRadioButton buttonWoehlke;
    private final JButton buttonRestart = new JButton("Restart");
    private final ButtonGroup bgroup = new ButtonGroup();

  private final CyclicCellularAutomatonCanvas canvas;

  public PanelNeighbourhoodButtons(
      CyclicCellularAutomatonCanvas canvas
  ) {
    super(
        canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTitle(),
        canvas.getTabCtx().getCtx().getProperties()
    );
    this.canvas = canvas;

    String buttonLabelVonNeumann = this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeVonNeumann();
    String buttonLabelMoore =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeMoore();
    String buttonLabelWoehlke =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeWoehlke();

    this.neighborhoodLabel = new JLabel("Neighbourhood:");
    this.buttonVonNeumann = new JRadioButton(buttonLabelVonNeumann, true);
    this.buttonMoore = new JRadioButton(buttonLabelMoore);
    this.buttonWoehlke = new JRadioButton(buttonLabelWoehlke);

    bgroup.add(buttonVonNeumann);
    bgroup.add(buttonMoore);
    bgroup.add(buttonWoehlke);

    CompoundBorder cyclicCellularAutomatonButtonsBorder = this.canvas.getTabCtx().getCtx().getBottomButtonsPanelBorder(super.getTitle());
    FlowLayout cyclicCellularAutomatonButtonsLayout = new FlowLayout();
    this.setBorder(cyclicCellularAutomatonButtonsBorder);
    this.setLayout(cyclicCellularAutomatonButtonsLayout);

    this.add(neighborhoodLabel);
    this.add(buttonVonNeumann);
    this.add(buttonMoore);
    this.add(buttonWoehlke);
    this.add(buttonRestart);
  }
}
