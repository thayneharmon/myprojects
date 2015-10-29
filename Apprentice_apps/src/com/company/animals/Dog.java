package com.company.animals;

/**
 * Created by thayneharmon on 7/17/2015.
 */
public class Dog extends Animal {
  public Dog(String name, int beatsPerYear, long lifeSpan) { super(name, beatsPerYear, lifeSpan);}

  public String say() {return "Hau, jau!";}

  @Override
  public String toString() {
    return super.toString() + "\n        I am a " + Dog.class.getName();
  }
}
