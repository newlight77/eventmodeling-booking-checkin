package com.github.newlight77.repository.database;

import com.github.newlight77.model.Room;

import java.util.*;

public class Rooms {

    private Map<String, Room> rooms = new HashMap<>();
    public Rooms() {
        this(10);
    }
    public Rooms(int roomsCount) {
        for (int i = 1 ; i <= roomsCount ; i++) {
            String roomNumber = String.valueOf(i);
            rooms.put(roomNumber, Room.builder() //
                    .occupied(false) //
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
