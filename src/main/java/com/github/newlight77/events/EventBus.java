package com.github.newlight77.events;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private List<EventListener> listeners = new ArrayList<>();

    public void register(EventListener listener) {
        listeners.add(listener);
    }

    public void fireEvent(String json) {
        for (EventListener listener : listeners) {
            listener.onEvent(json);
        }
    }
}
