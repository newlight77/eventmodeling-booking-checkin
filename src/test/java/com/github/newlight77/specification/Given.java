package com.github.newlight77.specification;

@FunctionalInterface
public interface Given {
    
    default Given given(final String name, final Given given) {
        System.out.println("Given " + name);
      given.execute(name);
        return given;
    }

    default When when(final String name, final When when) {
        System.out.println("When " + name);
      when.execute(name);
      return when;
    }

    public void execute(String name);
}
