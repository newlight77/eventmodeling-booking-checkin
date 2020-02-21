package com.github.newlight77.infrastructure.database;

import com.github.newlight77.model.Room;

import java.util.Collection;

public class HotelDatabase {

    private Rooms rooms;

    public HotelDatabase() {
        this(5);
    }
    public HotelDatabase(int roomsCount) {
        this.rooms = new Rooms(roomsCount);
    }

    public void total(int total) {
        this.rooms = new Rooms(total);
    }

    public Collection<Room> getRooms() {
        return rooms.allRooms();
    }
}
