package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class FileUtils {

  static BufferedWriter openFile(String fileName) {

    try {
      return new BufferedWriter(new FileWriter(fileName));
    }
    catch (IOException e) {
      throw new RuntimeException("openFile:"+fileName,e);
    }
  }
}
