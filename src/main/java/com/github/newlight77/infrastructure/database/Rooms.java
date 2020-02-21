package com.github.newlight77.infrastructure.database;

import com.github.newlight77.model.Room;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Rooms {

    private Map<String, Room> rooms = new HashMap<>();
    public Rooms(int roomsCount) {
        for (int i = 1 ; i <= roomsCount ; i++) {
            String roomNumber = String.valueOf(i);
            rooms.put(roomNumber, Room.builder() //
                    .available(true) //
                    .roomNumber(roomNumber) //
                    .type("Double") //
                    .build()
            );
        }
    }

    public Room getByNumber(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<Room> allRooms() {
        return rooms.values();
    }
}
