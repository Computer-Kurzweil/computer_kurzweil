package org.woehlke.computer.kurzweil.tabs.sierpinskitriangle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;

import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class SierpinskiTriangleContext implements TabContext, SierpinskiTriangle {

    private final ComputerKurzweilContext ctx;
    private final SierpinskiTriangleCanvas canvas;
    private final SierpinskiTriangleTab tab;
    private SierpinskiTriangleController controller;

    public SierpinskiTriangleContext(
        SierpinskiTriangleTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new SierpinskiTriangleCanvas( this);
        this.controller = new SierpinskiTriangleController(this);
    }

    @Override
    public SierpinskiTriangleCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new SierpinskiTriangleController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new SierpinskiTriangleController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
