package org.woehlke.simulation.wator;

import static org.woehlke.simulation.wator.config.GuiConfigDefault.TITLE;

import org.woehlke.simulation.wator.control.ObjectRegistry;
import org.woehlke.simulation.wator.view.FrameWaTor;

/**
 * Class with main Method for Starting the Desktop Application.
 *
 * @author Thomas Woehlke
 * <p>
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @see FrameWaTor
 * <p>
 * Simulated Evolution. Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * Green food appears in a world with red moving cells.
 * These cells eat the food if it is on their position.
 * Movement of the cells depends on random and their DNA.
 * A fit cell moves around and eats enough to reproduce.
 * Reproduction is done by splitting the cell and randomly changing the DNA of the two new Cells.
 * If a cell doesn't eat enough, it will first stand still and after a while it dies.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 */
public class WaTorApplication {

  private WaTorApplication() {
    ObjectRegistry ctx = new ObjectRegistry();
    FrameWaTor frame = new FrameWaTor(ctx);
    ctx.setFrame(frame);
    try {
      ctx.getController().start();
    } catch (IllegalThreadStateException e){
        System.out.println(e.getLocalizedMessage());
    }
  }




  /**
   * Starting the Desktop Application.
   *
   * @param args CLI Parameter.
   */
  public static void main(String[] args) {
    WaTorApplication application = new WaTorApplication();
    System.out.println(TITLE + ": Started the Desktop Application");
  }

}