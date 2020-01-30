package com.github.newlight77.specification;

public class Beha4j {

  public static Beha4j scenario(final String name) {
    System.out.println("Scenario : " + name);
    return new Beha4j();
  }
  
  public Given given(final String name, Given given) {
    given.execute(name);
    return given;
  }
  
}
