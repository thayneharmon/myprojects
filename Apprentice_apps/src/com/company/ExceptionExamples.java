package com.company;

/**
 * Created by thayneharmon on 7/13/2015.
 */
public class ExceptionExamples {

  public static void main(String[] args) {
    //show exception handling including examples of checked, unchecked, and Error exceptions
    try {
      thisACheckedException();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

    try {
      thisIsAUncheckedException();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      thisIsAThrowableNonException();
    }
    catch (Throwable e) {
      System.out.println(e.getMessage());
    }

  }
  private static void thisACheckedException() throws Exception {
    throw new Exception("This is a checked exception, use for recoverable errors.");
  }

  private static void thisIsAUncheckedException () {
    throw new RuntimeException("This is a unchecked runtime exception, coding error, un-retry-able error");
  }

  private static void thisIsAThrowableNonException () {
    throw new Error("This is a Throwable non-exception error, should not catch");
  }
}
