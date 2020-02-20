package org.woehlke.computer.kurzweil.commons.tabs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.layouts.BoxLayoutVertical;

import javax.swing.*;
import javax.swing.border.Border;

@Log
@Getter
@ToString(callSuper = true, exclude = {"ctx","tabbedPane","border","layout","panelSubtitle"})
@EqualsAndHashCode(callSuper=true, exclude = {"ctx","tabbedPane","border","layout","panelSubtitle"})
public abstract class TabPanel extends JPanel implements Tab {

    protected final ComputerKurzweilApplicationTabbedPane tabbedPane;
    protected final ComputerKurzweilApplicationContext ctx;
    protected final Border border;
    protected final BoxLayoutVertical layout;
    protected final PanelSubtitle panelSubtitle;

    protected final String title;
    protected final String subTitle;

    protected final TabType tabType;

    protected TabPanel(ComputerKurzweilApplicationTabbedPane tabbedPane, TabType tabType) {
        this.tabbedPane=tabbedPane;
        this.ctx = this.tabbedPane.getCtx();
        this.tabType = tabType;
        this.layout = new BoxLayoutVertical(this);
        this.border = this.ctx.getTabBorder();
        this.title =  this.ctx.getProperties().getTitle(tabType);
        this.subTitle = this.ctx.getProperties().getSubtitle(tabType);
        this.setName(title);
        this.panelSubtitle = new PanelSubtitle(subTitle);
        this.setLayout(layout);
        //this.setBorder(border);
    }
}
