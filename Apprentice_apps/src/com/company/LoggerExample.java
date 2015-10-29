package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.slf4j.MDC;
//import java.net.Inet4Address;
//import org.slf4j.impl.Log4jLoggerFactory;

///**
// * Created by thayneharmon on 7/13/2015.
// */
public class LoggerExample {
  private static final Logger slf4jLogger = LoggerFactory.getLogger(LoggerExample.class);

  public static void main( String[] args )
  {
    //try {
    //    MDC.put("hostname", Inet4Address.getLocalHost().getHostName());
    //}catch(Exception a){
    //    slf4jLogger.error("message=\"{}\"", a.getMessage(), a);
    //}
    //System.out.println( "Hello World!" );
    slf4jLogger.debug("Message: {} {}","Hello", "World");
    slf4jLogger.info("Message: {} {}","Hello", "World");
    slf4jLogger.warn("Message: {} {}","Hello", "World");
    try{
      throw new Exception("Hello Error");
    }catch (Exception e){
      slf4jLogger.error("Message: {}",e.getMessage(), e);
    }
  }
}
