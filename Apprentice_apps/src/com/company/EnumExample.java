package com.company;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class EnumExample {

  public enum speed {VERY_FAST, FAST, MIDDLE, SLOW, VERY_SLOW};
  public enum carName {
    CHEVROLET("Chevrolet"), TOYOTA("Toyota");
    private String nameAsString;
    private carName(String nameAsString) {
      this.nameAsString = nameAsString;
    }
    @Override
    public String toString() {
      return this.nameAsString;
    }
  }

  public static void main(String[] args) {

    System.out.println("This " + carName.CHEVROLET + " speed is " + speed.VERY_FAST);
  }

  public class Cars {
    String name;
  }
}
