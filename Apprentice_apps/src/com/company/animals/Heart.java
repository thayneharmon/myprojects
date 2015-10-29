package com.company.animals;

/**
 * Created by thayneharmon on 7/17/2015.
 */
public class Heart {
  private long lifeSpan;
  private int beatsPerYear;
  private int age;

  Heart(long lifeSpan, int beatsPerYear) {
    this.lifeSpan = lifeSpan;
    this.beatsPerYear = beatsPerYear;
  }
  public long getLifeSpan() {
    return this.lifeSpan;
  }
}
