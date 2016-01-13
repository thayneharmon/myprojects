package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


/**
 *
 * Created by thayneharmon on 1/11/2016.
 */
public class DBUtil {

  private final String DATABASE_FILE = "c:/Users/thayneharmon/Documents/myprojects/MyWebApp/shopping_database.dat";

  public void serializeWriterLists(Map<String, Map<String, Item>> shoppingLists) {

    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;

    try {
      fileOutputStream = new FileOutputStream(DATABASE_FILE);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(shoppingLists);
      objectOutputStream.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void serializeReadLists(Map<String, Map<String, Item>> lists) {
    Map<String, Map<String, Item>> lists1;

    if (Files.exists(Paths.get(DATABASE_FILE))) {
      try {
        FileInputStream fileInputStream = new FileInputStream(DATABASE_FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        lists1 = (Map<String, Map<String, Item>>)objectInputStream.readObject();
        objectInputStream.close();
        lists.putAll(lists1);
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}
/*
public void searilizePlant(ArrayList<Plant> _plants) {
    try {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        for (int i = 0; i < _plants.size(); i++) {
            out.writeObject(_plants.get(i));
        }
        out.close();
        fileOut.close();
    } catch (IOException ex) {
    }
}

deserializing code:

public ArrayList<Plant> desearilizePlant() {
    ArrayList<Plant> plants = new ArrayList<Plant>();
    Plant _plant = null;
    try {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        Object object = in.readObject();

       // _plant = (Plant) object;


        // TODO: ITERATE OVER THE WHOLE STREAM
        while (object != null) {
            plants.add((Plant) object);
            object = in.readObject();
        }

        in.close();
    } catch (IOException i) {
        return null;
    } catch (ClassNotFoundException c) {
        System.out.println("Employee class not found");
        return null;
    }
    return plants;
}

My invoking code:

ArrayList<Plant> plants = new ArrayList<Plant>();
plants.add(plant1);
Garden garden = new Garden();
garden.searilizePlant(plants);

// THIS IS THE PROBLEM HERE
ArrayList<Plant> dp = new ArrayList<Plant>();
dp = garden.desearilizePlant();

 //We use this class to not write a header in a file that already exist
class MyObjectOutputStream extends ObjectOutputStream {

    public MyObjectOutputStream(OutputStream os) throws IOException {
        super(os);
      }

    @Override
    protected void writeStreamHeader() {}
}
*/