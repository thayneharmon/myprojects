package com.company;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.Comparator;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class ReadSortFile {

  public static void main(String[] args) {
    List<String> lines = new ArrayList<>();
    //don't use filereader or filewriter in production, use ?
    try{
      BufferedReader br = new BufferedReader(new FileReader("sample10k.txt"));
      String s;
      while((s = br.readLine()) != null) {
        lines.add(s);
      }
      Collections.sort(lines);
      //Collections.reverse(lines);
      //Collections.sort(lines, new mycompare());
      BufferedWriter bw = new BufferedWriter(new FileWriter("sample10k_sorted.txt"));
      //FileOutputStream fos = new FileOutputStream("sample10k_sorted.txt");
      for(int i = 0;i < lines.size(); i++)
      {
        bw.write(lines.get(i));
        bw.newLine();

        //fos.write(((String)lines.get(i)).getBytes());
        //fos.write((byte)'\r');
        //fos.write((byte)'\n');
      }
      bw.flush();
      bw.close();
      //fos.flush();
      //fos.close();
    }catch(IOException ex){
      ex.printStackTrace();
    }
  }
  /*public static Comparator<String>  mycompare  = new Comparator<String>() {
      public int compare(String o1, String o2) {
          //return o1.compareTo(o2); // ascending
         return o2.compareTo(o1); // descending
      }
  };*/
  public static class mycompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2){
      return o2.compareTo(o1);
    }
  }
}
