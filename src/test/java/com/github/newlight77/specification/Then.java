package com.github.newlight77.specification;

@FunctionalInterface
public interface Then {
  
    default When then(final String name, final When when) {
        System.out.println("Then " + name);
        when.execute(name);
        return when;
    }

    public abstract void execute(String name);
}
