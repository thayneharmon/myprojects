package com.example;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Object Item
 * Created by thayneharmon on 12/15/2015.
 */
public class Item1 implements Serializable{

  private String name;
  private Double price;
  private String store;
  private String date;
  private Integer count;

  public Item1(String name) {
    this(name, 0.0);
  }

  public Item1(String name, Double price) {
    this(name, price, "");
  }

  public Item1(String name, Double price, String store) {
    this(name, price, store, 1);
  }

  public Item1(String name, Double price, String store, Integer count) {
    this.name = name;
    this.price = price;
    this.store = store;
    this.count = count;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
    date = simpleDateFormat.format(Calendar.getInstance().getTime());
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

  public String getDate() {
    return date;
  }

  public void setDate(Date date) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
    this.date = simpleDateFormat.format(date);
  }

  @Override
  public String toString() {
    return "Item [name=" + name + ", price=" + price + ", store=" + store + "]";
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
