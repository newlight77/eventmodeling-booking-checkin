package com.github.newlight77.uc.availability;

import com.github.newlight77.events.EventListener;
import com.github.newlight77.model.Room;
import com.github.newlight77.repository.RoomReadRepository;

import java.util.List;

public class RoomAvailabilityHandler implements EventListener {

    private RoomReadRepository roomReadRepository;

    public RoomAvailabilityHandler(RoomReadRepository roomRepository) {
        this.roomReadRepository = roomRepository;
    }

    public List<Room> availableRooms() {
        return roomReadRepository.getAvailableRooms();
    }

    @Override
    public void onEvent(String json) {

    }
}
