package com.company;

/**
 * Composition - Apple creates a new instance of Fruit()
 * Shows effect of passing by value, reference, final
 * Created by thayneharmon on 7/13/2015.
 */
public class Apple {
  String says = "keeps the doctor away";
  private int count = 0;
  private String color = "Red";
  private int weightOunces = 0;

  Apple(){}
  Apple(String color){
    this.color = color;
  }
  Apple(int count, int weightOunces){
    this.count = count;
  }

  public Fruit  f1 = new Fruit(count, color, weightOunces);

  public void eatTheFruit(int howMany, Object ob, final int away) {
    howMany = 5;
    ((Apple)ob).says = "Hello world";
    // error away = 4;
    System.out.println("eatTheFruit::inside: a=" + howMany + ", b=" + away + ", ob.says=" + ((Apple)ob).says);
  }
}
