package com.company;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class WriteXMLStatic {
  public static void main(String[] args) {
    BufferedWriter bw = FileUtils.openFile("newxmlfile.txt");
    try {
      bw.write("<title>Hello world</title>");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
