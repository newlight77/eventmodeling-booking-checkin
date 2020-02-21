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

public class EventStoreFile implements IEventStore<CheckedInEvent> {

    private final Map<String, CheckedInEvent> checkedInEvents = new HashMap<>();

    private EventStoreFileConfig config;
    private Collection<IEventListener<CheckedInEvent>> listeners = new ArrayList<>();

    public EventStoreFile(EventStoreFileConfig config) {
        this.config = config;
    }

    @Override
    public void register(IEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public Collection<IEventListener<CheckedInEvent>> listeners() {
        return listeners;
    }

    @Override
    public void eventFired(CheckedInEvent checkedInEvent) {
        this.broadcast(checkedInEvent);
    }

    @Override
    public void store(CheckedInEvent checkedInEvent) {
        this.storeInFile(checkedInEvent);
        eventFired(checkedInEvent);
    }

    @Override
    public Collection<CheckedInEvent> events(String roomNumber) {
        return eventsFromFile(roomNumber);
    }

    private void storeInFile(CheckedInEvent checkedInEvent) {
        try {
            Path path = Paths.get(config.configPath(), "rooms-" + checkedInEvent.getRoomNumber() + ".json");
            Files.writeString(path, JsonUtil.toJson(checkedInEvent));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to write RoomEvents to file", e);
        }
    }

    private Collection<CheckedInEvent> eventsFromFile(String roomNumber) {
        try {
            Path path = Paths.get(config.configPath(), "rooms-" + roomNumber + ".json");
            String json = "[" + Files.readString(path) + "]";
            return JsonUtil.toListOfObjects(json);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read RoomEvents from file", e);
        }
    }
}
