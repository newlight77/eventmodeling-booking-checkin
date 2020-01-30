package com.github.newlight77.specification;

@FunctionalInterface
public interface When {

  default When when(final String name, final When when) {
    System.out.println("When " + name);
    when.execute(name);
    return when;
  }

  default When then(final String name, final When when) {
    System.out.println("Then " + name);
    when.execute(name);
    return when;
  }

  public void execute(String name);
}
