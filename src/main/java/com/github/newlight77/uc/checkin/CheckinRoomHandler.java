package com.github.newlight77.uc.checkin;

import com.github.newlight77.infrastructure.adapters.RoomWriteRepository;
import com.github.newlight77.ports.IRoomReadRepository;
import com.github.newlight77.ports.IRoomWriteRepository;

public class CheckinRoomHandler {

    private IRoomWriteRepository roomWriteRepository;
    private IRoomReadRepository roomReadRepository;

    public CheckinRoomHandler(RoomWriteRepository roomWriteRepository, IRoomReadRepository roomReadRepository) {
        this.roomWriteRepository = roomWriteRepository;
        this.roomReadRepository = roomReadRepository;
    }

    public void checkin(CheckinCommand command) {
        if (!roomReadRepository.isAvailable(command.getRoomNumber())) {
            throw new IllegalStateException();
        }
        roomWriteRepository.checkin(command.toEvent());
    }
}
