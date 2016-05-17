package com.example.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.UriBuilder;

import com.example.DBUtil;
import com.example.Item;
import com.example.Item1;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/shopping")
public class JSONService {

  final ObjectMapper mapper = new ObjectMapper();
  final static Map<String, Map<String, Item>> shoppingLists = new HashMap<String, Map<String, Item>>();
  final DBUtil dbUtil = new DBUtil();

  public JSONService () {
    // read once
    if (shoppingLists.size() == 0) {
      dbUtil.serializeReadLists(shoppingLists);
    }
  }
  /**
   * list shopping lists
   * @return lists
   * @throws IOException
   */
  @GET
  @Path("/lists")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response listShoppingLists() throws IOException {

    List<String> linkList = new ArrayList<String>();

    for (String list : shoppingLists.keySet()) {
      String uri = "http://localhost/shopping/lists/" + list;
      linkList.add(uri);
    }
      return Response.status(200).entity(mapper.writeValueAsString(linkList)).build();
    //return Response.status(200).entity(mapper.writeValueAsString(shoppingLists)).build();
  }

  /**
   * create a new list
   * @param newList to create
   * @return list
   * @throws IOException
   */
  @PUT
  @Path("/lists/{newList}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createAShoppingList(
      @PathParam("newList") String newList ) throws IOException {

    if (!shoppingLists.containsKey(newList)) {
      shoppingLists.put(newList, new HashMap<String, Item>());
      dbUtil.serializeWriterLists(shoppingLists);
    }

    return Response.status(200).entity(mapper.writeValueAsString(shoppingLists)).build();
  }

  /**
   * list a specific list
   * @param listName to list
   * @return the list
   * @throws IOException
   */
  @GET
  @Path("/lists/{listName}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response listShoppingList(@PathParam("listName") String listName) throws IOException {

    return Response.status(200).entity(mapper.writeValueAsString(shoppingLists.get(listName))).build();
  }

  /**
   * list a specific item from a specific list
   * @param listName to use
   * @param item wanted
   * @return items contens
   * @throws IOException
   */
  @GET
  @Path("/lists/{listName}/items/{item}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response listShoppingList(@PathParam("listName") String listName, @PathParam("item") String item) throws IOException {

    return Response.status(200).entity(mapper.writeValueAsString(shoppingLists.get(listName).get(item))).build();
  }

  /**
   * search for
   * @param listName required wanted
   * @param item optional item wanted
   * @param dateStart option start date
   * @param dateEnd option end date
   * @param price option price (less than)
   * @param store option stare
   * @return items found
   * @throws IOException
   */
  @GET
  @Path("/lists/{listName}/items")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response searchShoppingList(
      @PathParam("listName") String listName,
      @QueryParam("item") String item,
      @QueryParam("dateStart") Date dateStart,
      @QueryParam("dateEnd") Date dateEnd,
      @QueryParam("price") Double price,
      @QueryParam("store") String store) throws IOException {

    List<Item> result = new ArrayList<Item>();
    Map<String, Item> listMap = shoppingLists.get(listName);

    if (item != null) {
      for (String name : listMap.keySet()) {
        if (name.contains(item)) {
          result.add(listMap.get(name));
        }
      }
    }
    else {
        result.addAll(listMap.values());
    }

    if (result.size() > 0) {

      if (dateStart != null) {
        for (Iterator<Item> itemIterator = result.iterator(); itemIterator.hasNext();) {
          Item item_c = itemIterator.next();
          Calendar item_calendar = Calendar.getInstance();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
          try {
            item_calendar.setTime(simpleDateFormat.parse(item_c.getDate()));
          }
          catch (ParseException e) {
            e.printStackTrace();
          }
          int item_day = item_calendar.get(Calendar.DAY_OF_MONTH);

          Calendar query_calendar = Calendar.getInstance();
          query_calendar.setTime(dateStart);
          int query_day = query_calendar.get(Calendar.DAY_OF_MONTH);

          if (query_day > item_day) {
            itemIterator.remove();
          }
        }
      }

      if (dateEnd != null) {
        for (Iterator<Item> itemIterator = result.iterator(); itemIterator.hasNext();) {
          Item item_c = itemIterator.next();
          Calendar item_calendar = Calendar.getInstance();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
          try {
            item_calendar.setTime(simpleDateFormat.parse(item_c.getDate()));
          }
          catch (ParseException e) {
            e.printStackTrace();
          }
          int item_day = item_calendar.get(Calendar.DAY_OF_MONTH);

          Calendar query_calendar = Calendar.getInstance();
          query_calendar.setTime(dateEnd);
          int query_day = query_calendar.get(Calendar.DAY_OF_MONTH);

          if (query_day <= item_day) {
            itemIterator.remove();
          }
        }
      }

      if (price != null) {
        for (Iterator<Item> itemIterator = result.iterator(); itemIterator.hasNext();) {
          Item item_c = itemIterator.next();
          if (item_c.getPrice() > price) {
            itemIterator.remove();
          }
        }
      }

      if (store != null) {
        for (Iterator<Item> itemIterator = result.iterator(); itemIterator.hasNext();) {
          Item item_c = itemIterator.next();
          if (!item_c.getStore().toLowerCase().contains(store.toLowerCase()) ) {
            itemIterator.remove();
          }
        }
      }
    }

    return Response.status(200).entity(mapper.writeValueAsString(result)).build();
  }


