package com.github.newlight77;

import com.github.newlight77.model.Room;
import com.github.newlight77.model.Rooms;

import java.util.List;
import java.util.stream.Collectors;

public class RoomReadRepository {

    private Rooms rooms;

    public RoomReadRepository(Rooms rooms) {
        this.rooms = rooms;
    }
    public RoomReadRepository() {
        this(new Rooms());
    }
    public String getCheckin(String roomNumber) {
        return new RoomFileBased().readJson(roomNumber).toString();
    }

    public List<Room> getAvailableRooms() {
        return rooms.allRooms().stream()
                .filter(r -> !r.isOccupied())
                .collect(Collectors.toList());
    }

}
