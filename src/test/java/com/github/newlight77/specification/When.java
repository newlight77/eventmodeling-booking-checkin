package com.github.newlight77.specification;

@FunctionalInterface
public interface When {

  default When when(final String name, final When when) {
    when.execute(name);
    return when;
  }

  default When then(final String name, final When when) {
    when.execute(name);
    return when;
  }

  public void execute(String name);
}
