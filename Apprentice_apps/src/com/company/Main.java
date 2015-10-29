package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

  public static void main(String[] args) {

    Random rand = new Random();
    List<char[]> list = new ArrayList<>();
      long count = 0;
    char[] chars = new char[26];

    int mb = 1024 * 1024;
    Runtime runtime = Runtime.getRuntime();
    System.out.println("##### Heap utilization statistics [MB] #####");
    System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb);
    System.out.println("Free Memory:" + runtime.freeMemory() / mb);
    System.out.println("Total Memory:" + runtime.totalMemory() / mb);
    System.out.println("Max Memory:" + runtime.maxMemory() / mb);

    try {
      while (true) {
        for (int i = 0; i < 26; i++) {
          int j = rand.nextInt(26);  // 26 characters in the alphabet
          chars[i] = (char) (j + 65); // ASCII A
        }
        list.add(chars);
          count++;
      }
    }
    catch (OutOfMemoryError ex) {
      System.out.println("Out of memory.");
      System.out.println("Number of chars = " + (count * 26));
      System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb);
      System.out.println("Free Memory:" + runtime.freeMemory() / mb);
      System.out.println("Total Memory:" + runtime.totalMemory() / mb);
      System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }
  }
}
