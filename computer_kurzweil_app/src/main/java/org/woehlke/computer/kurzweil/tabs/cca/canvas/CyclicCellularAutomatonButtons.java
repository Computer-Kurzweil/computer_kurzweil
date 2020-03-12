package org.woehlke.computer.kurzweil.tabs.cca.canvas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomaton;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomatonCanvas;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke","canvas"})
@EqualsAndHashCode(callSuper=true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke","canvas"})
public class CyclicCellularAutomatonButtons extends SubTabImpl implements CyclicCellularAutomaton {

  private final JButton buttonVonNeumann;
  private final JButton buttonMoore;
  private final JButton buttonWoehlke;
  private final String buttonLabelVonNeumann;
  private final String buttonLabelMoore;
  private final String buttonLabelWoehlke;
  private final CyclicCellularAutomatonCanvas canvas;
  private final CompoundBorder cyclicCellularAutomatonButtonsBorder;
  private final FlowLayout cyclicCellularAutomatonButtonsLayout;

  public CyclicCellularAutomatonButtons(
      CyclicCellularAutomatonCanvas canvas
  ) {
    super(canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTitle());
    this.canvas = canvas;
    this.buttonLabelVonNeumann = this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeVonNeumann();
    this.buttonLabelMoore =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeMoore();
    this.buttonLabelWoehlke =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeWoehlke();
    this.buttonVonNeumann = new JButton(this.buttonLabelVonNeumann);
    this.buttonMoore = new JButton(this.buttonLabelMoore );
    this.buttonWoehlke = new JButton(this.buttonLabelWoehlke);
    this.cyclicCellularAutomatonButtonsBorder = this.canvas.getTabCtx().getCtx().getBottomButtonsPanelBorder(super.getTitle());
    this.cyclicCellularAutomatonButtonsLayout = new FlowLayout();
    this.setBorder(cyclicCellularAutomatonButtonsBorder);
    this.setLayout(cyclicCellularAutomatonButtonsLayout);
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
  }

}
