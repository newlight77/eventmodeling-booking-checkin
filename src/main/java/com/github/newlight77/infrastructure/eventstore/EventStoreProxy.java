package com.github.newlight77.infrastructure.eventstore;

import com.github.newlight77.model.CheckedInEvent;
import com.github.newlight77.util.JsonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventStoreProxy implements IEventStore<CheckedInEvent> {

    private final Map<String, Collection<CheckedInEvent>> checkedInEvents = new HashMap<>();

    private IEventStore<CheckedInEvent> eventStore;
    private Collection<IEventListener<CheckedInEvent>> listeners = new ArrayList<>();

    public EventStoreProxy(IEventStore<CheckedInEvent> eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void register(IEventListener listener) {
        listeners.add(listener);
        this.eventStore.register(listener);
    }

    @Override
    public Collection<IEventListener<CheckedInEvent>> listeners() {
        return listeners;
    }

    @Override
    public void eventFired(CheckedInEvent checkedInEvent) {
        this.store(checkedInEvent);
        this.broadcast(checkedInEvent);
    }

    @Override
    public void store(CheckedInEvent checkedInEvent) {
        this.storeInCache(checkedInEvent);
        this.eventStore.store(checkedInEvent);
    }

    @Override
    public Collection<CheckedInEvent> events(String roomNumber) {
        return eventsFromCache(roomNumber);
    }

    private void storeInCache(CheckedInEvent checkedInEvent) {
        if (!checkedInEvents.containsKey(checkedInEvent.getRoomNumber())) {
            checkedInEvents.put(checkedInEvent.getRoomNumber(), new ArrayList<>());
        }
        checkedInEvents.get(checkedInEvent.getRoomNumber()).add(checkedInEvent);
    }

    private Collection<CheckedInEvent> eventsFromCache(String roomNumber) {
        return checkedInEvents.get(roomNumber);
    }
}
