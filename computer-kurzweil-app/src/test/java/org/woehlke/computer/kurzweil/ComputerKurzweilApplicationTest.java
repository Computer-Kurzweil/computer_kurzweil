package org.woehlke.computer.kurzweil;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple AppMainDesktop.
 */
public class ComputerKurzweilApplicationTest {

  /**
   * When I am grown up, I will be a cool Uni Test.
   */
  @Test
  public void testApp() {
      String line = "-----------------------------------------------------------------------------------------";
      System.out.println("ComputerKurzweilApplicationTest.testApp()");
      System.out.println(line);
      System.out.println("When I am grown up, I will be a cool Unit Test.");
      System.out.println(line);
      assertTrue(true);
  }
}
