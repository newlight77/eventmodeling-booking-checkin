package com.github.newlight77.infrastructure.eventstore;

public interface IEventListener<T> {
    void onEvent(T event);
}
