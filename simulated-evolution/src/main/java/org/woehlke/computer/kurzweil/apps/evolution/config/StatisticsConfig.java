package org.woehlke.computer.kurzweil.apps.evolution.config;

/**
 * TODO write doc.
 */
public class StatisticsConfig implements StatisticsConfigDefault {

  /**
   * TODO write doc.
   */
  private final int queueMaxLength;

  public StatisticsConfig() {
    this.queueMaxLength = QUEUE_MAX_LENGTH;
  }

  public int getQueueMaxLength() {
    return queueMaxLength;
  }
}