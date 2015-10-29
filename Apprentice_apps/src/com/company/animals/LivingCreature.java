package com.company.animals;

/**
 * Created by thayneharmon on 7/17/2015.
 */
public abstract class LivingCreature implements SoundEmitter {

  private String name = "Not loved";
  private Heart heart;

  protected LivingCreature(String name, int beatsPerYear, long lifeSpan) {
    this.setName(name);
    heart = new Heart(lifeSpan, beatsPerYear);
  }

  public String getName() {return name;}

  void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Not loved!");
    }
    this.name = name;
  }

  public String say() { return "Grunt!";}

  @Override
  public String toString() {
    return super.toString() + "\n" +
        "       I live " + heart.getLifeSpan() + " years\n" +
        "       I am " + LivingCreature.class.getName();
  }

  public Heart getHeart() {
    return heart;
  }
}
