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
public class PanelSubtitle extends JPanel implements GuiComponentTab {

    public PanelSubtitle(String text) {
        this.setLayout(new CenterFlowLayout());
        this.add(new JLabel(text));
    }

    @Override
    public void showMe() {
        this.setVisible(true);
    }

    public static PanelSubtitle getPanelSubtitleForApplication(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getAllinone().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForCca(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getCca().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForDla(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getDla().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSimulatedEvolution(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getEvolution().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSimulatedKochSnowflake(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getKochsnowflake().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForMandelbrot(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        return new PanelSubtitle(text);
    }


    public static PanelSubtitle getPanelSubtitleForSameGame(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getSamegame().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiTriangle(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getSierpinskitriangle().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiTetris(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getTetris().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiTurmite(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getTurmite().getView().getSubtitle();
        return new PanelSubtitle(text);
    }

    public static PanelSubtitle getPanelSubtitleForSierpinskiWaTor(ComputerKurzweilApplicationContext ctx) {
        String text = ctx.getProperties().getWator().getView().getSubtitle();
        return new PanelSubtitle(text);
    }
}
