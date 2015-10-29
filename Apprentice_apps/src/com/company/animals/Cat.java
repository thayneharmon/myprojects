package com.company.animals;

/**
 * Created by thayneharmon on 7/17/2015.
 */
public class Cat extends Animal {
  public Cat(String name, int beatsPerYear, long lifeSpan) { super(name, beatsPerYear, lifeSpan);}

  public String say() {return "meow, meow!";}

  @Override
  public String toString() {
    return super.toString() + "\n        I am a " + Cat.class.getName();
  }

}
