package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.commons.layouts.CenterFlowLayout;

import javax.swing.*;


@Log4j2
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelCopyright extends JPanel implements GuiComponentTab {

    public PanelCopyright(ComputerKurzweilApplicationContext ctx) {
        this.setLayout(new CenterFlowLayout());
        String copyright = ctx.getProperties().getAllinone().getView().getCopyright();
        this.add(new JLabel(copyright));
    }

    @Override
    public void showMe() {
        this.setVisible(true);
    }

}
