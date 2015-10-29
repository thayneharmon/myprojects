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
      thisIsAUnceckedException();
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
    throw new Exception("This is a checked exception");
  }

  private static void thisIsAUnceckedException () {
    throw new RuntimeException("This is a unchecked runtime exception");
  }

  private static void thisIsAThrowableNonException () {
    throw new Error("Throwable non-exception error");
  }
}
