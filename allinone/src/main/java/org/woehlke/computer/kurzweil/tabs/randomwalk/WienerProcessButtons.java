package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log
@Getter
@ToString(callSuper = true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke"})
@EqualsAndHashCode(callSuper=true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke"})
public class WienerProcessButtons extends JPanel implements GuiComponentTab {

  private final JButton buttonVonNeumann;
  private final JButton buttonMoore;
  private final JButton buttonWoehlke;
  private final String buttonLabelVonNeumann;
  private final String buttonLabelMoore;
  private final String buttonLabelWoehlke;
  private final String title;
  private final WienerProcessCanvas canvas;
  private final CompoundBorder border;
  private final FlowLayout layout;

  public WienerProcessButtons(
      WienerProcessCanvas canvas
  ) {
    this.canvas = canvas;
    this.title = this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTitle();
    this.buttonLabelVonNeumann = this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeVonNeumann();
    this.buttonLabelMoore =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeMoore();
    this.buttonLabelWoehlke =this.canvas.getTabCtx().getCtx().getProperties().getCca().getView().getNeighborhood().getTypeWoehlke();
    this.buttonVonNeumann = new JButton(this.buttonLabelVonNeumann);
    this.buttonMoore = new JButton(this.buttonLabelMoore );
    this.buttonWoehlke = new JButton(this.buttonLabelWoehlke);
    this.border = this.canvas.getTabCtx().getCtx().getBottomButtonsPanelBorder(  this.title);
    this.layout = new FlowLayout();
    this.setBorder(border);
    this.setLayout(layout);
    this.add(this.buttonVonNeumann);
    this.add(this.buttonMoore);
    this.add(this.buttonWoehlke);
    showMe();
  }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
        repaint();
    }

}
