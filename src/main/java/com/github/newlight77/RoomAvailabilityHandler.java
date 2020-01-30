package com.github.newlight77;

import com.github.newlight77.model.Room;

import java.util.List;

public class RoomAvailabilityHandler {

    private RoomReadRepository roomReadRepository;

    public RoomAvailabilityHandler(RoomReadRepository roomRepository) {
        this.roomReadRepository = roomRepository;
    }

    public List<Room> availableRooms() {
        return roomReadRepository.getAvailableRooms();
    }
}
