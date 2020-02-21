package com.github.newlight77.infrastructure.eventstore;

public interface IPublisher<T> {
    void publish(T json);
}
