package com.example.rest;

import java.io.IOException;
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

import com.example.DBUtil;
import com.example.Item;
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

    return Response.status(200).entity(mapper.writeValueAsString(shoppingLists)).build();
  }

  /**
   * create a new list
   * @param newList to create
   * @return list
   * @throws IOException
   */
  @POST
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
   * search for specific items in a list
   * @param listName to search
   * @param item to search for (will find all partial matches)
   * @return the items found
   * @throws IOException
   */
  @GET
  @Path("/lists/{listName}/search")
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
          item_calendar.setTime(item_c.getDate());
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
          item_calendar.setTime(item_c.getDate());
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
  @POST
  @Path("/lists/{listName}/items/{newItem}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateShoppingList(
      @PathParam("listName") String listName,
      @PathParam("newItem") String newItem,
      @QueryParam("price") Double price,
      @QueryParam("store") String store) throws IOException {


    if (price == null) {
      price = 0.0;
    }
    if (store == null) {
      store ="";
    }
    shoppingLists.get(listName).put(newItem, new Item(newItem, price, store));
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
  @Path("/lists/{listName}/items/(item)")
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
}
