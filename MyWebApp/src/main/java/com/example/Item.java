package com.example;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Object Item
 * Created by thayneharmon on 12/15/2015.
 */
public class Item implements Serializable{

  private String name;
  private Double price;
  private String store;
  private Date date;

  public Item(String name) {
    this(name, 0.0, "");
  }

  public Item(String name, Double price) {
    this(name, price, "");
  }

  public Item(String name, Double price, String store) {
    this.name = name;
    this.price = price;
    this.store = store;
    date = Calendar.getInstance().getTime();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Item [name=" + name + ", price=" + price + ", store=" + store + "]";
  }

}
