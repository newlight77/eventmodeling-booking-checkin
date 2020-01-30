package com.github.newlight77.repository;

import com.github.newlight77.repository.database.RoomsFileDatabase;
import com.github.newlight77.model.Room;

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
        return database.getRooms().stream()
                .filter(r -> !r.isOccupied())
                .collect(toList());
    }
}
