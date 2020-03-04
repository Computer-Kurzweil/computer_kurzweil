package org.woehlke.computer.kurzweil.tabs.simulatedevolution.cell;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;

import java.io.Serializable;

/**
 * The CellCore contains the DNA which has influence on orientation at moving.
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 19:55:23
 */
@Log
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CellCore implements Serializable {

    /**
    * The DNA Values of the Genome.
    */
    private Integer[] dna;

    private final static int MIN_VALUE = 0;
    private final static int MAX_VALUE = 16;
    private final static int MAX_INITIAL_VALUE = 8;

    private final ComputerKurzweilContext ctx;

    public CellCore(final ComputerKurzweilContext ctx) {
        this.ctx=ctx;
        dna = new Integer[CellOrientation.values().length];
        for (int i = 0; i < CellOrientation.values().length; i++) {
            dna[i] = ctx.getRandom().nextInt() % MAX_INITIAL_VALUE;
        }
    }

    private CellCore(final CellCore other) {
        this.ctx = other.ctx;
        this.dna = other.dna;
    }

  /**
   * Mitosis is the Cell Core Division where the DNA changes for Evolution.
   * After Mitosis this Cell Core is one of the two Children.
   *
   * @return the other Child CellCore.
   * @see Cell#reproductionByCellDivision()
   */
  public CellCore reproductionMitosis() {
    CellCore child = new CellCore(this);
    CellOrientation dnaMutationBase = CellOrientation.values()[getRandomBaseForMutation()];
    this.decrease(dnaMutationBase);
    child.increase(dnaMutationBase);
    return child;
  }

  private int getRandomBaseForMutation(){
      int baseIndex = ctx.getRandom().nextInt() % CellOrientation.values().length;
      return (baseIndex < MIN_VALUE)?( baseIndex * -1):baseIndex;
  }

  private void increase(CellOrientation dnaMutationBase) {
    if (dna[dnaMutationBase.ordinal()] == MAX_VALUE) {
      for (int i = 0; i < dna.length; i++) {
          dna[i]--;
      }
    }
    dna[dnaMutationBase.ordinal()]++;
  }

  private void decrease(CellOrientation dnaMutationBase) {
    if (dna[dnaMutationBase.ordinal()] == -MAX_VALUE) {
      for (int i = 0; i < dna.length; i++) {
          dna[i]++;
      }
    }
    dna[dnaMutationBase.ordinal()]--;
  }

      private double getDnaValue(){
          double value;
          double dnaValue = 0.0d;
          for (int i = 0; i < CellOrientation.values().length; i++) {
              value = dna[i].longValue() ^ 2;
              dnaValue += (value < MIN_VALUE)?(value * -1):value;
          }
          return dnaValue;
      }

    private Double[] getRna(double dnaValue){
        Double[] rna = new Double[CellOrientation.values().length];
        double rnaValue = 0.0d;
        double value;
        for (int i = 0; i < CellOrientation.values().length; i++) {
            value = dna[i].longValue() ^ 2;
            rnaValue += ((value < MIN_VALUE)?(value * -1):value) / dnaValue;
            rna[i] = rnaValue;
        }
        return rna;
    }

    private double getSumRandom(){
        double sumRandom = ctx.getRandom().nextInt(MAX_VALUE) ^ 2;
        return ((sumRandom < MIN_VALUE)?(sumRandom * -1):sumRandom) / (MAX_VALUE ^ 2);
    }

  /**
   * @return gives a new Orientation based on the Combinition of Random and DNA.
   */
  public CellOrientation getRandomOrientation() {
      double dnaValue = getDnaValue();
      Double[] rna = getRna(dnaValue);
      double sumRandom = getSumRandom();
      for (int i = 0; i < CellOrientation.values().length; i++) {
        if (sumRandom > rna[i]) {
            return CellOrientation.values()[i];
        }
      }
      return CellOrientation.FORWARD;
  }

}
