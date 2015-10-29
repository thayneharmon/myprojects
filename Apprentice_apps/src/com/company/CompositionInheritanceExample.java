package com.company;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;


/**
 * Created by thayneharmon on 7/13/2015.
 */
public class CompositionInheritanceExample {

  public static void main(String[] args) {

    Apple a1 = new Apple();
    System.out.println(a1.says);
    System.out.println(a1.f1.allFruit);


    Orange o1 = new Orange();
    System.out.println(o1.says);
    System.out.println(o1.allFruit);
    //o1.taste = "yum"; //private
    o1.allFruit = "good";

    Fruit o2 = new Orange();
    System.out.println(o2.allFruit);

    Fruit o3 = new Orange(5,3); // count, ounces
    o3.aMethodToOverride();

    int we = 2;

    a1.eatTheFruit(1,a1,we);

    System.out.println("we="+we+",a1.says="+a1.says);

    // instance of
    List<Object> ob = new ArrayList<>();
    int isInt = 10;

    ob.add(isInt);
    ob.add(o1);
    ob.add(o3);

    for (Object o : ob) {
      if ( o instanceof Integer )
        System.out.println("found int");
      else
      if ( o instanceof Orange)
        System.out.println("found Orange");
      else
      if ( o instanceof Fruit)
        System.out.println("found Fruit");
    }

  }
}
