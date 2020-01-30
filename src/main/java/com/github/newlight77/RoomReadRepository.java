package com.github.newlight77;

import com.github.newlight77.model.Room;
import com.github.newlight77.model.Rooms;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RoomReadRepository {

    private RoomsFileDatabase database;

    public RoomReadRepository(RoomsFileDatabase database) {
        this.database = database;
    }
    public String getCheckin(String roomNumber) {
        return database.readJson(roomNumber).toString();
    }

    public List<Room> getAvailableRooms() {
        Rooms rooms = database.getRooms();
        return rooms.allRooms().stream()
                .filter(r -> !r.isOccupied())
                .collect(toList());
    }
}
