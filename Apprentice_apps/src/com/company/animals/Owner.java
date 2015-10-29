package com.company.animals;

/**
 * Created by thayneharmon on 7/17/2015.
 */
public final class Owner extends LivingCreature {
  public Owner(String name, int beatsPerYear, long lifeSpan) { super(name, beatsPerYear, lifeSpan);}

  public String say() {return "I am intelligent!";}

  @Override
  public String toString() {
    return super.toString() + "\n        and " + this.getClass().getName();
  }

}
