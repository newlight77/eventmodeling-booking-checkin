package com.github.newlight77.infrastructure.adapters;

import com.github.newlight77.infrastructure.database.HotelDatabase;
import com.github.newlight77.infrastructure.eventstore.IEventStore;
import com.github.newlight77.model.CheckedInEvent;
import com.github.newlight77.ports.IRoomWriteRepository;

public class RoomWriteRepository implements IRoomWriteRepository<CheckedInEvent> {

    private HotelDatabase database;
    private IEventStore<CheckedInEvent> eventStore;

    public RoomWriteRepository(IEventStore<CheckedInEvent> eventStore, HotelDatabase database) {
        this.eventStore = eventStore;
        this.database = database;
    }

    public void checkin(CheckedInEvent checkedInEvent) {
        eventStore.store(checkedInEvent);
    }

    public void roomAvailable(String roomNumber, boolean available) {
        database.getRooms().stream() //
                .filter(r -> r.getRoomNumber().equals(roomNumber)) //
                .forEach(r -> r.setAvailable(available));

        eventStore.store(CheckedInEvent.builder() //
                .roomNumber(roomNumber) //
                .available(available) //
                .build());
    }
}
