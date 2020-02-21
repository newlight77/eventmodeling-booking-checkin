package com.github.newlight77.uc.availability;

import com.github.newlight77.infrastructure.eventstore.IEventListener;
import com.github.newlight77.infrastructure.eventstore.IEventStore;
import com.github.newlight77.model.CheckedInEvent;
import com.github.newlight77.model.Room;
import com.github.newlight77.ports.IRoomReadRepository;
import com.github.newlight77.ports.IRoomWriteRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoomAvailabilityProcessor implements IEventListener<CheckedInEvent> {

    private IRoomWriteRepository roomWriteRepository;
    private IRoomReadRepository roomReadRepository;

    public RoomAvailabilityProcessor(IRoomReadRepository roomReadRepository,
                                     IRoomWriteRepository roomWriteRepository,
                                     IEventStore<CheckedInEvent> eventStore) {
        this.roomReadRepository = roomReadRepository;
        this.roomWriteRepository = roomWriteRepository;
        eventStore.register(this);
    }

    @Override
    public void onEvent(CheckedInEvent checkedInEvent) {
        if (checkedInEvent.getCheckinTime() != null && !"".equals(checkedInEvent.getCheckinTime())) {
            this.roomWriteRepository.roomAvailable(checkedInEvent.getRoomNumber(), false);
        }
    }

    public Collection<Room> availableRooms() {
        List<Room> rooms = new ArrayList<>();
        return roomReadRepository.availableRooms();
    }
}
