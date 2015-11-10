package com.company.calculator;

/**
 * Created by thayneharmon on 11/9/2015.
 */
public class sub implements calc {

  @Override
  public int doCalc(int a, int b) {
    int c =  a - b;
    Print p = new Print();
    System.out.println(a + " minus " + b);
    p.sign(c);
    return c;
  }

}
