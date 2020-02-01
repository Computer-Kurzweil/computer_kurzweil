package org.woehlke.computer.kurzweil.view.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;

import javax.swing.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelCopyright extends JPanel implements AppGuiComponent {

    public PanelCopyright(ComputerKurzweilApplicationContext ctx) {
        this.setLayout(new PanelCopyrightLayout());
        String copyright = ctx.getProperties().getAllinone().getView().getCopyright();
        this.add(new JLabel(copyright));
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
