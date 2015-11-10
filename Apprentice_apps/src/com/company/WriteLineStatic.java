package com.company;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class WriteLineStatic {
  public static void main(String[] args) {
    BufferedWriter bw = FileUtils.openFile("newfile.txt");
    try {
      bw.write("Hello world");
    }
    catch (IOException e) {
      throw new RuntimeException("WriteLineStatic: " + "newfile.txt", e);
    }
  }
}
