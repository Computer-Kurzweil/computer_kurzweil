package org.woehlke.computer.kurzweil.widgets;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.widgets.layouts.PanelCopyrightLayout;

import javax.swing.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelCopyright extends JPanel implements GuiComponentTab {

    public PanelCopyright(ComputerKurzweilApplicationContext ctx) {
        this.setLayout(new PanelCopyrightLayout());
        String copyright = ctx.getProperties().getAllinone().getView().getCopyright();
        this.add(new JLabel(copyright));
    }

    @Override
    public void showMe() {
        this.setVisible(true);
    }

}
