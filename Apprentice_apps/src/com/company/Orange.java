package com.company;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class Orange extends Fruit {
  String says = "I am orange";
  Orange(){}

  Orange(int count, int weightOnces) {
    super(count, "Orange", weightOnces, "tart");
  }
  @Override
  public void aMethodToOverride() {
    System.out.println("I have been overridden");
    allFruit = "square";
    //count = 0; //private
    taste = "yuk";

  }
}
