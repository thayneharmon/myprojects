package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

  public static void main(String[] args) {

    Random rand = new Random();
    List<char[]> list = new ArrayList<>();
    long count = 0;
    long listSize = 0;

    int mb = 1024 * 1024;
    Runtime runtime = Runtime.getRuntime();
    System.out.println("##### Heap utilization statistics [MB] #####");
    System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb);
    System.out.println("Free Memory:" + runtime.freeMemory() / mb);
    System.out.println("Total Memory:" + runtime.totalMemory() / mb);
    System.out.println("Max Memory:" + runtime.maxMemory() / mb);

    final int numOfChar = 26;
    try {
      while (true) {
        char[] chars = new char[numOfChar];
        for (int i = 0; i < numOfChar; i++) { // filling in the array is not needed, the array is fully allocated
          int j = rand.nextInt(numOfChar);  // 26 characters in the alphabet
          chars[i] = (char) (j + 65); // ASCII A
        }
        list.add(chars);
        count+=chars.length;
        listSize = list.size();
      }
    }
    catch (OutOfMemoryError ex) {
      list = null;
      System.out.println("Out of memory.");
      System.out.println("Number of chars = " + count + ", list size = " + listSize);
      System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb);
      System.out.println("Free Memory:" + runtime.freeMemory() / mb);
      System.out.println("Total Memory:" + runtime.totalMemory() / mb);
      System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }
  }
}
/*
##### Heap utilization statistics [MB] #####
Used Memory:2
Free Memory:118
Total Memory:121
Max Memory:1795
Out of memory.
Number of chars = 531028056, list size = 20424156
Used Memory:2
Free Memory:1047
Total Memory:1050
Max Memory:1795

1062.056112 MB

##### Heap utilization statistics [MB] #####
Used Memory:2
Free Memory:118
Total Memory:121
Max Memory:1795
Out of memory.
Number of chars = 815511552, list size = 796398
Used Memory:1570
Free Memory:224
Total Memory:1795
Max Memory:1795

1631.023104 MB
 */