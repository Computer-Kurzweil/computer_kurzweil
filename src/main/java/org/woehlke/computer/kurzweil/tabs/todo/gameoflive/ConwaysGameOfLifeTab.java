package org.woehlke.computer.kurzweil.tabs.todo.gameoflive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.commons.widgets.PanelBottomButtons;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.TabType;

import java.awt.event.ActionEvent;


@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class ConwaysGameOfLifeTab extends TabPanel implements Tab {

    private final ConwaysGameOfLifeContext tabCtx;
    private final ConwaysGameOfLifeCanvas canvas;

    private final PanelBottomButtons bottomButtonsPanel;

    public ConwaysGameOfLifeTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TabType.CONWAYS_GAME_OF_LIFE);

        this.tabCtx = new ConwaysGameOfLifeContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.bottomButtonsPanel = new PanelBottomButtons( this );
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.bottomButtonsPanel.getStartButton().addActionListener(this);
        this.bottomButtonsPanel.getStopButton().addActionListener(this);
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.ctx.getFrame().pack();
        showMe();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void showMe() {

    }

    @Override
    public TabContext getTabCtx() {
        return null;
    }
}
