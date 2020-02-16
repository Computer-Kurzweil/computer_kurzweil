package org.woehlke.computer.kurzweil.commons.tabs;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.layouts.BoxLayoutVertical;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
@ToString
public abstract class TabPanel extends JPanel implements Tab {

    @ToString.Exclude
    protected final ComputerKurzweilApplicationContext ctx;
    @ToString.Exclude
    protected final CompoundBorder border;
    @ToString.Exclude
    protected final BoxLayoutVertical layout;
    @ToString.Exclude
    protected final PanelSubtitle panelSubtitle;

    protected final String title;
    protected final String subTitle;

    protected final TabType tabType;

    protected TabPanel(ComputerKurzweilApplicationContext ctx, TabType tabType, String subTitle, String title) {
        this.setName(title);
        this.ctx = ctx;
        this.tabType = tabType;
        this.layout = new BoxLayoutVertical(this);
        this.border = this.ctx.getBorder();
        this.subTitle = subTitle;
        this.title = title;
        this.panelSubtitle = new PanelSubtitle(subTitle);
        this.setLayout(layout);
        this.setBorder(border);
    }
}