  /**
   * add an item to a list
   * @param listName to add to
   * @param newItem to add
   * @return resulting list
   * @throws IOException
   */
  @PUT
  @Path("/lists/{listName}/items/{newItem}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateShoppingList(
      @PathParam("listName") String listName,
      @PathParam("newItem") String newItem,
      @QueryParam("price") Double price,
      @QueryParam("store") String store,
      @QueryParam("count") Integer count) throws IOException {


    if (price == null) {
      price = 0.0;
    }
    if (store == null) {
      store ="";
    }
    if (count == null) {
      count = 1;
    }
    shoppingLists.get(listName).put(newItem, new Item(newItem, price, store, count));
    dbUtil.serializeWriterLists(shoppingLists);
    return Response.status(200).entity(mapper.writeValueAsString(shoppingLists.get(listName))).build();
  }

  /**
   * remove item from a list
   * @param listName to remove from
   * @param item to remove
   * @return resulting list
   * @throws IOException
   */
  @DELETE
  @Path("/lists/{listName}/items/{item}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteShoppingListItem(
      @PathParam("listName") String listName,
      @PathParam("item") String item) throws IOException {

    shoppingLists.get(listName).remove(item);
    dbUtil.serializeWriterLists(shoppingLists);

    return Response.status(200).entity(mapper.writeValueAsString(shoppingLists.get(listName))).build();
  }

  /**
   * remove the list
   * @param listName to remove
   * @return resulting lists
   * @throws IOException
   */
  @DELETE
  @Path("/lists/{listName}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteShoppingListsList(
      @PathParam("listName") String listName) throws IOException {

    shoppingLists.remove(listName);
    dbUtil.serializeWriterLists(shoppingLists);

    return Response.status(200).entity(mapper.writeValueAsString(shoppingLists)).build();
  }

  @PUT
  @Path("/lists/rewritelist")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void copyOldListToNewList() {

    Map<String, Map<String, Item1>> shoppingListsNew = new HashMap<String, Map<String, Item1>>();

    for (String listName : shoppingLists.keySet()) {
      Map<String, Item1> newMap = new HashMap<String, Item1>();
      Map<String, Item> oldMap = new HashMap<String, Item>();
      oldMap.putAll(shoppingLists.get(listName));
      for (String itemName : oldMap.keySet()) {
        Item item = oldMap.get(itemName);
        Item1 item1 = new Item1(item.getName(),item.getPrice(), item.getStore(), item.getCount());
        //item1.setDate(item.getDate());
        newMap.put(item1.getName(), item1);
      }
      shoppingListsNew.put(listName, newMap);
    }
    dbUtil.serializeWriterListsAlt(shoppingListsNew);
  }
}
