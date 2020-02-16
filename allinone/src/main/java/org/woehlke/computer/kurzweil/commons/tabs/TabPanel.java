package org.woehlke.computer.kurzweil.commons.tabs;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.layouts.TabLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
@ToString(exclude={"ctx","border","layout"})
public abstract class TabPanel extends JPanel implements Tab {

    protected final ComputerKurzweilApplicationContext ctx;
    protected final CompoundBorder border;
    protected final TabLayout layout;
    protected final String title;
    protected final String subTitle;
    protected final PanelSubtitle panelSubtitle;

    protected final TabType tabType;

    protected TabPanel(ComputerKurzweilApplicationContext ctx, TabType tabType, String subTitle, String title) {
        this.setName(title);
        this.ctx = ctx;
        this.tabType = tabType;
        this.layout = new TabLayout(this);
        this.border =  this.ctx.getBorder();
        this.subTitle = subTitle;
        this.title = title;
        this.panelSubtitle = new PanelSubtitle(subTitle);
        this.setLayout(layout);
        this.setBorder(border);
    }
}
