package com.github.newlight77.infrastructure.adapters;

import com.github.newlight77.infrastructure.database.HotelDatabase;
import com.github.newlight77.infrastructure.eventstore.IEventStore;
import com.github.newlight77.model.CheckedInEvent;
import com.github.newlight77.model.Room;
import com.github.newlight77.ports.IRoomReadRepository;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class RoomReadRepository implements IRoomReadRepository<Room> {

    private HotelDatabase database;
    private IEventStore<CheckedInEvent> eventStore;

    public RoomReadRepository(IEventStore<CheckedInEvent> eventStore, HotelDatabase database) {
        this.eventStore = eventStore;
        this.database = database;
    }

    public Room projectRoom(String roomNumber) {
        Room.RoomBuilder builder = Room.builder();
        this.eventStore.events(roomNumber)
                .forEach(event -> {
            if (event.getBadgeNumber() != null) builder.badgeNumber(event.getBadgeNumber());
            if (event.getCheckinTime() != null) builder.checkinTime(event.getCheckinTime());
            if (event.getCustomerName() != null) builder.customerName(event.getCustomerName());
            if (event.getReservationNumber() != null) builder.reservationNumber(event.getReservationNumber());
        });
        return builder.build();
    }

    public boolean isAvailable(String roomNumber) {
        return database.getRooms().stream() //
                .filter(r -> !roomNumber.equals(r.getRoomNumber())) //
                .findFirst()
                .orElse(new Room())
                .isAvailable();
    }

    public Collection<Room> availableRooms() {
        return database.getRooms().stream() //
                .filter(Room::isAvailable) //
                .collect(toList());
    }
}
