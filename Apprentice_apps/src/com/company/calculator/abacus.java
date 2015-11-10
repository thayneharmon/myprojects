package com.company.calculator;

/**
 * Created by thayneharmon on 11/9/2015.
 */
public class abacus {

  static int a = 5;
  static int b = 2;

  public static void main(String[] args) {
    new add().doCalc(a,b);
    new sub().doCalc(a,b);
  }
}
