package org.woehlke.computer.kurzweil.tabs.cca;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;

import javax.swing.*;
import java.awt.*;

@Log
@Getter
public class CyclicCellularAutomatonButtons extends JPanel implements GuiComponentTab {

  private final JButton buttonVonNeumann;
  private final JButton buttonMoore;
  private final JButton buttonWoehlke;
  private final String buttonLabelVonNeumann;
  private final String buttonLabelMoore;
  private final String buttonLabelWoehlke;
  private final CyclicCellularAutomatonCanvas canvas;

  public CyclicCellularAutomatonButtons(
      CyclicCellularAutomatonCanvas canvas
  ) {
    this.canvas = canvas;
    this.buttonLabelVonNeumann = this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeVonNeumann();
    this.buttonLabelMoore =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeMoore();
    this.buttonLabelWoehlke =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeWoehlke();
    this.buttonVonNeumann = new JButton(this.buttonLabelVonNeumann);
    this.buttonMoore = new JButton(this.buttonLabelMoore );
    this.buttonWoehlke = new JButton(this.buttonLabelWoehlke);
    this.setLayout(new FlowLayout());
    this.add(new JLabel(this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTitle()));
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
    this.buttonVonNeumann.addActionListener(this.canvas.getTabCtx());
    this.buttonMoore.addActionListener(this.canvas.getTabCtx());
    this.buttonWoehlke.addActionListener(this.canvas.getTabCtx());
    showMe();
  }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

}
