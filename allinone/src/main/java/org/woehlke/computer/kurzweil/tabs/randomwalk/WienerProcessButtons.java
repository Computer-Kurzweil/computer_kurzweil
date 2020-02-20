package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.TabType.RANDOM_WALK_WIENER_PROCESS;

@Log
@Getter
@ToString(callSuper = true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke"})
@EqualsAndHashCode(callSuper=true, exclude = {"buttonVonNeumann","buttonMoore","buttonWoehlke"})
public class WienerProcessButtons extends JPanel implements GuiComponentTab {

  private final WienerProcessCanvas canvas;
  private final CompoundBorder border;
  private final FlowLayout layout;

  public WienerProcessButtons(
      WienerProcessCanvas canvas
  ) {
      this.canvas=canvas;
    this.border = this.canvas.getTabCtx().getCtx().getBottomButtonsPanelBorder(
        this.canvas.getTabCtx().getCtx().getProperties().getTitle(RANDOM_WALK_WIENER_PROCESS)
    );
    this.layout = new FlowLayout();
    this.setBorder(border);
    this.setLayout(layout);
    showMe();
  }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
        repaint();
    }

}
