package com.example;

/**
 * Object Item
 * Created by thayneharmon on 12/15/2015.
 */
public class Item {

  String name;
  Double value;
  String store;

  public Item(String name) {
    this(name, 0.0, "");
  }

  public Item(String name, Double value) {
    this(name, value, "");
  }

  public Item(String name, Double value, String store) {
    this.name = name;
    this.value = value;
    this.store = store;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }

  @Override
  public String toString() {
    return "Item [name=" + name + ", value=" + value + ", store=" + store + "]";
  }

}
