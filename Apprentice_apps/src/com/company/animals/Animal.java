package com.company.animals;

/**
 * Created by thayneharmon on 7/17/2015.
 */
public abstract class Animal extends LivingCreature {
  private LivingCreature owner; // aggregation

  public Animal(String name, int beatsPerYear, long lifeSpan) {
    super(name,beatsPerYear,lifeSpan);
  }

    public String toString() {
    return super.toString() + "\n      and " + Animal.class.getName()
        + "\n           " + (owner != null ? "My owner is " + owner.getName(): "");
  }
  public LivingCreature getOwner() {return owner;}

  public void setOwner(LivingCreature owner) {this.owner = owner;}

}
