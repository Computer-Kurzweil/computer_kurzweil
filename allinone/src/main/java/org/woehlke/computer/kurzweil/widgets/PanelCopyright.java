package org.woehlke.computer.kurzweil.widgets;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.layouts.PanelCopyrightLayout;

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
    public void showMe() {

    }

    @Override
    public void hideMe() {

    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
