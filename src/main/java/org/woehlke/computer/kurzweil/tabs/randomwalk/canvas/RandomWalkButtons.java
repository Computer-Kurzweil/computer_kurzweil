package org.woehlke.computer.kurzweil.tabs.randomwalk.canvas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;
import org.woehlke.computer.kurzweil.tabs.randomwalk.RandomWalk;
import org.woehlke.computer.kurzweil.tabs.randomwalk.RandomWalkCanvas;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.TabType.RANDOM_WALK_WIENER_PROCESS;

@Log4j2
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class RandomWalkButtons extends JPanel implements GuiComponent, RandomWalk {

  private final RandomWalkCanvas canvas;
  private final CompoundBorder border;
  private final FlowLayout layout;

  public RandomWalkButtons(
      RandomWalkCanvas canvas
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
