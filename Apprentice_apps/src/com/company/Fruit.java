package com.company;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class Fruit {
  protected String allFruit = "round";
  private int count = 0;
  private String color = "";
  private int weightOunces = 0;
  public String taste = ""; // breaks encapsulation, accessible outside to the world


  public Fruit() {}

  public Fruit(int count) {
    this.count = count;
    System.out.println("I am " + count + " " + color + " fruit weighing " + weightOunces);
  }
  public Fruit(int count, String color) {
    this(count);
    this.color = color;
  }
  public Fruit(int count, String color, int weightOunces) {
    this(count, color);
    this.weightOunces = weightOunces;
  }
  public Fruit(int count, String color, int weightOunces, String taste) {
    this(count, color, weightOunces);
    this.taste = taste;
  }



  public void aMethodToOverride(){

    System.out.println("This is me");
  }

}
