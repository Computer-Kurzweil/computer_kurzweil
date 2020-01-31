package org.woehlke.computer.kurzweil.view.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;

import javax.swing.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelCopyright extends JPanel {

    public PanelCopyright(ComputerKurzweilApplicationContext ctx) {
        this.setLayout(new PanelCopyrightLayout());
        String copyright = ctx.getProperties().getAllinone().getView().getCopyright();
        this.add(new JLabel(copyright));
    }

}
