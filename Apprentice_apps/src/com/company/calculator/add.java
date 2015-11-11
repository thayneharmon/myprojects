package com.company.calculator;

/**
 * Created by thayneharmon on 11/9/2015.
 */
public class add extends Print {

  public int doCalc(int a, int b) {
    int c =  a + b;
    System.out.println(a + " plus " + b);
    printTotal(c);
    return c;
  }
}
