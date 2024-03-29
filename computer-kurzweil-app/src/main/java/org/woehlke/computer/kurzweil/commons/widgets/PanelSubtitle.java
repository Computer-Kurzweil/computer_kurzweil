package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutCenter;

import javax.swing.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelSubtitle extends JPanel implements GuiComponent {

    private static final long serialVersionUID = 7526471155622776147L;

    public PanelSubtitle(String text) {
        this.setLayout(new FlowLayoutCenter());
        this.add(new JLabel(text));
    }

    @Override
    public void showMe() {
        this.setVisible(true);
    }

    public static PanelSubtitle getPanelSubtitleForApplication(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getAllinone().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForCca(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getCca().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForDla(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getDla().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSimulatedEvolution(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getSimulatedevolution().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSimulatedKochSnowflake(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getKochsnowflake().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForMandelbrot(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        return new PanelSubtitle(text);
    }


    public static PanelSubtitle getPanelSubtitleForSameGame(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getSamegame().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiTriangle(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getSierpinskitriangle().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiTetris(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getTetris().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiTurmite(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getTurmite().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiWaTor(ComputerKurzweilContext ctx) {
        String text = ctx.getProperties().getWator().getView().getSubtitle();
        return new PanelSubtitle(text);
    }
}
