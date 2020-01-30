package com.github.newlight77.specification;

public class Beha4j {

  public static Beha4j scenario(final String name) {
    System.out.println("Scenario : " + name);
    return new Beha4j();
  }
  
  public Beha4j given(final String name, Given given) {
    System.out.println("Given : " + name);
    given.execute(name);
    return this;
  }

  public Beha4j when(final String name, final When when) {
    System.out.println("When " + name);
    when.execute(name);
    return this;
  }

  public Beha4j then(final String name, final Then then) {
    System.out.println("Then " + name);
    then.execute(name);
    return this;
  }
}
