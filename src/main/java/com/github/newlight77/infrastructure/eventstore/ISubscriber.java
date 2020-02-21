package com.github.newlight77.infrastructure.eventstore;

import java.util.function.Consumer;

public interface ISubscriber<T> {
    void subscribe(Consumer<T> callback);
}
