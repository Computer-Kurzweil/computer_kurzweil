package org.woehlke.simulation.allinone.view.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;

import javax.swing.*;
import java.awt.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelCopyright extends JPanel {

    @Getter
    private final JLabel copyrightLabel;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    public PanelCopyright(ComputerKurzweilApplicationContext ctx) {
        this.ctx=ctx;
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        this.setLayout(layout);
        this.copyrightLabel = new JLabel(ctx.getProperties().getAllinone().getView().getCopyright());
        this.add(copyrightLabel);
    }

}
