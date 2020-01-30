package com.github.newlight77.specification;

public class Beha4j {

  public static Beha4j scenario(final String name) {
    System.out.println("Scenario : " + name);
    return new Beha4j();
  }
  
  public Given given(final String name, Given given) {
    System.out.println("Given : " + name);
    given.execute(name);
    return given;
  }
  
}
