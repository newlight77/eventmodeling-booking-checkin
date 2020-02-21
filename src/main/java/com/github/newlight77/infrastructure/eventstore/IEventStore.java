package com.github.newlight77.infrastructure.eventstore;

import java.util.Collection;

public interface IEventStore<T> {

    Collection<IEventListener<T>> listeners();

    void register(IEventListener listener);

    void eventFired(T roomEvent);

    void store(T roomEvent);

    Collection<T> events(String roomNumber);

    default void broadcast(T event) {
        for (IEventListener<T> listener : listeners()) {
            listener.onEvent(event);
        }
    }
}
